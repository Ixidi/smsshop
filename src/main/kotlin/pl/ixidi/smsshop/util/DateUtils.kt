package pl.ixidi.smsshop.util

import pl.ixidi.smsshop.SmsShopPlugin
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun now(): String {
        val format = SimpleDateFormat(SmsShopPlugin.instance.settings.getString("dateFormat", "dd.MM.yyyy HH:mm"))
        return format.format(Date())
    }

}