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
