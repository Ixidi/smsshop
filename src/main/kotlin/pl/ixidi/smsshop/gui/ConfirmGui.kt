package pl.ixidi.smsshop.gui

import org.bukkit.Material
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.offer.Offer
import pl.ixidi.smsshop.extension.color
import pl.ixidi.smsshop.gui.action.BuyAction
import pl.ixidi.smsshop.gui.action.CheckAction
import pl.ixidi.smsshop.gui.action.SwitchAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class ConfirmGui(
        private val offer: Offer,
        account: Account,
        private val previous: Gui
) : AbstractGui("&6&lSmsShop", account, BasicGuiButton(Material.PAPER, 0, SmsShopPlugin.instance.language.get("areYouSure")), moneyStatusClick = false, transactions = false) {

    override fun reload() {
        val lore = SmsShopPlugin.instance.language.get("offer", mapOf("category" to offer.category.name, "price" to offer.price.toString(), "lore" to offer.lore))
        setButton(13, BasicGuiButton(offer.material, offer.durability, offer.title.color(), lore.split("\n").map { it.color() }))
        setButton(29, BasicGuiButton(Material.REDSTONE_BLOCK, 0, SmsShopPlugin.instance.language.get("noBuy"), action = SwitchAction(previous)))
        setButton(33, BasicGuiButton(Material.EMERALD_BLOCK, 0, SmsShopPlugin.instance.language.get("yesBuy"), action = BuyAction(offer, account)))
        setButton(33, BasicGuiButton(Material.EMERALD_BLOCK, 0, SmsShopPlugin.instance.language.get("yesBuy"), action = BuyAction(offer, account)))
    }

}