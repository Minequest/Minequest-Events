package com.theminequest.MQCoreEvents.BlockEvent;

import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class BlockEvent extends QEvent {
	
	private int timetowait;
	private long starttime;
	private int X;
	private int Y;
	private int Z;
	private int type;

	public BlockEvent(long q, int e, String details) {
		super(q, e, details);
		// TODO Auto-generated constructor stub
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
		timetowait = Integer.parseInt(details[0]);
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
		// TODO Auto-generated method stub
		return null;
	}

}
