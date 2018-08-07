package pl.ixidi.smsshop.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.account.BasicAccount

class PlayerJoinListener : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        if (SmsShopPlugin.instance.accountStorage.get(player.uniqueId) == null) {
            SmsShopPlugin.instance.accountStorage.add(BasicAccount(player.uniqueId, player.name))
        }
    }

}