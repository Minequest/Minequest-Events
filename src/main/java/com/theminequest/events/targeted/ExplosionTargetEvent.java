package com.theminequest.events.targeted;

import java.util.Collection;

import org.bukkit.Location;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.Managers;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.event.TargetedQuestEvent;
import com.theminequest.api.util.NumberUtils;

public class ExplosionTargetEvent extends TargetedQuestEvent {
			
	private double radius;
	private float dmg;
	private boolean fire;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * HANDLE NORMAL + TARGETING
	 */
	@Override
	public void setupArguments(String[] details) {
		radius = Double.parseDouble(details[0]);
		Float f = NumberUtils.parseFloat(details[1]);
		if (f == null)
			dmg = 4.0F;
		else
			dmg = f;
		fire = false;
		if (details.length >= 3)
			fire = Boolean.parseBoolean(details[2]);
	}
	
	@Override
	public CompleteStatus targetAction(Collection<MQPlayer> entities) {
		boolean status = true;
		for (MQPlayer player : entities) {
			Location l = Managers.getPlatform().fromLocation(player.getLocation());
			if (!l.getWorld().createExplosion(l, dmg, fire))
				status = false;
		}
		
		if (status)
			return CompleteStatus.SUCCESS;
		else
			return CompleteStatus.WARNING;
	}
	
	@Override
	public Integer switchTask() {
		return null;
	}
	
}
