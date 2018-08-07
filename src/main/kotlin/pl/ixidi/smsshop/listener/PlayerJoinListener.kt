package pl.ixidi.smsshop.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.base.BasicAccount

class PlayerJoinListener : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        SmsShopPlugin.accountStorage.get(player.uniqueId) {
            val account = BasicAccount(player.uniqueId, player.name)
            SmsShopPlugin.accountStorage.add(account)
            account
        }
    }

}