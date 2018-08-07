package pl.ixidi.smsshop.api.gui

import org.bukkit.entity.Player

interface Gui {

    val title: String

    fun setButton(slot: Int, item: GuiButton)

    fun getButton(slot: Int): GuiButton?

    fun open(player: Player)

    fun reload()
}