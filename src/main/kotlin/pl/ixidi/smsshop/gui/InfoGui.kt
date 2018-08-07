package pl.ixidi.smsshop.gui

import org.bukkit.Material
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.api.log.LogType
import pl.ixidi.smsshop.extension.color
import pl.ixidi.smsshop.gui.action.SwitchAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class InfoGui(
        account: Account,
        private val previous: Gui? = null,
        moneyStatusClick: Boolean = true
) : AbstractGui("${account.name} #1", account, moneyStatusClick = moneyStatusClick, transactions = false) {

    override fun reload() {
        setButton(0, BasicGuiButton(Material.SKULL_ITEM, 3,"&f${account.uuid}"))

        if (previous != null) {
            setButton(45, BasicGuiButton(Material.STICK, 0, "&f&l<--", action = SwitchAction(previous)))
        }

        val lang = SmsShopPlugin.instance.language
        val buttons = ArrayList<GuiButton>()
        for ((index, log) in account.logs().reversed().withIndex()) {
            val list = ArrayList<String>()
            list.add("&6${lang.get("words.type")}: &7${log.type.langName()}".color())
            log.properties().forEach { k, v ->
                list.add("&6${lang.get("words.$k")}: &7${v.toLongOrNull() ?: lang.getOrNull("words.$v") ?: v}".color())
            }
            val button = BasicGuiButton(Material.PAPER, 0,"&7&l#${account.logs().size - index}", list)
            if (index > 43) {
                buttons.add(button)
                continue
            }
            setButton(index + 1, button)
        }

        if (buttons.isEmpty()) return
        val pageGui = PageGui(account, 2, this, buttons, false, transactions = false)
        setButton(53, BasicGuiButton(Material.STICK, 0, "&f&l-->", action = SwitchAction(pageGui)))
    }

}