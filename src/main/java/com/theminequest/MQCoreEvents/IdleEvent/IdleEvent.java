package com.theminequest.MQCoreEvents.IdleEvent;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;

public abstract class IdleEvent extends QuestEvent {

	@Override
	public void parseDetails(String[] details) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean conditions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompleteStatus action() {
		// TODO Auto-generated method stub
		return null;
	}

}
