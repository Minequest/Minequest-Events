package com.theminequest.MQCoreEvents.EntityEvent;

import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class EntitySpawnerNoMove extends QEvent {

	public EntitySpawnerNoMove(long q, int e, String details) {
		super(q, e, details);
		// TODO Auto-generated constructor stub
	}

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
