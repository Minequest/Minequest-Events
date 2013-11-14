package com.theminequest.events.block;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.QuestEvent;
import com.theminequest.bukkit.util.ItemUtils;
import com.theminequest.doc.DocArgType;
import com.theminequest.doc.V1Documentation;

@V1Documentation(
		type = "Event",
		ident = "BlockDCEvent",
		description = "Destroy a block, then create it.",
		arguments = { "Initial Delay", "Delay after Destruction", "X", "Y", "Z", "Type" },
		typeArguments = { DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.STRING }
		)
public class BlockDCEvent extends QuestEvent {

	private long firstdelay;
	private long seconddelay;
	private Location loc;
	private Material typeid;
	private long starttime;
	private int step;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * Destroy, then create
	 * [0]: first delay in MS before destroy
	 * [1]: second delay is MS after destroy before create
	 * [2]: X
	 * [3]: Y
	 * [4]: Z
	 * [5]: Type ID 
	 */
	@Override
	public void setupArguments(String[] details) {
		firstdelay = Long.parseLong(details[0]);
		seconddelay = Long.parseLong(details[1]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		int x = Integer.parseInt(details[2]);
		int y = Integer.parseInt(details[3]);
		int z = Integer.parseInt(details[4]);
		loc = new Location(w,x,y,z);
		typeid = ItemUtils.getMaterial(details[5]);
		step = 0;
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if (step==0){
			if (starttime+firstdelay<=System.currentTimeMillis()){
				loc.getBlock().setType(Material.AIR);
				step++;
				starttime = System.currentTimeMillis();
			}
			return false;
		}
		if (starttime+seconddelay<=System.currentTimeMillis()){
			return true;
		}
		return false;
	}

	@Override
	public CompleteStatus action() {
		loc.getBlock().setType(typeid);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
