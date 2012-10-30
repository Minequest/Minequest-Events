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

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class SingleAreaEvent extends AreaEvent {
	private Player trigger = null;

	@Override
	public boolean delayedConditions() {
		List<Player> py = group.getMembers();
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		for (Player p : py){
			if (!p.getWorld().getName().equals(worldname))
				continue;
			if (p.getLocation().distance(getLocation())<=radius){
				trigger = p;
				return true;
			}
		}
		return false;
	}

	@Override
	public List<LivingEntity> getTargets() {
		List<LivingEntity> pls = new ArrayList<LivingEntity>();
		
		if (trigger != null) {
			pls.add(trigger);
		}
		
		return pls;
	}

}
