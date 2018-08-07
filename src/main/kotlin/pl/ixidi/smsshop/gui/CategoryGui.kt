package pl.ixidi.smsshop.gui

import org.bukkit.Material
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.gui.action.ShowOffersAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class CategoryGui(account: Account) : AbstractGui("&6&lSmsShop", account) {

    override fun reload() {
        val list = SmsShopPlugin.instance.categoryStorage.getAll()
        for (category in list) {
            val button = BasicGuiButton(category.material, category.durability, category.name, category.lore.split("\n"), ShowOffersAction(category, account))
            setButton(category.guiSlot, button)
        }
    }


}