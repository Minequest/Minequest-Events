package com.theminequest.events.env;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;

public class ArrowEvent extends DelayedQuestEvent {
	
	private long delay;
	
	private Location loc;
	private Vector dest;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
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
	public void setupArguments(String[] details) {
		delay = Long.parseLong(details[0]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		double lx = Double.parseDouble(details[1]);
		double ly = Double.parseDouble(details[2]);
		double lz = Double.parseDouble(details[3]);
		loc = new Location(w,lx,ly,lz);
		double dx = Double.parseDouble(details[4]);
		double dy = Double.parseDouble(details[5]);
		double dz = Double.parseDouble(details[6]);
		dest = new Vector(dx,dy,dz);
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public CompleteStatus action() {
		loc.getWorld().spawnArrow(loc, dest, 1, 12);
		return CompleteStatus.SUCCESS;
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
