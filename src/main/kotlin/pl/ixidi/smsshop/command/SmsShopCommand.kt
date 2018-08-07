package pl.ixidi.smsshop.command

import org.bukkit.entity.Player
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.ReloadType
import pl.ixidi.smsshop.api.log.LogType
import pl.ixidi.smsshop.account.BasicLog
import pl.ixidi.smsshop.command.base.PlayerCommand
import pl.ixidi.smsshop.extension.message
import pl.ixidi.smsshop.extension.rawMessage
import pl.ixidi.smsshop.gui.InfoGui
import pl.ixidi.smsshop.storage.AccountStorage
import pl.ixidi.smsshop.util.DateUtils

class SmsShopCommand : PlayerCommand("smsshop.admin") {


    override fun execute(sender: Player, args: List<String>) {
        if (args.isEmpty() || args[0] == "help") {
            sender.rawMessage("&6&lSmsShop ${SmsShopPlugin.instance.description.version} &7by Ixidi")
            sender.message("helpCommand")
            return
        }
        when (args[0]) {
            "reload" -> {
                SmsShopPlugin.instance.reload(ReloadType.ALL)
                sender.message("reloaded")
            }
            "account" -> {
                val account = if (args.size >= 2)
                    (SmsShopPlugin.instance.accountStorage as AccountStorage).getByName(args[1])
                else {
                    sender.message("noNickname")
                    return
                }

                if (account == null) {
                    sender.message("accountNotFound", mapOf("target" to args[1]))
                    return
                }

                if (args.size <= 2) {
                    val gui = InfoGui(account, moneyStatusClick = false)
                    gui.reload()
                    gui.open(sender)
                    return
                }

                val value = args[2].toLongOrNull()
                if (value == null) {
                    sender.message("valueNotNumber", mapOf("value" to args[2]))
                    return
                }

                val log = BasicLog(LogType.ADMIN)
                log.addProperty("executor", sender.name)
                log.addProperty("before", account.money.toString())
                log.addProperty("value", value.toString())

                val operation = if (args.size <= 3) "set" else args[3]

                when (operation) {
                    "add", "+" -> {
                        account.addMoney(value)
                        log.addProperty("operation", "addition")
                        sender.message("accountModify.add", mapOf("target" to account.name, "value" to value.toString(), "now" to account.money.toString()))
                    }
                    "subtract", "-" -> {
                        account.subtractMoney(value)
                        log.addProperty("operation", "subtracting")
                        sender.message("accountModify.subtract", mapOf("target" to account.name, "value" to value.toString(), "now" to account.money.toString()))
                    }
                    "set", "=" -> {
                        account.money = value
                        log.addProperty("operation", "setUp")
                        sender.message("accountModify.set", mapOf("target" to account.name, "value" to value.toString()))
                    }
                    else -> {
                        sender.message("accountModify.unsupported")
                        return
                    }
                }

                log.addProperty("after", account.money.toString())
                log.addProperty("date", DateUtils.now())
                account.addLog(log)
            }

            else -> {
                sender.message("unsupportedCommand")
            }
        }
    }

}