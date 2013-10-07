package com.theminequest.events.targeted;

import java.util.Collection;

import org.bukkit.Location;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.Managers;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.event.TargetedQuestEvent;

public class LightningTargetEvent extends TargetedQuestEvent {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * HANDLE NORMAL + TARGETING
	 */
	@Override
	public void setupArguments(String[] details) {
		
	}
	
	@Override
	public CompleteStatus targetAction(Collection<MQPlayer> entities) {
		for (MQPlayer p : entities) {
			Location l = Managers.getPlatform().fromLocation(p.getLocation());
			l.getWorld().strikeLightning(l);
		}
		
		return CompleteStatus.SUCCESS;
	}
	
	@Override
	public Integer switchTask() {
		return null;
	}
	
}
