package pl.ixidi.smsshop

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.storage.Storage
import pl.ixidi.smsshop.storage.AccountStorage
import java.io.File
import java.util.*

object SmsShopPlugin : JavaPlugin() {

    lateinit var accountStorage: Storage<UUID, Account>
    private set

    override fun onEnable() {
        accountStorage = AccountStorage(File(dataFolder, "accounts"))
        accountStorage.load()

        loadListeners(listOf(

        ))
    }

    override fun onDisable() {
        accountStorage.save()
    }

    private fun loadListeners(listeners: List<Listener>) {
        listeners.forEach { server.pluginManager.registerEvents(it, this) }
    }

}