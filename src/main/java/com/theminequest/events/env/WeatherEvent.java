package com.theminequest.events.env;

import org.bukkit.Bukkit;
import org.bukkit.World;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.DelayedQuestEvent;
import com.theminequest.doc.DocArgType;
import com.theminequest.doc.V1Documentation;

@V1Documentation(
		type = "Event",
		ident = "WeatherEvent",
		description = "Change the weather.",
		arguments = { "Delay", "Stormy?", "Duration (ticks)" },
		typeArguments = { DocArgType.INT, DocArgType.BOOL, DocArgType.INT }
		)
public class WeatherEvent extends DelayedQuestEvent {
	
	private long delay;
	
	private boolean rain;
	private int duration;

	// Duration is in ticks.
	@Override
	public void setupArguments(String[] details) {
		delay = Long.parseLong(details[0]);
		rain = Boolean.parseBoolean(details[1]);
		duration = Integer.parseInt(details[2]);
	}

	@Override
	public boolean delayedConditions() {
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

	@Override
	public long getDelay() {
		return delay;
	}

}
