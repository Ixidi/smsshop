package pl.ixidi.smsshop.command

import org.bukkit.entity.Player
import pl.ixidi.smsshop.command.base.PlayerCommand
import pl.ixidi.smsshop.extension.account
import pl.ixidi.smsshop.gui.CategoryGui

class ShopCommand : PlayerCommand() {

    override fun execute(sender: Player, args: List<String>) {
        val gui = CategoryGui(sender.account())
        gui.reload()
        gui.open(sender)
    }

}