package pl.ixidi.smsshop.gui

import org.bukkit.Material
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.offer.OfferCategory
import pl.ixidi.smsshop.gui.action.SwitchAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class OfferGui(val category: OfferCategory, val prevoius: Gui, val account: Account): AbstractGui("&6&lSmsShop") {

    override fun reload() {
        val offers = SmsShopPlugin.instance.offerStorage.getAll().filter { it.category == category }
        if (offers.isEmpty()) {
            val button = BasicGuiButton(Material.BARRIER, SmsShopPlugin.instance.language.get("noOffers"), action = SwitchAction(prevoius))
            repeat(55) {
                setButton(it - 1, button)
            }
        }
    }

}