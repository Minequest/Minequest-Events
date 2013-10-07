package com.theminequest.events.targeted;

import java.util.Collection;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.event.TargetedQuestEvent;

public class HealthTargetEvent extends TargetedQuestEvent {
	
	private double percentage;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] Percent of Max
	 */
	@Override
	public void setupArguments(String[] details) {
		percentage = Double.parseDouble(details[0])/100;
	}

	@Override
	public CompleteStatus targetAction(Collection<MQPlayer> entities) {
		for (MQPlayer player : entities)
			player.setHealth((int) (player.getHealth() + (player.getMaxHealth()*percentage)));
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
