package pl.ixidi.smsshop.storage

import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.log.LogType
import pl.ixidi.smsshop.account.BasicAccount
import pl.ixidi.smsshop.account.BasicLog
import pl.ixidi.smsshop.extension.isInvalid
import pl.ixidi.smsshop.extension.markInvalid
import pl.ixidi.smsshop.extension.validString
import pl.ixidi.smsshop.storage.base.SingleObjectStorage
import pl.ixidi.smsshop.util.FileYamlConfiguration
import java.io.File
import java.util.*

class AccountStorage(folder: File) : SingleObjectStorage<UUID, Account>(folder) {
    
    override fun onSave(value: Account, yaml: FileYamlConfiguration) {
        yaml.set("name", value.name)
        yaml.set("uuid", value.uuid.toString())
        yaml.set("money", value.money)

        val logs = value.logs()
        if (logs.isEmpty())
            return

        for ((index, log) in logs.withIndex()) {
            yaml.set("logs.$index.type", log.type.name)
            log.properties().forEach { k, v ->
                yaml.set("logs.$index.properties.$k", v)
            }
        }
    }

    override fun onLoad(yaml: FileYamlConfiguration): Account? {
        if (yaml.file.isInvalid()) return null

        val account = try {
            val uuid = UUID.fromString(yaml.validString("uuid"))
            val name = yaml.validString("name")
            val money = yaml.getLong("money")
            BasicAccount(uuid, name, money)
        } catch (ex: Exception) {
            ex.printStackTrace()
            yaml.file.markInvalid()
            return null
        }

        val parentSection = yaml.getConfigurationSection("logs") ?: return account
        val parentKeys = parentSection.getKeys(false)
        mainLoop@ for (parentKey in parentKeys) {
            val logSection = parentSection.getConfigurationSection(parentKey)!!

            try {
                val type = LogType.match(logSection.validString("type"))
                val log = BasicLog(type)

                val propertiesSection = logSection.getConfigurationSection("properties") ?: continue@mainLoop
                val propertiesKeys = propertiesSection.getKeys(false)
                propertyLoop@ for (propertiesKey in propertiesKeys) {
                    try {
                        val value = propertiesSection.validString(propertiesKey)
                        log.addProperty(propertiesKey, value)
                    } catch (ex: Exception) {
                        continue@propertyLoop
                    }
                }

                account.addLog(log)
            } catch (ex: Exception) {
                continue@mainLoop
            }
        }

        return account
    }

    fun getByName(name: String): Account? = getAll().filter { it.name == name }.getOrNull(0)


}