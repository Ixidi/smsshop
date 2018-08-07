package pl.ixidi.smsshop.api.gui

import org.bukkit.entity.Player

interface GuiManager {

    fun getCurrent(player: Player): Gui?

    fun add(player: Player, gui: Gui)

    fun remove(player: Player)

}