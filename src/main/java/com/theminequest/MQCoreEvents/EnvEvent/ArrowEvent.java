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
package com.theminequest.MQCoreEvents.EnvEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class ArrowEvent extends QuestEvent {
	
	private long delay;
	private long starttime;
	
	private Location loc;
	private Vector dest;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * 
	 * [0] Delay in MS
	 * [1] loc x
	 * [2] loc y
	 * [3] loc z
	 * [4] dest x
	 * [5] dest y
	 * [6] dest z
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		double lx = Double.parseDouble(details[1]);
		double ly = Double.parseDouble(details[2]);
		double lz = Double.parseDouble(details[3]);
		loc = new Location(w,lx,ly,lz);
		double dx = Double.parseDouble(details[4]);
		double dy = Double.parseDouble(details[5]);
		double dz = Double.parseDouble(details[6]);
		dest = new Vector(dx,dy,dz);
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if (System.currentTimeMillis()-starttime>=delay)
			return true;
		return false;
	}

	@Override
	public CompleteStatus action() {
		loc.getWorld().spawnArrow(loc, dest, 1, 12);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
