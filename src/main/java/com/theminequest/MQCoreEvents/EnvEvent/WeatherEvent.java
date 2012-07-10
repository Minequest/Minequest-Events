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
import org.bukkit.World;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class WeatherEvent extends QuestEvent {
	
	private long delay;
	private long starttime;
	
	private boolean rain;
	private int duration;

	// Duration is in ticks.
	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		rain = Boolean.parseBoolean(details[1]);
		duration = Integer.parseInt(details[2]);
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if (starttime+delay>System.currentTimeMillis())
			return false;
		return true;
	}

	@Override
	public CompleteStatus action() {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
		w.setStorm(rain);
		w.setWeatherDuration(duration);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
