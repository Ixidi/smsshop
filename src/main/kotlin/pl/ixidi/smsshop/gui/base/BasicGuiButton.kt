package pl.ixidi.smsshop.gui.base

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import pl.ixidi.smsshop.api.gui.GuiAction
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.extension.color
import pl.ixidi.smsshop.gui.action.NoneAction

data class BasicGuiButton(
        override val material: Material,
        override val name: String,
        override val lore: List<String> = emptyList(),
        override val action: GuiAction = NoneAction
) : GuiButton {

    override fun getItem(): ItemStack {
        val stack = ItemStack(material)
        val meta = stack.itemMeta
        meta.displayName = name.color()
        meta.lore = lore.map { it.color() }
        stack.itemMeta = meta
        return stack
    }

}