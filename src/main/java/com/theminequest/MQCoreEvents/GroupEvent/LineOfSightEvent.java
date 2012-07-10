/*
 * This file is part of MineQuest-NPC, Additional Events for MineQuest.
 * MineQuest-NPC is licensed under GNU General Public License v3.
 * Copyright (C) 2012 The MineQuest Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.theminequest.MQCoreEvents.GroupEvent;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Events.TargetQuestEvent;

@Deprecated
public class LineOfSightEvent extends TargetQuestEvent {
	
	private int tasktoexecute;

	@Override
	public boolean enableTargets() {
		return false;
	}

	@Override
	public int getTargetId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getDelay() {
		return super.getDelay();
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public void additionalDetails(String[] details) {
		tasktoexecute = Integer.parseInt(details[3]);
	}

	@Override
	public CompleteStatus action() {
		return CompleteStatus.IGNORE;
	}

	@Override
	public Integer switchTask() {
		return tasktoexecute;
	}

}
