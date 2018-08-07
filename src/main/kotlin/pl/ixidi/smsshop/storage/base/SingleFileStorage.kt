package pl.ixidi.smsshop.storage.base

import org.bukkit.configuration.ConfigurationSection
import pl.ixidi.smsshop.api.storage.Storable
import pl.ixidi.smsshop.util.FileYamlConfiguration
import java.io.File

abstract class SingleFileStorage<K, V : Storable<K>>(file: File) : AbstractStorage<K, V>() {

    private val yaml = FileYamlConfiguration(file)

    override fun save() {
        for ((index, value) in getAll().withIndex()) {
            onSave(value, yaml.createSection(index.toString()))
        }
        yaml.save()
    }

    override fun load() {
        yaml.load()
        val keys = yaml.getKeys(false)
        if (keys.isEmpty()) return
        for (key in keys) {
            val section = yaml.getConfigurationSection(key)
            val value = onLoad(section)
            if (value != null) add(value)
        }
        if (getAll().isEmpty()) ifEmptyAfterLoad()
    }

    abstract fun onSave(value: V, section: ConfigurationSection)

    abstract fun onLoad(section: ConfigurationSection): V?

    protected open fun ifEmptyAfterLoad() {}
}