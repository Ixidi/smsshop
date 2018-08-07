package pl.ixidi.smsshop.extension

import org.bukkit.ChatColor

fun String.color(): String = ChatColor.translateAlternateColorCodes('&', this)