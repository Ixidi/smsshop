package pl.ixidi.smsshop.api.offer

import org.bukkit.Material
import pl.ixidi.smsshop.api.storage.Storable

interface Offer : Storable<Int> {

    val title: String

    val lore: String

    val category: OfferCategory

    val material: Material

    val price: Long

    val commands: List<String>

    val id: Int

}