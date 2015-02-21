/*
 *  Created by Filip P. on 2/6/15 7:02 PM.
 */

package me.pauzen.alphacore.effects;

import me.pauzen.alphacore.abilities.Ability;
import me.pauzen.alphacore.abilities.PremadeAbilities;
import me.pauzen.alphacore.players.CorePlayer;

public enum PremadeEffects {

    NO_BLOCK_BREAK(),
    NO_BLOCK_PLACE(),
    NO_SHOOT_BOW(15),
    MUTE(PremadeAbilities.CHAT.ability()),;

    private Effect effect;

    PremadeEffects(long duration) {
        this.effect = new Effect(duration) {
            @Override
            public void onApply(CorePlayer corePlayer) {
            }

            @Override
            public void onRemove(CorePlayer corePlayer) {
            }

            @Override
            public void perSecond(CorePlayer corePlayer) {
            }
        };
    }

    PremadeEffects(Effect effect) {
        this.effect = effect;
    }

    PremadeEffects(Ability ability) {
        this(new Effect() {
            @Override
            public void onApply(CorePlayer corePlayer) {
                corePlayer.toggleAbilityState(ability);
            }

            @Override
            public void onRemove(CorePlayer corePlayer) {
                corePlayer.toggleAbilityState(ability);
            }

            @Override
            public void perSecond(CorePlayer corePlayer) {
            }
        });
    }

    PremadeEffects() {
        this(-1);
    }

    public Effect effect() {
        return this.effect;
    }

}

