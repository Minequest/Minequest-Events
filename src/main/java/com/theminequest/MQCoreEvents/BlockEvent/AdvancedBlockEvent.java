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

public class AdvancedBlockEvent extends QuestEvent {
	
	private long delay;
	private Location loc;
	private int typeid;
	private byte damage;
	private long starttime;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] TypeID
	 * [5] Damage Value
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World W = Bukkit.getWorld(worldname);
		int X = Integer.parseInt(details[1]);
		int Y = Integer.parseInt(details[2]);
		int Z = Integer.parseInt(details[3]);
		loc = new Location(W,X,Y,Z);
		typeid = Integer.parseInt(details[4]);
		damage = Byte.parseByte(details[5]);
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
		boolean result = loc.getBlock().setTypeIdAndData(typeid, damage, true);
		if (result)
			return CompleteStatus.SUCCESS;
		return CompleteStatus.FAILURE;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
