package com.theminequest.MQCoreEvents;

import org.bukkit.plugin.java.JavaPlugin;

import com.theminequest.MQCoreEvents.AreaEvent.AreaEvent;
import com.theminequest.MQCoreEvents.AreaEvent.SingleAreaEvent;
import com.theminequest.MQCoreEvents.BlockEvent.AdvancedBlockEvent;
import com.theminequest.MQCoreEvents.BlockEvent.BlockCDEvent;
import com.theminequest.MQCoreEvents.BlockEvent.BlockDCEvent;
import com.theminequest.MQCoreEvents.BlockEvent.BlockEvent;
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
		//e.registerEvent("I", IdleEvent.class);
		e.registerEvent("AreaEvent", AreaEvent.class);
		e.registerEvent("SingleAreaEvent", SingleAreaEvent.class);
		e.registerEvent("AdvancedBlockEvent", AdvancedBlockEvent.class);
		e.registerEvent("BlockCDEvent", BlockCDEvent.class);
		e.registerEvent("BlockDCEvent", BlockDCEvent.class);
		e.registerEvent("BlockEvent", BlockEvent.class);
	}

}
