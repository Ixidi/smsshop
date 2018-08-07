package pl.ixidi.smsshop.gui.action

import org.bukkit.entity.Player
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiAction

class SwitchAction(private val toSwitch: Gui): GuiAction {

    override fun onClick(player: Player, gui: Gui, slot: Int) {
        toSwitch.reload()
        toSwitch.open(player)
    }

}