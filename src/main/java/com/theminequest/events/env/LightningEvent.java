package com.theminequest.events.env;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.Managers;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.quest.QuestUtils;
import com.theminequest.api.quest.event.DelayedQuestEvent;

public class LightningEvent extends DelayedQuestEvent {
	
	private long delay;
	
	private boolean targeted;
	private int targetid;
	private Location loc;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * HANDLE NORMAL + TARGETING
	 */
	@Override
	public void setupArguments(String[] details) {
//		if (details[0].equalsIgnoreCase("T")){
//			targeted = true;
//			delay = Long.parseLong(details[1]);
//			targetid = Integer.parseInt(details[2]);
//		}else{
			targeted = false;
			delay = Long.parseLong(details[0]);
			String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
			World w = Bukkit.getWorld(worldname);
			double x = Double.parseDouble(details[1]);
			double y = Double.parseDouble(details[2]);
			double z = Double.parseDouble(details[3]);
			loc = new Location(w,x,y,z);
//		}
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}

	@Override
	public CompleteStatus action() {
		String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
		World w = Bukkit.getWorld(worldname);
//		if (targeted){
//			List<LivingEntity> targets = Managers.getTargetManager().processTargetDetails(getQuest(), QuestUtils.getTargetDetails(getQuest(), targetid));
//			for (LivingEntity e : targets){
//				w.strikeLightning(e.getLocation());
//			}
//		} else {
			w.strikeLightning(loc);
//		}
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
