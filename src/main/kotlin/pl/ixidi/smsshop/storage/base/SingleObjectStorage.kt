package pl.ixidi.smsshop.storage.base

import pl.ixidi.smsshop.api.storage.Storable
import pl.ixidi.smsshop.extension.invalid
import pl.ixidi.smsshop.util.FileYamlConfiguration
import java.io.File
import java.nio.file.NotDirectoryException

abstract class SingleObjectStorage<K, V : Storable<K>>(private val folder: File) : AbstractStorage<K, V>() {

    init {
        folder.mkdirs()
        if (!folder.isDirectory) throw NotDirectoryException("${folder.absolutePath} is not directory!")
    }

    final override fun save() {
        getAll().forEach {
            val yaml = prepareYaml(it)
            onSave(it, yaml)
            yaml.save()
        }
    }

    final override fun load() {
        val list = folder.listFiles()
        if (list.isEmpty()) return
        for (file in list) {
            if (file.name.startsWith("invalid")) continue
            if (!file.name.endsWith(".yml")) {
                file.invalid()
                continue
            }
            val yaml = prepareYaml(file)
            val value = onLoad(yaml)
            if (value != null) add(value)
        }
    }

    private fun prepareYaml(file: File): FileYamlConfiguration = FileYamlConfiguration(file)

    private fun prepareYaml(value: V): FileYamlConfiguration = prepareYaml(File(folder, "${value.key().toString()}.yml"))

    abstract fun onSave(value: V, yaml: FileYamlConfiguration)

    abstract fun onLoad(yaml: FileYamlConfiguration): V?

}