package com.theminequest.MQCoreEvents;

import org.bukkit.plugin.java.JavaPlugin;

import com.theminequest.MQCoreEvents.AreaEvent.AreaEvent;
import com.theminequest.MQCoreEvents.AreaEvent.SingleAreaEvent;
import com.theminequest.MQCoreEvents.BlockEvent.AdvancedBlockEvent;
import com.theminequest.MQCoreEvents.BlockEvent.BlockCDEvent;
import com.theminequest.MQCoreEvents.BlockEvent.BlockDCEvent;
import com.theminequest.MQCoreEvents.BlockEvent.BlockEvent;
import com.theminequest.MQCoreEvents.EntityEvent.EntitySpawnerCompleteEvent;
import com.theminequest.MQCoreEvents.EntityEvent.EntitySpawnerEvent;
import com.theminequest.MQCoreEvents.EntityEvent.EntitySpawnerNoMove;
import com.theminequest.MQCoreEvents.EntityEvent.EntitySpawnerNoMoveComplete;
import com.theminequest.MQCoreEvents.EntityEvent.HealthEntitySpawn;
import com.theminequest.MQCoreEvents.EnvEvent.ArrowEvent;
import com.theminequest.MQCoreEvents.EnvEvent.ExplosionEvent;
import com.theminequest.MQCoreEvents.IdleEvent.IdleEvent;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Events.EventManager;

public class MQCoreEvents extends JavaPlugin {

	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		EventManager e = Managers.getEventManager();
		//e.registerEvent("I", IdleEvent.class);
		e.addEvent("AreaEvent", AreaEvent.class);
		e.addEvent("SingleAreaEvent", SingleAreaEvent.class);
		e.addEvent("AdvancedBlockEvent", AdvancedBlockEvent.class);
		e.addEvent("BlockCDEvent", BlockCDEvent.class);
		e.addEvent("BlockDCEvent", BlockDCEvent.class);
		e.addEvent("BlockEvent", BlockEvent.class);
		e.addEvent("EntitySpawnerCompleteEvent", EntitySpawnerCompleteEvent.class);
		e.addEvent("EntitySpawnerEvent", EntitySpawnerEvent.class);
		e.addEvent("EntitySpawnerNoMove", EntitySpawnerNoMove.class);
		e.addEvent("EntitySpawnerCompleteNMEvent", EntitySpawnerNoMoveComplete.class);
		e.addEvent("HealthEntitySpawn", HealthEntitySpawn.class);
		e.addEvent("ArrowEvent", ArrowEvent.class);
		e.addEvent("ExplosionEvent", ExplosionEvent.class);
	}

}
