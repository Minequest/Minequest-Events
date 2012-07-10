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
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;
import com.theminequest.MineQuest.API.Utils.ItemUtils;

public class BlockEvent extends QuestEvent {
	
	private long timetowait;
	private long starttime;
	private int X;
	private int Y;
	private int Z;
	private Material material;

	/*
	 * [0] delay in MS
	 * [1] X
	 * [2] Y
	 * [3] Z
	 * [4] Type ID
	 */
	@Override
	public void parseDetails(String[] details) {
		// TODO Auto-generated method stub
		timetowait = Long.parseLong(details[0]);
		X = Integer.parseInt(details[1]);
		Y = Integer.parseInt(details[2]);
		Z = Integer.parseInt(details[3]);
		material = ItemUtils.getMaterial(details[4]);
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		long curtime = System.currentTimeMillis();
		if (curtime-starttime>=timetowait)
			return true;
		return false;
	}

	@Override
	public CompleteStatus action() {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		Location l = new Location(w,X,Y,Z);
		Block b = l.getBlock();
		if (material != null) {
			boolean status = b.setTypeId(material.getId());
			if (status)
				return CompleteStatus.SUCCESS;
		}
		return CompleteStatus.WARNING;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
