package com.theminequest.MQCoreEvents.EnvEvent;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.MineQuest;
import com.theminequest.MineQuest.BukkitEvents.CompleteStatus;
import com.theminequest.MineQuest.EventsAPI.QEvent;
import com.theminequest.MineQuest.Quest.Quest;
import com.theminequest.MineQuest.Target.TargetDetails;
import com.theminequest.MineQuest.Target.TargetManager;
import com.theminequest.MineQuest.Utils.NumberUtils;

public class ExplosionEvent extends QEvent {
	
	private boolean targeted;
	
	private long delay;
	private long starttime;
	
	private Location loc;
	private TargetDetails t;
	
	private double radius;
	private float dmg;

	public ExplosionEvent(long q, int e, String details) {
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
			t = MineQuest.questManager.getQuest(getQuestId()).getTarget(Integer.parseInt(details[2]));
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
			World w = Bukkit.getWorld(MineQuest.questManager.getQuest(getQuestId()).details.world);
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
			Quest q = MineQuest.questManager.getQuest(getQuestId());
			List<LivingEntity> targets = TargetManager.getTarget(q,t);
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
