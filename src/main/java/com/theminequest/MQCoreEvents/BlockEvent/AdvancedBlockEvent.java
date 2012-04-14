package com.theminequest.MQCoreEvents.BlockEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class AdvancedBlockEvent extends QEvent {
	
	private long delay;
	private Location loc;
	private int typeid;
	private byte damage;
	private long starttime;

	public AdvancedBlockEvent(long q, int e, String details) {
		super(q, e, details);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.EventsAPI.QEvent#parseDetails(java.lang.String[])
	 * [0] delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] TypeID
	 * [5] Damage Value
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		World W = Bukkit.getWorld(MineQuest.questManager.getQuest(getQuestId()).world);
		int X = Integer.parseInt(details[1]);
		int Y = Integer.parseInt(details[2]);
		int Z = Integer.parseInt(details[3]);
		loc = new Location(W,X,Y,Z);
		typeid = Integer.parseInt(details[4]);
		damage = Byte.parseByte(details[5]);
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if ((System.currentTimeMillis()-starttime)>=delay)
			return false;
		return true;
	}

	@Override
	public CompleteStatus action() {
		boolean result = loc.getBlock().setTypeIdAndData(typeid, damage, true);
		if (result)
			return CompleteStatus.SUCCESS;
		return CompleteStatus.FAILURE;
	}

}
