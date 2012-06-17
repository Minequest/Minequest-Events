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
