package pl.ixidi.smsshop.gui

import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.gui.action.ShowOffersAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class CategoryGui(val account: Account) : AbstractGui("&6&lSmsShop") {

    override fun reload() {
        val list = SmsShopPlugin.instance.categoryStorage.getAll()
        println(list.size)
        for (category in list) {
            val button = BasicGuiButton(category.material, category.name, category.lore.split("\n"), ShowOffersAction(category, account))
            setButton(category.guiSlot, button)
        }
    }


}