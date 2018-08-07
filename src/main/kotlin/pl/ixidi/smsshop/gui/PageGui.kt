package pl.ixidi.smsshop.gui

import org.bukkit.Material
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.gui.action.SwitchAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class PageGui(private val account: Account,
              private val page: Int,
              private val previus: Gui,
              private val list: List<GuiButton>
) : AbstractGui("#$page") {

    override fun reload() {
        setButton(45, BasicGuiButton(Material.STICK, "&f&l<--", action = SwitchAction(previus)))

        val buttons = ArrayList<GuiButton>()
        for ((index, button) in list.withIndex()) {
            if (index > 44) {
                buttons.add(button)
                continue
            }
            setButton(index, button)
        }
        if (buttons.isEmpty()) return
        val pageGui = PageGui(account, page + 1, this, buttons)
        setButton(53, BasicGuiButton(Material.STICK, "&f&l-->", action = SwitchAction(pageGui)))
    }

}