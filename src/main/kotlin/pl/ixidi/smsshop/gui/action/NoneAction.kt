package pl.ixidi.smsshop.gui.action

import org.bukkit.entity.Player
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiAction

object NoneAction : GuiAction {
    override fun onClick(player: Player, gui: Gui, slot: Int) {}
}
