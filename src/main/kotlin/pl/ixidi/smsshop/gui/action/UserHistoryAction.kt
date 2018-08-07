package pl.ixidi.smsshop.gui.action

import org.bukkit.entity.Player
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiAction
import pl.ixidi.smsshop.gui.InfoGui

class UserHistoryAction(
        private val account: Account
) : GuiAction {

    override fun onClick(player: Player, gui: Gui, slot: Int) {
        val infoGui = InfoGui(account, gui, moneyStatusClick = false)
        infoGui.reload()
        infoGui.open(player)
    }

}