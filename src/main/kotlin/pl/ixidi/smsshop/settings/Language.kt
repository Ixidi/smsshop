package pl.ixidi.smsshop.settings

import pl.ixidi.smsshop.SmsShopPlugin
import pl.ixidi.smsshop.util.FileYamlConfiguration
import java.io.File

class Language(lang: String) {

    private val file: File = File(SmsShopPlugin.dataFolder, "language_$lang.yml")
    private val yaml = FileYamlConfiguration(file)

    init {
        reload()
    }

    fun get(key: String, args: Map<String, String>): String {
        var langString = yaml.getString(key, "$key $args")
        args.forEach {
            langString = langString.replace("$%${it.key}%^", it.value)
        }
        return langString
    }

    fun reload() {
        yaml.load()
    }

    fun save() {
        yaml.save()
    }


}