package pl.ixidi.smsshop.gui.action

import org.bukkit.entity.Player
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiAction
import pl.ixidi.smsshop.api.offer.OfferCategory
import pl.ixidi.smsshop.gui.OfferGui

class ShowOffersAction(val category: OfferCategory, val account: Account) : GuiAction {

    override fun onClick(player: Player, gui: Gui, slot: Int) {
        val offerGui = OfferGui(category, gui, account)
        offerGui.reload()
        offerGui.open(player)
    }

}