package com.theminequest.events.block;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;
import com.theminequest.bukkit.util.ItemUtils;
import com.theminequest.doc.DocArgType;
import com.theminequest.doc.V1Documentation;

@V1Documentation(
		type = "Event",
		ident = "BlockEvent",
		description = "Set a block at location.",
		arguments = { "Delay", "X", "Y", "Z", "Block" },
		typeArguments = { DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.STRING }
		)
public class BlockEvent extends DelayedQuestEvent {
	
	private long delay;
	private int X;
	private int Y;
	private int Z;
	private Material material;
	
	/*
	 * [0] delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] Type ID
	 */
	@Override
	public void setupArguments(String[] details) {
		delay = Long.parseLong(details[0]);
		X = Integer.parseInt(details[1]);
		Y = Integer.parseInt(details[2]);
		Z = Integer.parseInt(details[3]);
		material = ItemUtils.getMaterial(details[4]);
	}
	
	@Override
	public boolean delayedConditions() {
		return true;
	}
	
	@Override
	public CompleteStatus action() {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		Location l = new Location(w, X, Y, Z);
		Block b = l.getBlock();
		if (material != null) {
			b.setType(material);
			return CompleteStatus.SUCCESS;
		}
		return CompleteStatus.WARNING;
	}
	
	@Override
	public long getDelay() {
		return delay;
	}
	
	@Override
	public Integer switchTask() {
		return null;
	}
}
