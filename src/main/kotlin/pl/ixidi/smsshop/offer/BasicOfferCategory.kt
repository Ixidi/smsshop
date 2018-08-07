package pl.ixidi.smsshop.offer

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import pl.ixidi.smsshop.api.gui.GuiButton
import pl.ixidi.smsshop.api.offer.OfferCategory
import pl.ixidi.smsshop.gui.base.BasicGuiButton

data class BasicOfferCategory(
        override val name: String,
        override val title: String,
        override val lore: String,
        override val guiSlot: Int,
        override val material: Material,
        override val durability: Short = 0
) : OfferCategory {

    override fun key(): String = name.toLowerCase()

}