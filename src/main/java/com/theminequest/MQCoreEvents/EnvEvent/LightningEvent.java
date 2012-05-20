package com.theminequest.MQCoreEvents.EnvEvent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.CompleteStatus;
import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.EventsAPI.QEvent;
import com.theminequest.MineQuest.Quest.Quest;
import com.theminequest.MineQuest.Target.TargetManager;

public class LightningEvent extends QEvent {
	
	private long delay;
	private long starttime;
	
	private boolean targeted;
	private int targetid;
	private Location loc;

	public LightningEvent(long q, int e, String details) {
		super(q, e, details);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.EventsAPI.QEvent#parseDetails(java.lang.String[])
	 * HANDLE NORMAL + TARGETING
	 */
	@Override
	public void parseDetails(String[] details) {
		if (details[0].equalsIgnoreCase("T")){
			targeted = true;
			delay = Long.parseLong(details[1]);
			targetid = Integer.parseInt(details[2]);
		}else{
			targeted = false;
			delay = Long.parseLong(details[0]);
			World w = Bukkit.getWorld(MineQuest.questManager.getQuest(getQuestId()).getWorld());
			double x = Double.parseDouble(details[0]);
			double y = Double.parseDouble(details[1]);
			double z = Double.parseDouble(details[2]);
			loc = new Location(w,x,y,z);
		}
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
		if (targeted){
			List<LivingEntity> targets = TargetManager.getTarget(q,q.getTarget(targetid));
			for (LivingEntity e : targets){
				w.strikeLightning(e.getLocation());
			}
		} else {
			w.strikeLightning(loc);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
