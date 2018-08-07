package pl.ixidi.smsshop.api.offer

import org.bukkit.Material
import pl.ixidi.smsshop.api.storage.Storable

interface OfferCategory : Storable<String> {

    val name: String

    val title: String

    val lore: String

    val guiSlot: Int

    val material: Material

    val durability: Short
}