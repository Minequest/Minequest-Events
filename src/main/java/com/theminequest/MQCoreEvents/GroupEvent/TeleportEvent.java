package com.theminequest.MQCoreEvents.GroupEvent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;
import com.theminequest.MineQuest.EventsAPI.TargetedQEvent;

public class TeleportEvent extends TargetedQEvent {
	
	private long delay;
	private int targetid;
	private Location loc;

	public TeleportEvent(long q, int e, String details) {
		super(q, e, details);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean enableTargets() {
		return true;
	}

	@Override
	public int getTargetId() {
		return targetid;
	}

	@Override
	public long getDelay() {
		return delay;
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public void parseDetails(String[] details) {
		delay = Long.parseLong(details[0]);
		targetid = Integer.parseInt(details[1]);
		World w = Bukkit.getWorld(getQuest().getWorld());
		double x = Double.parseDouble(details[2]);
		double y = Double.parseDouble(details[3]);
		double z = Double.parseDouble(details[4]);
		loc = new Location(w,x,y,z);
	}

	@Override
	public CompleteStatus action() {
		List<LivingEntity> targets = getTargets();
		for (LivingEntity e : targets){
			e.teleport(loc);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
