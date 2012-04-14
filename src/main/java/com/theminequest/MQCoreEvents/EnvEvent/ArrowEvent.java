package com.theminequest.MQCoreEvents.EnvEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class ArrowEvent extends QEvent {
	
	private long delay;
	private long starttime;
	
	private Location loc;
	private Vector dest;

	public ArrowEvent(long q, int e, String details) {
		super(q, e, details);
	}

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.EventsAPI.QEvent#parseDetails(java.lang.String[])
	 * 
	 * [0] Delay in MS
	 * [1] loc x
	 * [2] loc y
	 * [3] loc z
	 * [4] dest x
	 * [5] dest y
	 * [6] dest z
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		World w = Bukkit.getWorld(MineQuest.questManager.getQuest(getQuestId()).world);
		double lx = Double.parseDouble(details[1]);
		double ly = Double.parseDouble(details[2]);
		double lz = Double.parseDouble(details[3]);
		loc = new Location(w,lx,ly,lz);
		double dx = Double.parseDouble(details[4]);
		double dy = Double.parseDouble(details[5]);
		double dz = Double.parseDouble(details[6]);
		dest = new Vector(dx,dy,dz);
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if (System.currentTimeMillis()-starttime>=delay)
			return true;
		return false;
	}

	@Override
	public CompleteStatus action() {
		loc.getWorld().spawnArrow(loc, dest, 1, 12);
		return CompleteStatus.SUCCESS;
	}

}
