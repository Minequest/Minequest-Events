package com.theminequest.MQCoreEvents.BlockEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class BlockEvent extends QEvent {
	
	private long timetowait;
	private long starttime;
	private int X;
	private int Y;
	private int Z;
	private int type;

	public BlockEvent(long q, int e, String details) {
		super(q, e, details);
	}

	/*
	 * [0] delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] Type ID
	 */
	@Override
	public void parseDetails(String[] details) {
		// TODO Auto-generated method stub
		timetowait = Long.parseLong(details[0]);
		X = Integer.parseInt(details[1]);
		Y = Integer.parseInt(details[2]);
		Z = Integer.parseInt(details[3]);
		type = Integer.parseInt(details[4]);
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		long curtime = System.currentTimeMillis();
		if (curtime-starttime>=timetowait)
			return true;
		return false;
	}

	@Override
	public CompleteStatus action() {
		World w = Bukkit.getWorld(MineQuest.questManager.getQuest(getQuestId()).world);
		Location l = new Location(w,X,Y,Z);
		Block b = l.getBlock();
		boolean status = b.setTypeId(type);
		if (status)
			return CompleteStatus.SUCCESS;
		return CompleteStatus.FAILURE;
	}

}
