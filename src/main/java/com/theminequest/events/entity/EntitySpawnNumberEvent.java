package com.theminequest.events.entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;

import com.theminequest.bukkit.util.MobUtils;

public class EntitySpawnNumberEvent extends DelayedQuestEvent {
	
	private long delay;
	private Location loc;
	private EntityType t;
	private int amount;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.API.Events.QuestEvent#parseDetails(java.lang.String[])
	 * [0] delay in ms
	 * [1] x
	 * [2] y
	 * [3] z
	 * [4] mob type
	 * [5] amount
	 */
	@Override
	public void setupArguments(String[] details) {
		delay = Long.parseLong(details[0]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		double x = Double.parseDouble(details[1]);
		double y = Double.parseDouble(details[2]);
		double z = Double.parseDouble(details[3]);
		loc = new Location(w,x,y,z);
		t = MobUtils.getEntityType(details[4]);
		amount = Integer.parseInt(details[5]);
	}
	
	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public CompleteStatus action() {
		for (int i=0; i<amount; i++)
			loc.getWorld().spawnEntity(loc, t);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
