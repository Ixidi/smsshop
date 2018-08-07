package pl.ixidi.smsshop.gui

import org.bukkit.Material
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.extension.color
import pl.ixidi.smsshop.gui.action.SwitchAction
import pl.ixidi.smsshop.gui.base.AbstractGui
import pl.ixidi.smsshop.gui.base.BasicGuiButton

class InfoGui(private val account: Account) : AbstractGui("${account.name} #1") {

    override fun reload() {
        val symbol = SmsShopPlugin.instance.settings.getString("moneySymbol", "$")
        setButton(0, BasicGuiButton(Material.GOLD_INGOT, "&e&l${account.money}$symbol"))
        setButton(1, BasicGuiButton(Material.SKULL_ITEM, "&f${account.uuid}"))

        val lang = SmsShopPlugin.instance.language
        val buttons = ArrayList<GuiButton>()
        for ((index, log) in account.logs().reversed().withIndex()) {
            val list = ArrayList<String>()
            list.add("&6${lang.get("words.type")}: &7${log.type.langName()}".color())
            log.properties().forEach { k, v ->
                list.add("&6${lang.get("words.$k")}: &7${v.toLongOrNull() ?: lang.getOrNull("words.$v") ?: v}".color())
            }
            val button = BasicGuiButton(Material.PAPER, "&7&l#${account.logs().size - index}", list)
            if (index > 42) {
                buttons.add(button)
                continue
            }
            setButton(index + 2, button)
        }

        if (buttons.isEmpty()) return
        val pageGui = PageGui(account, 2, this, buttons)
        setButton(53, BasicGuiButton(Material.STICK, "&f&l-->", action = SwitchAction(pageGui)))
    }

}