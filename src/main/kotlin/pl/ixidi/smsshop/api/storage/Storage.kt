package pl.ixidi.smsshop.api.storage

interface Storage<K, V : Storable<K>> {

    fun save()

    fun load()

    fun add(value: V)

    fun get(key: K, ifNull: () -> V? = {null}): V?

    fun getAll(): List<V>

    fun remove(key: K)

    fun clear()

}