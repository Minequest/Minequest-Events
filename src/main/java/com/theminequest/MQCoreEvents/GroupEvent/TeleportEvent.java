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
package com.theminequest.MQCoreEvents.GroupEvent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Events.TargetQuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class TeleportEvent extends TargetQuestEvent {
	
	private long delay;
	private int targetid;
	private Location loc;
	
	@Override
	public boolean enableTargets() {
		return true;
	}

	@Override
	public int getTargetId() {
		return targetid;
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
	public void additionalDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		targetid = Integer.parseInt(details[1]);
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		double x = Double.parseDouble(details[2]);
		double y = Double.parseDouble(details[3]);
		double z = Double.parseDouble(details[4]);
		loc = new Location(w,x,y,z);
	}

	@Override
	public CompleteStatus action() {
		List<LivingEntity> targets = getTargets();
		for (LivingEntity e : targets){
			e.teleport(loc);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
