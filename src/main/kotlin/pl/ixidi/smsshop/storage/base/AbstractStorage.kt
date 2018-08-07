package pl.ixidi.smsshop.storage.base

import pl.ixidi.smsshop.api.storage.Storable
import pl.ixidi.smsshop.api.storage.Storage

abstract class AbstractStorage<K, V : Storable<K>> : Storage<K, V> {

    private val map = HashMap<K, V>()

    final override fun add(value: V) {
        map[value.key()] = value
    }

    final override fun get(key: K, ifNull: () -> V?): V? = map[key]

    final override fun remove(key: K) {
        map.remove(key)
    }

    final override fun getAll(): List<V> = map.values.toList()

    final override fun clear() {
        map.clear()
    }
}