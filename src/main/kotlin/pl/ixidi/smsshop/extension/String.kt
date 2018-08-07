package pl.ixidi.smsshop.extension

import org.bukkit.ChatColor

fun String.color(): String = ChatColor.translateAlternateColorCodes('&', this)

fun String.parseArgs(args: Map<String, String>): String {
    var string = this
    args.forEach {
        string = string.replace("%${it.key}%", it.value)
    }
    return string
}