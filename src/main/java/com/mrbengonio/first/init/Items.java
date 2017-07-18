package com.mrbengonio.first.init;

import com.mrbengonio.first.handlers.CreativeTabsHandler;
import com.mrbengonio.first.items.ItemGenericItem;

import net.minecraft.item.Item;

public class Items {

	// Register Items
	public static final Item LukasItem = new ItemGenericItem("lukas_item", CreativeTabsHandler.tabKreuthil, 64);
	public static final Item testItem = new ItemGenericItem("test_item", CreativeTabsHandler.tabKreuthil, 64);
}