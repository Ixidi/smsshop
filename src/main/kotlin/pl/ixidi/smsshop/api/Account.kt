package pl.ixidi.smsshop.api

import pl.ixidi.smsshop.api.log.Log
import pl.ixidi.smsshop.api.storage.Storable
import java.util.*

interface Account : Storable<UUID> {

    val uuid: UUID

    val name: String

    var money: Long

    fun addMoney(money: Long)

    fun subtractMoney(money: Long)

    fun hasMoney(money: Long): Boolean

    fun addLog(log: Log)

    fun logs(): List<Log>


}