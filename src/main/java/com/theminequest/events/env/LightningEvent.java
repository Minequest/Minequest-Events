package com.theminequest.events.env;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;

public class LightningEvent extends DelayedQuestEvent {
	
	private long delay;
	
	private Location loc;
	
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
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		double x = Double.parseDouble(details[1]);
		double y = Double.parseDouble(details[2]);
		double z = Double.parseDouble(details[3]);
		loc = new Location(w, x, y, z);
	}
	
	@Override
	public boolean delayedConditions() {
		return true;
	}
	
	@Override
	public CompleteStatus action() {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		w.strikeLightning(loc);
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
