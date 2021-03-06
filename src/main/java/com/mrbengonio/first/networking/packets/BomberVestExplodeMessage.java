package com.mrbengonio.first.networking.packets;

import com.mrbengonio.first.First;
import com.mrbengonio.first.init.ModItems;
import com.mrbengonio.first.networking.AbstractServerMessageHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BomberVestExplodeMessage implements IMessage {

	// The basic, no-argument constructor MUST be included to use the new automated
	// handling
	public BomberVestExplodeMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {

	}

	@Override
	public void toBytes(ByteBuf buf) {

	}

	public static class Handler extends AbstractServerMessageHandler<BomberVestExplodeMessage> {

		@Override
		public IMessage handleServerMessage(EntityPlayer player, BomberVestExplodeMessage message, MessageContext ctx) {

			final EntityPlayer threadedplayer = player;
			player.getServer().addScheduledTask(new Runnable() {

				public void run() {
					if (threadedplayer.inventory.getStackInSlot(38) != null && threadedplayer.inventory
							.getStackInSlot(38).getItem() == ModItems.ITEMS.get("chestplate_explosive")) {
						if (!threadedplayer.isCreative())
							threadedplayer.inventory.removeStackFromSlot(38);
						threadedplayer.getEntityWorld().createExplosion(null, threadedplayer.posX, threadedplayer.posY,
								threadedplayer.posZ, 6F, true);
					} else
						First.LOGGER.warn(threadedplayer.getName()
								+ " tried to send a bombervest explosion packet without a vest! Either a hacker or a bug!");
				}
			});

			return null;
		}

	}
}
