package pl.ixidi.smsshop.extension

import org.bukkit.entity.Player
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.account.BasicAccount

fun Player.account(): Account {
    return SmsShopPlugin.instance.accountStorage.get(this.uniqueId) {
        val account = BasicAccount(this.uniqueId, this.name)
        SmsShopPlugin.instance.accountStorage.add(account)
        account
    }!!
}