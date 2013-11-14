package com.theminequest.events.targeted;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.event.TargetedQuestEvent;
import com.theminequest.doc.DocArgType;
import com.theminequest.doc.V1Documentation;

@V1Documentation(
		type = "TargetEvent",
		ident = "DamageEvent",
		description = "Damage players with the specified target.",
		arguments = { "damage" },
		typeArguments = { DocArgType.INT }
		)
public class DamageEvent extends TargetedQuestEvent {

	private int damage;

	/*
	 * (non-Javadoc)
	 * @see com.theminequest.MineQuest.Events.QEvent#parseDetails(java.lang.String[])
	 * [0] amount of damage
	 */
	@Override
	public void setupArguments(String[] details) {
		damage = Integer.parseInt(details[0]);
	}
	
	@Override
	public CompleteStatus targetAction(Collection<MQPlayer> entities) {
		for (MQPlayer player : entities) {
			Player nativePlayer = Bukkit.getPlayerExact(player.getName());
			if (nativePlayer != null)
				nativePlayer.damage(damage);
		}
		return CompleteStatus.SUCCESS;
	}

	@Override
	public Integer switchTask() {
		return null;
	}

}
