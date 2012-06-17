package com.theminequest.MQCoreEvents.EnvEvent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;
import com.theminequest.MineQuest.API.Quest.QuestUtils;
import com.theminequest.MineQuest.API.Target.TargetDetails;
import com.theminequest.MineQuest.API.Utils.NumberUtils;

public class ExplosionEvent extends QuestEvent {
	
	private boolean targeted;
	
	private long delay;
	private long starttime;
	
	private Location loc;
	private TargetDetails t;
	
	private double radius;
	private float dmg;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * HANDLE NORMAL + TARGETING
	 */
	@Override
	public void parseDetails(String[] details) {
		if (details[0].equalsIgnoreCase("T")){
			targeted = true;
			delay = Long.parseLong(details[1]);
			t = QuestUtils.getTargetDetails(getQuest(), Integer.parseInt(details[2]));
			radius = Double.parseDouble(details[3]);
			Float f = NumberUtils.parseFloat(details[4]);
			if (f==null)
				dmg = 4.0F;
			else
				dmg = f;
		} else {
			targeted = false;
			delay = Long.parseLong(details[0]);
			double x = Double.parseDouble(details[1]);
			double y = Double.parseDouble(details[2]);
			double z = Double.parseDouble(details[3]);
			String worldname = getQuest().getDetails().getProperty(QuestDetails.QUEST_WORLD);
			World w = Bukkit.getWorld(worldname);
			loc = new Location(w,x,y,z);
			radius = Double.parseDouble(details[4]);
			Float f = NumberUtils.parseFloat(details[5]);
			if (f==null)
				dmg = 4.0F;
			else
				dmg = f;
		}
		starttime = System.currentTimeMillis();
	}

	@Override
	public boolean conditions() {
		if (System.currentTimeMillis()-starttime>=delay)
			return true;
		return false;
	}

	@Override
	public CompleteStatus action() {
		if (targeted){
			Quest q = getQuest();
			List<LivingEntity> targets = Managers.getTargetManager().processTargetDetails(q,t);
			boolean status = true;
			for (LivingEntity t : targets){
				Location l = t.getLocation();
				if (!l.getWorld().createExplosion(l, dmg, false))
					status = false;
			}
			if (status)
				return CompleteStatus.SUCCESS;
			else
				return CompleteStatus.WARNING;
		} else {
			if (!loc.getWorld().createExplosion(loc,dmg,false))
				return CompleteStatus.FAILURE;
			return CompleteStatus.SUCCESS;
		}
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
