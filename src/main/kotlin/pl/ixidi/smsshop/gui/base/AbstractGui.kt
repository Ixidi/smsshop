package pl.ixidi.smsshop.gui.base

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.extension.color
import pl.ixidi.smsshop.gui.action.UserHistoryAction

abstract class AbstractGui(
        final override val title: String,
        protected val account: Account,
        fill: GuiButton = BasicGuiButton(Material.STAINED_GLASS_PANE, 8, " "),
        moneyStatus: Boolean = true,
        moneyStatusClick: Boolean = true,
        transactions: Boolean = true
) : Gui {

    private val inventory = Bukkit.createInventory(null, 54, title.color())
    private val map = HashMap<Int, GuiButton>()

    init {
        repeat(55) {
            setButton(it - 1, fill)
        }
        if (moneyStatus) {
            val lore = if (moneyStatusClick) SmsShopPlugin.instance.language.get("clickToTopUp").split("\n") else emptyList()
            val button = BasicGuiButton(Material.GOLD_INGOT, 0,"&e&l${account.money}", lore)
            setButton(49, button)
        }
        if (transactions) {
            val name = SmsShopPlugin.instance.language.get("transactions")
            val button = BasicGuiButton(Material.BOOK, 0, name.color(), action = UserHistoryAction(account))
            setButton(50, button)
        }
    }

    final override fun setButton(slot: Int, item: GuiButton) {
        if (slot > 54 || slot < 0) return
        map[slot] = item
        inventory.setItem(slot, item.getItem())
    }

    final override fun getButton(slot: Int): GuiButton? = map[slot]

    final override fun open(player: Player) {
        val inv = Bukkit.createInventory(player, 54, title.color())
        for ((index, content) in inventory.contents.toList().withIndex()) {
            inv.setItem(index, content)
        }
        player.openInventory(inv)
        SmsShopPlugin.instance.guiManager.add(player, this)
    }

}