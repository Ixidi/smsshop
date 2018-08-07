package pl.ixidi.smsshop.gui.action

import org.bukkit.entity.Player
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiAction
import pl.ixidi.smsshop.api.offer.Offer
import pl.ixidi.smsshop.extension.message
import pl.ixidi.smsshop.gui.ConfirmGui

class CheckAction(private val offer: Offer, private val account: Account) : GuiAction {

    override fun onClick(player: Player, gui: Gui, slot: Int) {
        if (!account.canBuy(offer)) {
            player.closeInventory()
            player.message("noMoney", mapOf("money" to account.money.toString(), "price" to offer.price.toString()))
            return
        }
        val confirmGui = ConfirmGui(offer, account, gui)
        confirmGui.reload()
        confirmGui.open(player)
    }

}