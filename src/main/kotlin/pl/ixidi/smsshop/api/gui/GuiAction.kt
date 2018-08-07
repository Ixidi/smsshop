package pl.ixidi.smsshop.api.gui

import org.bukkit.entity.Player

@FunctionalInterface
interface GuiAction {

    fun onClick(player: Player, gui: Gui, slot: Int)

}