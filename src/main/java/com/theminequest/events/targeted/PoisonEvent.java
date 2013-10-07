package com.theminequest.events.targeted;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.event.TargetedQuestEvent;

public class PoisonEvent extends TargetedQuestEvent {

	private int times;

	@Override
	public void setupArguments(String[] details) {
		times = Integer.parseInt(details[0]);
	}
	
	@Override
	public CompleteStatus targetAction(Collection<MQPlayer> entities) {
		for (MQPlayer e : entities) {
			Player p = Bukkit.getPlayerExact(e.getName());
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, times*20, 1), true);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
