package com.theminequest.MQCoreEvents.EntityEvent;

import java.util.ArrayList;
import java.util.List;

import com.theminequest.MineQuest.CompleteStatus;
import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.EventsAPI.QEvent;
import com.theminequest.MineQuest.Quest.Quest;

public class EntitySpawnerCompleteEvent extends QEvent {
	
	private long delay;
	private long starttime;
	private int taskid;
	
	private List<Integer> eventids;

	public EntitySpawnerCompleteEvent(long q, int e, String details) {
		super(q, e, details);
	}

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.EventsAPI.QEvent#parseDetails(java.lang.String[])
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
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if ((System.currentTimeMillis()-starttime)>=delay)
			return false;
		return true;
	}

	@Override
	public CompleteStatus action() {
		Quest q = MineQuest.questManager.getQuest(getQuestId());
		for (QEvent e : q.getActiveTask().getEvents()){
			if (eventids.contains(e.getEventId()))
				e.complete(CompleteStatus.IGNORE);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return taskid;
	}

}
