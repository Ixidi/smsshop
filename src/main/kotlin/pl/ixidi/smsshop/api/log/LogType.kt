package pl.ixidi.smsshop.api.log

import pl.ixidi.smsshop.SmsShopPlugin

enum class LogType {

    ORDER,
    TOPPING_UP,
    OTHER;

    companion object {
        fun match(string: String): LogType {
            val list = LogType.values().filter { it.name.toLowerCase() == string.toLowerCase() }
            if (list.isEmpty())
                return OTHER
            return list.first()
        }
    }

    fun langName(): String {
        return SmsShopPlugin.instance.language.get("words.${name.toLowerCase()}")
    }
}