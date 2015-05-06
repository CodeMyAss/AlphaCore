/*
 *  Created by Filip P. on 2/6/15 6:55 PM.
 */

package me.pauzen.alphacore.combat;

import me.pauzen.alphacore.events.CallablePlayerEvent;
import me.pauzen.alphacore.players.CorePlayer;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class AutoRespawnEvent extends CallablePlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    private EntityDamageEvent.DamageCause damageCause;

    public AutoRespawnEvent(CorePlayer corePlayer, EntityDamageEvent.DamageCause damageCause) {
        super(corePlayer);
        this.damageCause = damageCause;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public EntityDamageEvent.DamageCause getDamageCause() {
        return damageCause;
    }
}
