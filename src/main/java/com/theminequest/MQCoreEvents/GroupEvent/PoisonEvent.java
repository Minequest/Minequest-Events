package com.theminequest.MQCoreEvents.GroupEvent;

import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Events.TargetQuestEvent;

public class PoisonEvent extends TargetQuestEvent {

	private long delay;
	private int targetid;
	private int times;

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
	public void additionalDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		targetid = Integer.parseInt(details[1]);
		times = Integer.parseInt(details[2]);
	}

	@Override
	public CompleteStatus action() {
		List<LivingEntity> targets = getTargets();
		for (LivingEntity e : targets){
			e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, times*20, 1), true);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
