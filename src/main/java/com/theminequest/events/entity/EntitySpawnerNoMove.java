package com.theminequest.events.entity;

public class EntitySpawnerNoMove extends EntitySpawnerEvent {

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] Delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] Mob Type
	 * [5] dropItems;
	 */
	@Override
	public void setupArguments(String[] details) {
		noMove = true;
		super.setupArguments(details);
	}
}
