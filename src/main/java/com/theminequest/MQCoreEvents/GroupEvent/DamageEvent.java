package com.theminequest.MQCoreEvents.GroupEvent;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Events.TargetQuestEvent;

public class DamageEvent extends TargetQuestEvent {

	private long delay;
	private int targetid;
	private int damage;

	@Override
	public boolean enableTargets() {
		return true;
	}

	@Override
	public int getTargetId() {
		return targetid;
	}
	
	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] delay in MS
	 * [1] target ID
	 * [2] amount of damage
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		targetid = Integer.parseInt(details[1]);
		damage = Integer.parseInt(details[2]);
	}

	@Override
	public CompleteStatus action() {
		List<LivingEntity> targets = getTargets();
		for (LivingEntity e : targets)
			e.damage(damage);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
