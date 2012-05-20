package com.theminequest.MQCoreEvents.GroupEvent;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;

import com.theminequest.MineQuest.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;
import com.theminequest.MineQuest.EventsAPI.TargetedQEvent;

public class PoisonEvent extends TargetedQEvent {

	private long delay;
	private int targetid;
	private int times;
	
	public PoisonEvent(long q, int e, String details) {
		super(q, e, details);
	}

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

	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		targetid = Integer.parseInt(details[1]);
		times = Integer.parseInt(details[2]);
	}

	@Override
	public CompleteStatus action() {
		List<LivingEntity> targets = getTargets();
		for (LivingEntity e : targets){
			e.damage(1);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
