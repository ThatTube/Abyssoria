
package net.mcreator.abyssoria.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class CoralShardItem extends Item {
	public CoralShardItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
