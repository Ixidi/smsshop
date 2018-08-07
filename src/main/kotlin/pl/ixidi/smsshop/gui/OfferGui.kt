package pl.ixidi.smsshop.gui

import org.bukkit.Material
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.api.offer.OfferCategory
import pl.ixidi.smsshop.extension.color
import pl.ixidi.smsshop.gui.action.CheckAction
import pl.ixidi.smsshop.gui.action.SwitchAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class OfferGui(private val category: OfferCategory,
               private val previous: Gui,
               account: Account
): AbstractGui("&6&lSmsShop", account) {

    override fun reload() {
        val offers = SmsShopPlugin.instance.offerStorage.getAll().filter { it.category == category }
        if (offers.isEmpty()) {
            val button = BasicGuiButton(Material.BARRIER, 0, SmsShopPlugin.instance.language.get("noOffers"), action = SwitchAction(previous))
            repeat(55) {
                setButton(it - 1, button)
            }
            return
        }
        setButton(45, BasicGuiButton(Material.STICK, 0, "&f&l<--", action = SwitchAction(previous)))
        val buttons = ArrayList<GuiButton>()
        for ((index, offer) in offers.withIndex()) {
            val lore = SmsShopPlugin.instance.language.get("offer", mapOf("category" to category.name, "price" to offer.price.toString(), "lore" to offer.lore))
            val button = BasicGuiButton(offer.material, offer.durability, offer.title.color(), lore.split("\n").map { it.color() }, CheckAction(offer, account))
            if (index > 44) {
                buttons.add(button)
                continue
            }
            setButton(index, button)
        }

        if (buttons.isEmpty()) return
        val pageGui = PageGui(account, 2, this, buttons)
        setButton(53, BasicGuiButton(Material.STICK, 0, "&f&l-->", action = SwitchAction(pageGui)))
    }

}