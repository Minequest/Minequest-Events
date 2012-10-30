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
package com.theminequest.MQCoreEvents.AreaEvent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Events.DelayedQuestEvent;
import com.theminequest.MineQuest.API.Events.TargetterQuestEvent;
import com.theminequest.MineQuest.API.Events.UserQuestEvent;
import com.theminequest.MineQuest.API.Group.Group;
import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class AreaEvent extends DelayedQuestEvent implements UserQuestEvent, TargetterQuestEvent {
	
	private long delay;
	private int taskid;
	private Location loc;
	protected double radius;
	
	private List<Player> player;
	protected Group group;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] DELAY in MS
	 * [1] Task #
	 * [2] X
	 * [3] Y
	 * [4] Z
	 * [5] Radius
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
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

	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public boolean delayedConditions() {
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
	public List<LivingEntity> getTargets() {
		List<LivingEntity> pls = new ArrayList<LivingEntity>();
		for (Player p : player) {
			pls.add(p);
		}
		return pls;
	}

}
