package com.theminequest.MQCoreEvents.AreaEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class SingleAreaEvent extends AreaEvent {

	public SingleAreaEvent(long q, int e, String details) {
		super(q, e, details);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean conditions() {
		Player[] py = Bukkit.getOnlinePlayers();
		for (Player p : py){
			if (!group.contains(p))
				continue;
			if (p.getLocation().distanceSquared(loc)<=radiussq)
				return true;
		}
		return false;
	}

}
