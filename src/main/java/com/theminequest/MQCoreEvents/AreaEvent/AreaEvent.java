package com.theminequest.MQCoreEvents.AreaEvent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Events.UserQuestEvent;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Group.Group;
import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class AreaEvent extends QuestEvent implements UserQuestEvent {
	
	//private long delay;
	private int taskid;
	private Location loc;
	protected double radius;
	
	private List<Player> player;
	protected Group group;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] DELAY in MS (depreciated)
	 * [1] Task #
	 * [2] X
	 * [3] Y
	 * [4] Z
	 * [5] Radius
	 */
	@Override
	public void parseDetails(String[] details) {
		//delay = Long.parseLong(details[0]);
		taskid = Integer.parseInt(details[1]);
		int X = Integer.parseInt(details[2]);
		int Y = Integer.parseInt(details[3]);
		int Z = Integer.parseInt(details[4]);
		radius = Double.parseDouble(details[5]);
		Quest q = getQuest();
		loc = new Location(Bukkit.getWorld((String) q.getDetails().getProperty(QuestDetails.QUEST_WORLD)),X,Y,Z);
		group = Managers.getQuestGroupManager().get(getQuest());
		player = new ArrayList<Player>();
	}

	@Override
	public boolean conditions() {
		List<Player> py = group.getMembers();
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		for (Player p : py){
			if (!p.getWorld().getName().equals(worldname))
				continue;
			if (p.getLocation().distance(loc)<=radius)
				player.add(p);
		}
		if (player.size()>=group.getMembers().size())
			return true;
		return false;
	}

	@Override
	public CompleteStatus action() {
		return CompleteStatus.SUCCESS;
		
	}

	@Override
	public Integer switchTask() {
		return taskid;
	}

	@Override
	public String getDescription() {
		return "Head to " + loc.getX() + "," + loc.getY() + "," + loc.getZ() + "! (" + radius + " block leeway)";
	}
	
	public Location getLocation() {
		return loc;
	}

}
