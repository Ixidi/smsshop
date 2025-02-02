package pl.ixidi.smsshop.util

import com.google.common.io.Files
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class FileYamlConfiguration(val file: File) : YamlConfiguration() {

    init {
        load()
    }

    fun save() {
        save(file)
    }

    fun load() {
        if (!file.exists()) {
            Files.createParentDirs(file)
            file.createNewFile()
        }
        load(file)
    }

}