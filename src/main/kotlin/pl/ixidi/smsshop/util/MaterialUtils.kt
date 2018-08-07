package pl.ixidi.smsshop.util

import org.bukkit.Material

object MaterialUtils {

    fun matchMaterial(name: String): Material? = Material.matchMaterial(name.toUpperCase().replaceAfter(" ", "_"))

}