/*
 *  Created by Filip P. on 2/10/15 7:13 PM.
 */

package me.pauzen.alphacore.places.events;

import me.pauzen.alphacore.events.CallablePlayerContainerEvent;
import me.pauzen.alphacore.places.Place;
import me.pauzen.alphacore.players.CorePlayer;
import org.bukkit.event.HandlerList;

public class PlaceJoinEvent extends CallablePlayerContainerEvent {

    private static final HandlerList handlers = new HandlerList();
    private Place place;

    public PlaceJoinEvent(CorePlayer corePlayer, Place place) {
        super(corePlayer);
        this.place = place;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public Place getPlace() {
        return place;
    }
}
