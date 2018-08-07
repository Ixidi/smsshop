package pl.ixidi.smsshop.gui.base

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.extension.color

abstract class AbstractGui(final override val title: String) : Gui {

    private val inventory = Bukkit.createInventory(null, 54, title.color())
    private val map = HashMap<Int, GuiButton>()

    override fun setButton(slot: Int, item: GuiButton) {
        if (slot > 54 || slot < 0) return
        map[slot] = item
        inventory.setItem(slot, item.getItem())
    }

    override fun getButton(slot: Int): GuiButton? = map[slot]

    override fun open(player: Player) {
        val inv = Bukkit.createInventory(player, 54, title.color())
        for ((index, content) in inventory.contents.toList().withIndex()) {
            inv.setItem(index, content)
        }
        player.openInventory(inv)
        SmsShopPlugin.instance.guiManager.add(player, this)
    }

}