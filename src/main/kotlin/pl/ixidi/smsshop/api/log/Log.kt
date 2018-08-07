package pl.ixidi.smsshop.api.log

interface Log {

    val type: LogType

    fun addProperty(key: String, value: String)

    fun properties() : Map<String, String>

}