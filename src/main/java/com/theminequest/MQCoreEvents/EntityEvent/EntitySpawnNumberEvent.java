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
package com.theminequest.MQCoreEvents.EntityEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.DelayedQuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;
import com.theminequest.MineQuest.API.Utils.MobUtils;

public class EntitySpawnNumberEvent extends DelayedQuestEvent {
	
	private long delay;
	private Location loc;
	private EntityType t;
	private int amount;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.API.Events.QuestEvent#parseDetails(java.lang.String[])
	 * [0] delay in ms
	 * [1] x
	 * [2] y
	 * [3] z
	 * [4] mob type
	 * [5] amount
	 */
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		double x = Double.parseDouble(details[1]);
		double y = Double.parseDouble(details[2]);
		double z = Double.parseDouble(details[3]);
		loc = new Location(w,x,y,z);
		t = MobUtils.getEntityType(details[4]);
		amount = Integer.parseInt(details[5]);
	}
	
	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public CompleteStatus action() {
		for (int i=0; i<amount; i++)
			loc.getWorld().spawnCreature(loc, t);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
