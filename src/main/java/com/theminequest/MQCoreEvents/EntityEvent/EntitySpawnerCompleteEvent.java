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
