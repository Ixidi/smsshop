package pl.ixidi.smsshop

import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.ReloadType
import pl.ixidi.smsshop.api.gui.GuiManager
import pl.ixidi.smsshop.api.offer.Offer
import pl.ixidi.smsshop.api.offer.OfferCategory
import pl.ixidi.smsshop.api.storage.Storage
import pl.ixidi.smsshop.command.ShopCommand
import pl.ixidi.smsshop.command.SmsShopCommand
import pl.ixidi.smsshop.gui.base.BasicGuiManager
import pl.ixidi.smsshop.listener.GuiListener
import pl.ixidi.smsshop.listener.PlayerJoinListener
import pl.ixidi.smsshop.settings.Language
import pl.ixidi.smsshop.settings.Settings
import pl.ixidi.smsshop.storage.AccountStorage
import pl.ixidi.smsshop.storage.OfferCategoryStorage
import pl.ixidi.smsshop.storage.OfferStorage
import pl.ixidi.smsshop.util.FileYamlConfiguration
import java.io.File
import java.util.*

class SmsShopPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: SmsShopPlugin
        private set
    }

    init {
        instance = this
    }

    lateinit var settings: Settings
    private set

    lateinit var language: Language
    private set

    lateinit var categoryStorage: Storage<String, OfferCategory>
    private set

    lateinit var offerStorage: Storage<Int, Offer>
    private set

    lateinit var accountStorage: Storage<UUID, Account>
    private set

    lateinit var guiManager: GuiManager
    private set

    override fun onEnable() {
        if (!dataFolder.exists()) dataFolder.mkdirs()

        settings = Settings(FileYamlConfiguration(copyResource("config.yml")))
        copyResource("language_PL.yml")
        language = Language(settings.getString("lang", "PL"))

        categoryStorage = OfferCategoryStorage(copyResource("categories.yml"))
        categoryStorage.load()

        offerStorage = OfferStorage(copyResource("offers.yml"))
        offerStorage.load()

        accountStorage = AccountStorage(File(dataFolder, "accounts"))
        accountStorage.load()

        guiManager = BasicGuiManager()

        loadListeners(listOf(
            PlayerJoinListener(),
            GuiListener()
        ))

        loadCommands(mapOf(
                "smsshop" to SmsShopCommand(),
                "shop" to ShopCommand()
        ))
    }

    override fun onDisable() {
        accountStorage.save()
    }

    private fun copyResource(name: String): File {
        val file = File(dataFolder, name)
        if (!file.exists()) saveResource(name, true)
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
                language.reload()
            }

            ReloadType.OFFERS -> {
                categoryStorage.load()
                offerStorage.load()
            }

            ReloadType.ALL -> {
                settings.reload()
                language = Language(settings.getString("lang", "PL"))
                categoryStorage.load()
                offerStorage.load()
            }
        }
    }

}