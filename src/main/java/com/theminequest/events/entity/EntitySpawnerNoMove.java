package com.theminequest.events.entity;

import com.theminequest.doc.DocArgType;
import com.theminequest.doc.V1Documentation;

@V1Documentation(
		type = "Event",
		ident = "EntitySpawnerNoMove",
		description = "Spawn entities that can't move, repeatedly at a location.",
		arguments = { "Initial Delay", "X", "Y", "Z", "Entity Type", "Drop Items?" },
		typeArguments = { DocArgType.INT, DocArgType.FLOAT, DocArgType.FLOAT, DocArgType.FLOAT, DocArgType.STRING, DocArgType.BOOL }
		)
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
