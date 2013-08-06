package com.theminequest.events.group;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.theminequest.api.CompleteStatus;

public class HealthEvent extends TargetQuestEvent {
	
	private long delay;
	private int targetid;
	private double percentage;

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
	 * [1] Target ID
	 * [2] Percent of Max
	 */
	@Override
	public void additionalDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		targetid = Integer.parseInt(details[1]);
		percentage = Double.parseDouble(details[2])/100;
	}

	@Override
	public CompleteStatus action() {
		List<LivingEntity> targets = getTargets();
		for (LivingEntity e : targets)
			e.setHealth((int) (e.getHealth() + (e.getMaxHealth()*percentage)));
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
