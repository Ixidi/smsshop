package pl.ixidi.smsshop.settings

import org.bukkit.configuration.Configuration
import pl.ixidi.smsshop.util.FileYamlConfiguration

class Settings(private val yaml: FileYamlConfiguration) : Configuration by yaml {

    init {
        reload()
    }

    fun reload() {
        yaml.load()
    }

    fun save() {
        yaml.save()
    }
}