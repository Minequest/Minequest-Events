package com.theminequest.MQCoreEvents.BlockEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.UserQuestEvent;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public class BlockInteractEvent extends QuestEvent implements UserQuestEvent {
	
	private Location loc;
	private int taskid;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] X
	 * [1] Y
	 * [2] Z
	 * [3] task to trigger
	 */
	@Override
	public void parseDetails(String[] details) {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World locW = Bukkit.getWorld(worldname);
		int locX = Integer.parseInt(details[0]);
		int locY = Integer.parseInt(details[1]);
		int locZ = Integer.parseInt(details[2]);
		loc = new Location(locW, locX, locY, locZ);
		taskid = Integer.parseInt(details[3]);
	}

	@Override
	public boolean conditions() {
		return false;
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
	public boolean playerInteractCondition(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR))
			return false;
		return e.getClickedBlock().getLocation().equals(loc);
	}

	@Override
	public String getDescription() {
		return "Interact with the block at " + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "!";
	}

}
