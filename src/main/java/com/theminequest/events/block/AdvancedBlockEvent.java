package com.theminequest.events.block;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;
import com.theminequest.bukkit.util.ItemUtils;
import com.theminequest.doc.DocArgType;
import com.theminequest.doc.V1Documentation;

@V1Documentation(
		type = "Event",
		ident = "AdvancedBlockEvent",
		description = "Set a block at a specified location.",
		arguments = { "Delay", "X", "Y", "Z", "Block", "Damage" },
		typeArguments = { DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.STRING, DocArgType.INT }
		)
public class AdvancedBlockEvent extends DelayedQuestEvent {
	
	private long delay;
	private Location loc;
	private Material material;
	private byte damage;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] TypeID
	 * [5] Damage Value
	 */
	@Override
	public void setupArguments(String[] details) {
		delay = Long.parseLong(details[0]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World W = Bukkit.getWorld(worldname);
		int X = Integer.parseInt(details[1]);
		int Y = Integer.parseInt(details[2]);
		int Z = Integer.parseInt(details[3]);
		loc = new Location(W,X,Y,Z);
		material = ItemUtils.getMaterial(details[4]);
		damage = Byte.parseByte(details[5]);
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public CompleteStatus action() {
		boolean result = loc.getBlock().setTypeIdAndData(material.getId(), damage, true);
		if (result)
			return CompleteStatus.SUCCESS;
		return CompleteStatus.WARNING;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

	@Override
	public long getDelay() {
		return delay;
	}

}
