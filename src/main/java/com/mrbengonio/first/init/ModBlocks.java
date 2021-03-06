package com.mrbengonio.first.init;

import java.util.HashMap;
import java.util.Map;

import com.mrbengonio.first.blocks.BlockGenericBlock;
import com.mrbengonio.first.handlers.CreativeTabsHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final Map<String, Block> BLOCKS = new HashMap<String, Block>() {
		{
			// Ore
			put("ore_cupalite", new BlockGenericBlock("cupalite_ore", Material.ROCK, CreativeTabsHandler.tabKreuthil,
					6F, 50F, "pickaxe", 4));
			put("ore_saspum", new BlockGenericBlock("saspum_ore", Material.ROCK, CreativeTabsHandler.tabKreuthil, 6F,
					50F, "pickaxe", 4));
			put("ore_kreuthil", new BlockGenericBlock("kreuthil_ore", Material.ROCK, CreativeTabsHandler.tabKreuthil,
					6F, 50F, "pickaxe", 4));
			put("ore_xasmoninium", new BlockGenericBlock("xasmoninium_ore", Material.ROCK,
					CreativeTabsHandler.tabKreuthil, 6F, 50F, "pickaxe", 4));
		}
	};

}
