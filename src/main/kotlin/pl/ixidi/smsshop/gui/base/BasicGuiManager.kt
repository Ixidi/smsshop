package pl.ixidi.smsshop.gui.base

import org.bukkit.entity.Player
import pl.ixidi.smsshop.api.gui.Gui
import pl.ixidi.smsshop.api.gui.GuiManager
import java.util.*

class BasicGuiManager : GuiManager {

    private val map = HashMap<UUID, Gui>()

    override fun getCurrent(player: Player): Gui? = map[player.uniqueId]

    override fun add(player: Player, gui: Gui) {
        map[player.uniqueId] = gui
    }

    override fun remove(player: Player) {
        map.remove(player.uniqueId)
    }

}