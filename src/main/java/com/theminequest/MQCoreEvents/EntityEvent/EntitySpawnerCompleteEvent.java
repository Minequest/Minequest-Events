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
package com.theminequest.MQCoreEvents.EntityEvent;

import java.util.ArrayList;
import java.util.List;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.DelayedQuestEvent;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.Quest;

public class EntitySpawnerCompleteEvent extends DelayedQuestEvent {
	
	private long delay;
	private int taskid;
	
	private List<Integer> eventids;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] Delay In MS
	 * [1] Task ID
	 * [2] eventid1,eventid2,eventid3,eventid4...
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		taskid = Integer.parseInt(details[1]);
		String[] eids = details[2].split(",");
		eventids = new ArrayList<Integer>();
		for (String e : eids){
			eventids.add(Integer.parseInt(e));
		}
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public CompleteStatus action() {
		Quest q = getQuest();
		for (QuestEvent e : q.getActiveTask().getEvents()){
			if (eventids.contains(e.getEventId()))
				e.complete(CompleteStatus.IGNORE);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return taskid;
	}

	@Override
	public long getDelay() {
		return delay;
	}

}
