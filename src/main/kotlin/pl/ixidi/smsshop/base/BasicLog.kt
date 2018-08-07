package pl.ixidi.smsshop.base

import pl.ixidi.smsshop.api.log.Log
import pl.ixidi.smsshop.api.log.LogType

class BasicLog(override val type: LogType) : Log {

    private val map = HashMap<String, String>()

    override fun addProperty(key: String, value: String) {
        map[key] = value
    }

    override fun properties(): Map<String, String> = map.toMap()

}