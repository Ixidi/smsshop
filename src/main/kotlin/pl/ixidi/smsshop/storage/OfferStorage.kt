package pl.ixidi.smsshop.storage

import org.bukkit.configuration.ConfigurationSection
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.offer.Offer
import pl.ixidi.smsshop.base.offer.BasicOffer
import pl.ixidi.smsshop.extension.markInvalid
import pl.ixidi.smsshop.extension.validString
import pl.ixidi.smsshop.storage.base.SingleFileStorage

import pl.ixidi.smsshop.util.MaterialUtils
import java.io.File

class OfferStorage(file: File) : SingleFileStorage<Int, Offer>(file) {

    override fun onSave(value: Offer, section: ConfigurationSection) {
        section.set("title", value.title)
        section.set("lore", value.lore)
        section.set("category", value.category.name)
        section.set("material", value.material.name)
        section.set("price", value.price)
        section.set("commands", value.commands)
    }

    override fun onLoad(section: ConfigurationSection): Offer? {
        return try {
            val title = section.validString("title")
            val lore = section.validString("lore")
            val category = SmsShopPlugin.instance.categoryStorage.get(section.validString("category")) {
                OfferCategoryStorage.DEFAULT
            }
            val material = MaterialUtils.matchMaterial(section.validString("material")) ?: throw RuntimeException()
            val price = section.getLong("price")
            val commands = section.getStringList("commands") ?: throw RuntimeException()
            BasicOffer(getAll().size + 1, title, lore, category!!, material, price, commands)
        } catch (ex: Exception) {
            section.markInvalid()
            return null
        }
    }

}