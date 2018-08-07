package pl.ixidi.smsshop.extension

import java.io.File

fun File.markInvalid() {
    this.renameTo(File(this.parentFile, "invalid${System.currentTimeMillis()}_${this.name}"))
}

fun File.isInvalid(): Boolean = this.name.startsWith("invalid")