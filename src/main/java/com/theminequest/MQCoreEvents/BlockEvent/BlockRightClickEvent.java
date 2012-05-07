package com.theminequest.MQCoreEvents.BlockEvent;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.NamedQEvent;
import com.theminequest.MineQuest.EventsAPI.QEvent;

public class BlockRightClickEvent extends QEvent implements NamedQEvent {
	
	private Location loc;
	private int taskid;

	public BlockRightClickEvent(long q, int e, String details) {
		super(q, e, details);
	}

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.EventsAPI.QEvent#parseDetails(java.lang.String[])
	 * [0] X
	 * [1] Y
	 * [2] Z
	 * [3] task to trigger
	 */
	@Override
	public void parseDetails(String[] details) {
		World locW = Bukkit.getWorld(getQuest().getWorld());
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
		if (e.getAction()==Action.RIGHT_CLICK_BLOCK){
			if (e.getClickedBlock().getState().getLocation().equals(loc))
				return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Right click the block at " + loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "!";
	}

}
