package com.theminequest.events.env;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;
import com.theminequest.api.util.NumberUtils;

public class ExplosionEvent extends DelayedQuestEvent {
	
	private long delay;
	
	private Location loc;
	
	private double radius;
	private float dmg;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * HANDLE NORMAL + TARGETING
	 */
	@Override
	public void setupArguments(String[] details) {
		delay = Long.parseLong(details[0]);
		double x = Double.parseDouble(details[1]);
		double y = Double.parseDouble(details[2]);
		double z = Double.parseDouble(details[3]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		loc = new Location(w, x, y, z);
		radius = Double.parseDouble(details[4]);
		Float f = NumberUtils.parseFloat(details[5]);
		if (f == null)
			dmg = 4.0F;
		else
			dmg = f;
	}
	
	@Override
	public boolean delayedConditions() {
		return true;
	}
	
	@Override
	public CompleteStatus action() {
		if (!loc.getWorld().createExplosion(loc, dmg, false))
			return CompleteStatus.FAIL;
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
