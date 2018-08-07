package pl.ixidi.smsshop.extension

import org.bukkit.configuration.ConfigurationSection
import kotlin.reflect.KClass

fun ConfigurationSection.validString(key: String): String = this.getString(key) ?: throw NullPointerException()

fun ConfigurationSection.isInvalid(): Boolean = this.getBoolean("INVALID")

fun ConfigurationSection.markInvalid() {
    this.set("INVALID", true)
}
