package pl.ixidi.smsshop.command.base

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pl.ixidi.smsshop.extension.message

abstract class PlayerCommand(val permission: String = "") : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.message("playerOnly")
            return true
        }
        if (!permission.isNotBlank() && !sender.hasPermission(permission)) {
            sender.message("permission")
            return true
        }
        execute(sender, args.asList())
        return true
    }

    abstract fun execute(sender: Player, args: List<String>)

}