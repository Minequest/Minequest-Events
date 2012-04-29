package com.theminequest.MQCoreEvents.EnvEvent;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;
import com.theminequest.MineQuest.Quest.Quest;

public class WeatherEvent extends QEvent {
	
	private long delay;
	private long starttime;
	
	private boolean rain;
	private int duration;

	public WeatherEvent(long q, int e, String details) {
		super(q, e, details);
		// TODO Auto-generated constructor stub
	}

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
		Quest q = MineQuest.questManager.getQuest(getQuestId());
		World w = Bukkit.getWorld(q.getWorld());
		w.setStorm(rain);
		w.setWeatherDuration(duration);
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
