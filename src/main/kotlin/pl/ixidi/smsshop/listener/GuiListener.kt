package pl.ixidi.smsshop.listener

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import pl.ixidi.smsshop.SmsShopPlugin

class GuiListener : Listener {

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        SmsShopPlugin.instance.guiManager.remove(event.player as Player)
    }

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player

        if (event.clickedInventory == null) return

        val item = event.currentItem ?: return
        if (item.type == Material.AIR) return

        val gui = SmsShopPlugin.instance.guiManager.getCurrent(player) ?: return
        event.isCancelled = true

        val slot = event.slot
        val guiItem = gui.getButton(slot) ?: return
        guiItem.action.onClick(player, gui, slot)
    }

}