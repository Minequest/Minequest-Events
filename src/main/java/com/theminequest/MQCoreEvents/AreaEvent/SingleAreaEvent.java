package com.theminequest.MQCoreEvents.AreaEvent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class SingleAreaEvent extends AreaEvent {

	@Override
	public boolean conditions() {
		List<Player> py = group.getMembers();
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		for (Player p : py){
			if (!p.getWorld().getName().equals(worldname))
				continue;
			if (p.getLocation().distance(loc)<=radius)
				return true;
		}
		return false;
	}

}
