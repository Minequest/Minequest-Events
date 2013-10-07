package com.theminequest.events.targeted;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.platform.MQLocation;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.TargetedQuestEvent;

public class TeleportEvent extends TargetedQuestEvent {
	
	private MQLocation loc;

	@Override
	public void setupArguments(String[] details) {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		double x = Double.parseDouble(details[0]);
		double y = Double.parseDouble(details[1]);
		double z = Double.parseDouble(details[2]);
		loc = new MQLocation(w.getName(),x,y,z);
	}

	@Override
	public CompleteStatus targetAction(Collection<MQPlayer> entities) {
		for (MQPlayer player : entities)
			player.teleport(loc);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
