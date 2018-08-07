package pl.ixidi.smsshop.api.gui

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

interface GuiButton {

    val material: Material

    val name: String

    val lore: List<String>

    val action: GuiAction

    fun getItem(): ItemStack

}