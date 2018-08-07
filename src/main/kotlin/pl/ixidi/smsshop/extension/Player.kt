package pl.ixidi.smsshop.extension

import org.bukkit.entity.Player
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.base.BasicAccount

private val storage = SmsShopPlugin.accountStorage

fun Player.message(text: String) {
    this.sendMessage(text.color())
}

fun Player.account(): Account {
    return storage.get(this.uniqueId) {
        val account = BasicAccount(this.uniqueId, this.name)
        storage.add(account)
        account
    }!!
}