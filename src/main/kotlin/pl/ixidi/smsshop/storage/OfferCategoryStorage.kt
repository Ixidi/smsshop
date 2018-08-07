package pl.ixidi.smsshop.storage

import org.bukkit.Material
import org.bukkit.configuration.ConfigurationSection
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.offer.OfferCategory
import pl.ixidi.smsshop.offer.BasicOffer
import pl.ixidi.smsshop.offer.BasicOfferCategory
import pl.ixidi.smsshop.extension.isInvalid
import pl.ixidi.smsshop.extension.markInvalid
import pl.ixidi.smsshop.extension.validString
import pl.ixidi.smsshop.storage.base.SingleFileStorage
import pl.ixidi.smsshop.util.MaterialUtils
import java.io.File

class OfferCategoryStorage(file: File) : SingleFileStorage<String, OfferCategory>(file) {

    companion object {
        val DEFAULT = BasicOfferCategory("default", SmsShopPlugin.instance.language.get("words.default"), "", 5, Material.BOOK)
    }

    override fun onSave(value: OfferCategory, section: ConfigurationSection) {
        section.set("name", value.name)
        section.set("title", value.title)
        section.set("lore", value.lore)
        section.set("guiSlot", value.guiSlot)
        section.set("material", value.material)
    }

    override fun onLoad(section: ConfigurationSection): OfferCategory? {
        if (section.isInvalid()) return null

        return try {
            val name = section.validString("name")
            val title = section.validString("title")
            val lore = section.validString("lore")
            var guiSlot = section.getInt("guiSlot")
            if (guiSlot < 0 || guiSlot > 44) guiSlot = 0
            val materialSplit = section.validString("material").split(":")
            val durability = if (materialSplit.size >= 2) materialSplit[1].toShortOrNull() ?: throw RuntimeException() else 0
            val material = MaterialUtils.matchMaterial(materialSplit[0]) ?: throw RuntimeException()
            BasicOfferCategory(name, title, lore, guiSlot, material, durability)
        } catch (ex: Exception) {
            section.markInvalid()
            return null
        }
    }

}