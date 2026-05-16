
package net.mcreator.abyssoria.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class MoltenRockItem extends Item {
	public MoltenRockItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
