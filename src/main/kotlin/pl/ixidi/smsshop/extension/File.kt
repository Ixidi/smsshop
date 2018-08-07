package pl.ixidi.smsshop.extension

import java.io.File

fun File.invalid() {
    this.renameTo(File(this.parentFile, "invalid${System.currentTimeMillis()}_${this.name}"))
}