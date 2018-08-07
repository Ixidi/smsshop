package pl.ixidi.smsshop.base.offer

import org.bukkit.Material
import pl.ixidi.smsshop.api.offer.Offer
import pl.ixidi.smsshop.api.offer.OfferCategory

data class BasicOffer(
        override val id: Int,
        override val title: String,
        override val lore: String,
        override val category: OfferCategory,
        override val material: Material,
        override val price: Long,
        override val commands: List<String>
) : Offer {
    override fun key(): Int = id
}