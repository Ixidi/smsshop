package pl.ixidi.smsshop.command

import org.bukkit.entity.Player
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.command.base.PlayerCommand
import pl.ixidi.smsshop.extension.message
import pl.ixidi.smsshop.extension.rawMessage
import pl.ixidi.smsshop.storage.AccountStorage

class SmsShopCommand : PlayerCommand("smsshop.admin") {

    private val storage = SmsShopPlugin.accountStorage as AccountStorage

    override fun execute(sender: Player, args: List<String>) {
        if (args.isEmpty() || args[0] == "help") {
            sender.rawMessage("&6&lSmsShop ${SmsShopPlugin.description.version} &7by Ixidi")
            sender.message("helpCommand")
            return
        }
        when (args[0]) {
            "account" -> {
                val account = if (args.size >= 2)
                    storage.getByName(args[1])
                else
                    storage.get(sender.uniqueId)

                if (account == null) {
                    sender.message("accountNotFound", mapOf("target" to args[1]))
                    return
                }

                if (args.size <= 2) {
                    //TODO Display GUI
                    return
                }
            }

        }
    }

}