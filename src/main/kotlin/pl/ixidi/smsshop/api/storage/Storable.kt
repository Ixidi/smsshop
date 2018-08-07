package pl.ixidi.smsshop.api.storage

interface Storable<K> {

    fun key() : K

}