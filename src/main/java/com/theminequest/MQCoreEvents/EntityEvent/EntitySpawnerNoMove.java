package com.theminequest.MQCoreEvents.EntityEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDeathEvent;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;
import com.theminequest.MineQuest.API.Utils.MobUtils;

public class EntitySpawnerNoMove extends QuestEvent {

	private long delay;
	private long start;
	
	private World w;
	private Location loc;
	private EntityType t;
	
	private LivingEntity entity;
	
	private boolean setup;
	
	private volatile boolean scheduled;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] Delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] Mob Type
	 * [5] isSuper // deprecated and ignored;
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		w = Bukkit.getWorld(worldname);
		double x = Double.parseDouble(details[1]);
		double y = Double.parseDouble(details[2]);
		double z = Double.parseDouble(details[3]);
		loc = new Location(w,x,y,z);
		t = MobUtils.getEntityType(details[4]);
		setup = false;
		entity = null;
		start = System.currentTimeMillis();
		scheduled = false;
	}

	@Override
	public boolean conditions() {
		if (!setup){
			if (System.currentTimeMillis()-start>=delay){
				setup = true;
				Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineQuest"), new Runnable() {
					public void run() {
						entity = w.spawnCreature(loc, t);
					}
				});
				return false;
			}
		}
		
		if (entity != null) {
			synchronized (this) {
				if (!scheduled) {
					scheduled = true;
					Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("MineQuest"), new Runnable() {
						public void run() {
							if (isComplete() == null) {
								if (entity.isDead())
									entity = w.spawnCreature(loc, t);
								else
									entity.teleport(loc);
							}
							scheduled = false;
						}
					});
				}
			}
		}
		return false;
	}

	@Override
	public CompleteStatus action() {
		// It should NEVER get here.
		return CompleteStatus.FAILURE;
	}

	/* (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#cleanUpEvent()
	 */
	@Override
	public void cleanUpEvent() {
		if (entity!=null && !entity.isDead())
			entity.setHealth(0);
	}

	@Override
	public Integer switchTask() {
		return null;
	}
}
