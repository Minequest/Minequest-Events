package com.theminequest.events.entity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.Managers;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;
import com.theminequest.api.quest.event.UserQuestEvent;
import com.theminequest.bukkit.util.MobUtils;

public class HealthEntitySpawn extends DelayedQuestEvent implements UserQuestEvent, Listener {

	private long delay;
	
	private int taskid;
	
	private World w;
	private Location loc;
	private EntityType t;
	
	private LivingEntity entity;
	private int health;
	private boolean stay;
	
	private volatile boolean scheduled;
	private final Object scheduledLock = new Object();
	
	private int currentHealth;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] Delay in MS
	 * [1] Task
	 * [2] X
	 * [3] Y
	 * [4] Z
	 * [5] Mob Type
	 * [6] Health
	 * [7] Stay Put?
	 */
	@Override
	public void setupArguments(String[] details) {
		delay = Long.parseLong(details[0]);
		taskid = Integer.parseInt(details[1]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		w = Bukkit.getWorld(worldname);
		double x = Double.parseDouble(details[2]);
		double y = Double.parseDouble(details[3]);
		double z = Double.parseDouble(details[4]);
		loc = new Location(w,x,y,z);
		t = MobUtils.getEntityType(details[5]);
		health = Integer.parseInt(details[6]);
		stay = ("t".equalsIgnoreCase(details[7]));
		entity = null;
		scheduled = false;
		currentHealth = health;
	}
	
	@Override
	public boolean delayedConditions() {
		if (!scheduled && (entity == null || stay)) {
			synchronized (scheduledLock) {
				if (!scheduled) {
					scheduled = true;
					Managers.getPlatform().scheduleSyncTask(new Runnable() {
						public void run() {
							if (isComplete() == null) {
								if (entity == null) {
									entity = (LivingEntity) w.spawnEntity(loc, t);
									MobUtils.addlProps(entity);

									if (health < entity.getMaxHealth())
										entity.setHealth(health);
									
								} else if (stay && !entity.isDead() && entity.isValid()) {
									entity.teleport(loc);
								}
							}
							scheduled = false;
						}
					});
				}
			}
		}
		if (entity != null) {
			if (entity.isDead()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void setUpEvent() {
		Bukkit.getPluginManager().registerEvents(this, (Plugin) Managers.getPlatform().getPlatformObject());
	}

	@EventHandler
	public void entityDamageCondition(EntityDamageEvent e){
		if (entity == null)
			return;
		
		if (!e.getEntity().equals(entity))
			return;
		
		double eventDamage = e.getDamage();
		
		// check no damage ticks first
        if (eventDamage <= entity.getLastDamage() && entity.getNoDamageTicks() > entity.getMaximumNoDamageTicks() / 2)
            return;
		
		if (currentHealth > entity.getMaxHealth()) {
			entity.setHealth(entity.getMaxHealth());
		} else if (entity.getHealth() < currentHealth)
			entity.setHealth(currentHealth);
		
		currentHealth -= eventDamage;
	}

	@Override
	public void cleanUpEvent() {
		HandlerList.unregisterAll(this);
	}

	@Override
	public CompleteStatus action() {
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return taskid;
	}

	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public String getDescription() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kill Boss ");
		builder.append(t.getName());
		builder.append("!");
		return builder.toString();
	}
}
