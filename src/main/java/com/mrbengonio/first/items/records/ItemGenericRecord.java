package com.mrbengonio.first.items.records;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;
import com.mrbengonio.first.items.ItemBase;

import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGenericRecord extends ItemBase {
	private static final Map<SoundEvent, ItemGenericRecord> RECORDS = Maps.<SoundEvent, ItemGenericRecord>newHashMap();
	private final SoundEvent sound;

	public ItemGenericRecord(String name, CreativeTabs tab, SoundEvent song) {
		super(name, tab, 1);
		this.sound = song;
		RECORDS.put(this.sound, this);
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);

		if (iblockstate.getBlock() == Blocks.JUKEBOX
				&& !((Boolean) iblockstate.getValue(BlockJukebox.HAS_RECORD)).booleanValue()) {
			if (!worldIn.isRemote) {
				ItemStack itemstack = player.getHeldItem(hand);
				((BlockJukebox) Blocks.JUKEBOX).insertRecord(worldIn, pos, iblockstate, itemstack);
				worldIn.playEvent((EntityPlayer) null, 1010, pos, Item.getIdFromItem(this));
				itemstack.shrink(1);
				player.addStat(StatList.RECORD_PLAYED);
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.PASS;
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(this.getRecordNameLocal());
	}

	@SideOnly(Side.CLIENT)
	public String getRecordNameLocal() {
		return I18n.translateToLocal(this.getUnlocalizedName());
	}

	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	@Nullable
	@SideOnly(Side.CLIENT)
	public static ItemGenericRecord getBySound(SoundEvent soundIn) {
		return (ItemGenericRecord) RECORDS.get(soundIn);
	}

	@SideOnly(Side.CLIENT)
	public SoundEvent getSound() {
		return this.sound;
	}

}
