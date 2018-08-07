package pl.ixidi.smsshop.extension

import org.bukkit.command.CommandSender
import pl.ixidi.smsshop.SmsShopPlugin

fun CommandSender.rawMessage(text: String) {
    this.sendMessage(text.color())
}

fun CommandSender.message(key: String, map: Map<String, String> = emptyMap()) {
    rawMessage(SmsShopPlugin.language.get(key, map))
}

