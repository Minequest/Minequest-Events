package com.theminequest.MQCoreEvents.GroupEvent;

import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;
import com.theminequest.MineQuest.EventsAPI.TargetedQEvent;

@Deprecated
public class LineOfSightEvent extends TargetedQEvent {
	
	private int tasktoexecute;

	public LineOfSightEvent(long q, int e, String details) {
		super(q, e, details);
	}

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
	public void parseDetails(String[] details) {
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
