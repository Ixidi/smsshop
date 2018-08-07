package pl.ixidi.smsshop

import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.ReloadType
import pl.ixidi.smsshop.api.storage.Storage
import pl.ixidi.smsshop.command.SmsShopCommand
import pl.ixidi.smsshop.listener.PlayerJoinListener
import pl.ixidi.smsshop.settings.Language
import pl.ixidi.smsshop.settings.Settings
import pl.ixidi.smsshop.storage.AccountStorage
import pl.ixidi.smsshop.util.FileYamlConfiguration
import java.io.File
import java.util.*

object SmsShopPlugin : JavaPlugin() {

    lateinit var settings: Settings
    private set

    lateinit var language: Language
    private set

    lateinit var accountStorage: Storage<UUID, Account>
    private set

    override fun onEnable() {
        settings = Settings(FileYamlConfiguration(copyResource("config.yml")))
        language = Language(settings.getString("lang", "PL"))

        accountStorage = AccountStorage(File(dataFolder, "accounts"))
        accountStorage.load()

        loadListeners(listOf(
            PlayerJoinListener()
        ))

        loadCommands(mapOf(
                "smsshop" to SmsShopCommand()
        ))
    }

    override fun onDisable() {
        accountStorage.save()
    }

    private fun copyResource(name: String): File {
        val file = File(dataFolder, name)
        saveResource(name, false)
        return file
    }

    private fun loadListeners(listeners: List<Listener>) {
        listeners.forEach { server.pluginManager.registerEvents(it, this) }
    }

    private fun loadCommands(commandExecutors: Map<String, CommandExecutor>) {
        commandExecutors.forEach { getCommand(it.key).executor = it.value }
    }

    fun reload(type: ReloadType) {
        when (type) {
            ReloadType.SETTINGS -> settings.reload()
            ReloadType.LANGUAGE -> {
                copyResource("language_PL.yml")
                language.reload()
            }
            ReloadType.BOTH -> {
                settings.reload()
                copyResource("language_PL.yml")
                language = Language(settings.getString("lang", "PL"))
            }
        }
    }

}