/*
 *  Created by Filip P. on 2/2/15 11:24 PM.
 */

package me.pauzen.alphacore.events;

import me.pauzen.alphacore.players.CorePlayer;

public abstract class CallablePlayerEvent extends CallableEvent {

    private CorePlayer corePlayer;

    public CallablePlayerEvent(CorePlayer corePlayer) {
        this.corePlayer = corePlayer;
    }

    public CorePlayer getPlayer() {
        return corePlayer;
    }
}
