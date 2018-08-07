package pl.ixidi.smsshop.gui.action

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiAction
import pl.ixidi.smsshop.api.log.LogType
import pl.ixidi.smsshop.api.offer.Offer
import pl.ixidi.smsshop.account.BasicLog
import pl.ixidi.smsshop.extension.message
import pl.ixidi.smsshop.extension.parseArgs
import pl.ixidi.smsshop.util.DateUtils

class BuyAction(
        private val offer: Offer,
        private val account: Account
) : GuiAction {

    override fun onClick(player: Player, gui: Gui, slot: Int) {
        if (!account.canBuy(offer)) {
            player.closeInventory()
            player.message("noMoney", mapOf("money" to account.money.toString(), "price" to offer.price.toString()))
            return
        }
        val log = BasicLog(LogType.ORDER)
        log.addProperty("offer", offer.title)
        log.addProperty("before", account.money.toString())
        log.addProperty("price", offer.price.toString())
        account.subtractMoney(offer.price)
        log.addProperty("after", account.money.toString())
        log.addProperty("date", DateUtils.now())
        account.addLog(log)
        player.closeInventory()
        offer.commands.forEach {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), it.parseArgs(mapOf("player" to player.name, "offer" to offer.title, "offer-raw" to ChatColor.stripColor(offer.title))))
        }
        player.message("success", mapOf("offer" to offer.title, "price" to offer.price.toString()))
    }

}