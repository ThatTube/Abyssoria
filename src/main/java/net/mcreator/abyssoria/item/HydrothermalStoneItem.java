
package net.mcreator.abyssoria.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class HydrothermalStoneItem extends Item {
	public HydrothermalStoneItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.UNCOMMON));
	}
}
