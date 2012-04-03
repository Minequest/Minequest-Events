package com.theminequest.MQCoreEvents;

import org.bukkit.plugin.java.JavaPlugin;

import com.theminequest.MQCoreEvents.IdleEvent.IdleEvent;
import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.EventsAPI.EventManager;

public class MQCoreEvents extends JavaPlugin {

	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		EventManager e = MineQuest.eventManager;
		e.registerEvent("I", IdleEvent.class);
	}

}
