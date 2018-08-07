package pl.ixidi.smsshop.api.log

import pl.ixidi.smsshop.SmsShopPlugin

enum class LogType {

    ORDER,
    TOPPING_UP,
    ADMIN;

    companion object {
        fun match(string: String): LogType {
            val list = LogType.values().filter { it.name.toLowerCase() == string.toLowerCase() }
            if (list.isEmpty())
                return ADMIN
            return list.first()
        }
    }

    fun langName(): String {
        return SmsShopPlugin.instance.language.get("words.${name.toLowerCase()}")
    }
}