package pl.ixidi.smsshop.settings

import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.extension.parseArgs
import pl.ixidi.smsshop.util.FileYamlConfiguration
import java.io.File

class Language(lang: String) {

    private val file: File = File(SmsShopPlugin.instance.dataFolder, "language_$lang.yml")
    private val yaml = FileYamlConfiguration(file)

    init {
        reload()
    }

    fun get(key: String, args: Map<String, String>): String = get(key).parseArgs(args)

    fun get(key: String): String = yaml.getString(key, key)

    fun getOrNull(key: String): String? = yaml.getString(key)

    fun reload() {
        yaml.load()
    }

    fun save() {
        yaml.save()
    }


}