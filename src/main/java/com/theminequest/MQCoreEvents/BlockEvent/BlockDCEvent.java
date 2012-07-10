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
package com.theminequest.MQCoreEvents.BlockEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class BlockDCEvent extends QuestEvent {

	private long firstdelay;
	private long seconddelay;
	private Location loc;
	private int typeid;
	private long starttime;
	private int step;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * Destroy, then create
	 * [0]: first delay in MS before destroy
	 * [1]: second delay is MS after destroy before create
	 * [2]: X
	 * [3]: Y
	 * [4]: Z
	 * [5]: Type ID 
	 */
	@Override
	public void parseDetails(String[] details) {
		firstdelay = Long.parseLong(details[0]);
		seconddelay = Long.parseLong(details[1]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		int x = Integer.parseInt(details[2]);
		int y = Integer.parseInt(details[3]);
		int z = Integer.parseInt(details[4]);
		loc = new Location(w,x,y,z);
		typeid = Integer.parseInt(details[5]);
		step = 0;
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if (step==0){
			if (starttime+firstdelay<=System.currentTimeMillis()){
				loc.getBlock().setTypeId(0);
				step++;
				starttime = System.currentTimeMillis();
			}
			return false;
		}
		if (starttime+seconddelay<=System.currentTimeMillis()){
			return true;
		}
		return false;
	}

	@Override
	public CompleteStatus action() {
		boolean result = loc.getBlock().setTypeId(typeid);
		if (result)
			return CompleteStatus.SUCCESS;
		else
			return CompleteStatus.FAILURE;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
