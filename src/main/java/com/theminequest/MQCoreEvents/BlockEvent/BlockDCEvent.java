package com.theminequest.MQCoreEvents.BlockEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.theminequest.MineQuest.CompleteStatus;
import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class BlockDCEvent extends QEvent {

	private long firstdelay;
	private long seconddelay;
	private Location loc;
	private int typeid;
	private long starttime;
	private int step;

	public BlockDCEvent(long q, int e, String details) {
		super(q, e, details);
	}

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.EventsAPI.QEvent#parseDetails(java.lang.String[])
	 * Destroy, then create
	 * [0]: first delay in MS before destroy
	 * [1]: second delay is MS after destroy before create
	 * [2]: X
	 * [3]: Y
	 * [4]: Z
	 * [5]: Type ID 
	 */
	@Override
	public void parseDetails(String[] details) {
		firstdelay = Long.parseLong(details[0]);
		seconddelay = Long.parseLong(details[1]);
		World W = Bukkit.getWorld(MineQuest.questManager.getQuest(getQuestId()).details.world);
		int X = Integer.parseInt(details[2]);
		int Y = Integer.parseInt(details[3]);
		int Z = Integer.parseInt(details[4]);
		loc = new Location(W,X,Y,Z);
		typeid = Integer.parseInt(details[5]);
		step = 0;
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if (step==0){
			if (starttime+firstdelay<=System.currentTimeMillis()){
				loc.getBlock().setTypeId(0);
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
		boolean result = loc.getBlock().setTypeId(typeid);
		if (result)
			return CompleteStatus.SUCCESS;
		else
			return CompleteStatus.FAILURE;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
