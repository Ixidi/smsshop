package pl.ixidi.smsshop.api.log

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

}