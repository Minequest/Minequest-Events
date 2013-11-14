package com.theminequest.events.block;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.Managers;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.event.QuestEvent;
import com.theminequest.api.quest.event.UserQuestEvent;
import com.theminequest.doc.DocArgType;
import com.theminequest.doc.V1Documentation;

@V1Documentation(
		type = "Event",
		ident = "BlockInteractEvent",
		description = "Trigger a task upon interaction with a block.",
		arguments = { "X", "Y", "Z", "Next Task" },
		typeArguments = { DocArgType.INT, DocArgType.INT, DocArgType.INT, DocArgType.INT }
		)
public class BlockInteractEvent extends QuestEvent implements UserQuestEvent, Listener {
	
	private Location loc;
	private int taskid;
	private volatile boolean passed;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] X
	 * [1] Y
	 * [2] Z
	 * [3] task to trigger
	 */
	@Override
	public void setupArguments(String[] details) {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World locW = Bukkit.getWorld(worldname);
		int locX = Integer.parseInt(details[0]);
		int locY = Integer.parseInt(details[1]);
		int locZ = Integer.parseInt(details[2]);
		loc = new Location(locW, locX, locY, locZ);
		taskid = Integer.parseInt(details[3]);
		passed = false;
	}

	@Override
	public boolean conditions() {
		return passed;
	}

	@Override
	public CompleteStatus action() {
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return taskid;
	}

	@Override
	public void setUpEvent() {
		Bukkit.getPluginManager().registerEvents(this, (Plugin) Managers.getPlatform().getPlatformObject());
	}

	@EventHandler
	public void playerInteractCondition(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			passed = e.getClickedBlock().getLocation().equals(loc);
	}

	@Override
	public void cleanUpEvent() {
		HandlerList.unregisterAll(this);
	}

	@Override
	public String getDescription() {
		return "Interact with the block at " + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "!";
	}

}
