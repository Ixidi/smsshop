package pl.ixidi.smsshop.account

import pl.ixidi.smsshop.api.Account
import pl.ixidi.smsshop.api.log.Log
import pl.ixidi.smsshop.api.offer.Offer
import java.util.*
import kotlin.collections.ArrayList

class BasicAccount(override val uuid: UUID, override val name: String, override var money: Long = 0) : Account {

    private val logs = ArrayList<Log>()

    override fun addLog(log: Log) {
        logs.add(log)
    }

    override fun logs(): List<Log> = logs.toList()

    override fun addMoney(money: Long) {
        this.money += money
        if (this.money < 0) this.money = 0
    }

    override fun subtractMoney(money: Long) {
        this.money -= money
        if (this.money < 0) this.money = 0
    }

    override fun hasMoney(money: Long): Boolean = this.money >= money

    override fun canBuy(offer: Offer): Boolean = hasMoney(offer.price)

    override fun key(): UUID {
        return uuid
    }

}