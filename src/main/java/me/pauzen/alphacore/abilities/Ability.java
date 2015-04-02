/*
 *  Created by Filip P. on 2/2/15 11:24 PM.
 */

package me.pauzen.alphacore.abilities;

import me.pauzen.alphacore.applicable.Applicable;
import me.pauzen.alphacore.commands.Command;
import me.pauzen.alphacore.commands.CommandListener;
import me.pauzen.alphacore.commands.CommandManager;
import me.pauzen.alphacore.effects.Effect;
import me.pauzen.alphacore.listeners.ListenerImplementation;
import me.pauzen.alphacore.messages.ChatMessage;
import me.pauzen.alphacore.players.CorePlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ability extends ListenerImplementation implements Applicable {

    private Effect effect;
    private String abilityName;

    private boolean invisible = false;

    public Ability(String abilityName) {
        this.abilityName = abilityName;
        this.effect = new Effect(abilityName) {

            @Override
            public void onApply(CorePlayer cPlayer) {
                cPlayer.activateAbility(Ability.this);
            }

            @Override
            public void onRemove(CorePlayer cPlayer) {
                cPlayer.deactivateAbility(Ability.this);
            }

            @Override
            public void perSecond(CorePlayer cPlayer) {
            }
        };
    }

    public Ability(String abilityName, boolean invisible) {
        this(abilityName);
        this.invisible = invisible;
    }

    public static String booleanToState(boolean toggled) {
        return toggled ? ChatColor.GREEN + "activated" : ChatColor.RED + "deactivated";
    }

    public static void setAbilityState(Ability ability, CorePlayer corePlayer, boolean newState) {
        ChatMessage.SET.send(corePlayer, ability.getName(), Ability.booleanToState(corePlayer.setAbilityState(ability, newState)));
    }

    public static void toggleAbilityState(Ability ability, CorePlayer corePlayer) {

        boolean state = corePlayer.toggleAbilityState(ability);

        AbilityStateChangeEvent stateChangeEvent = new AbilityStateChangeEvent(corePlayer, ability, state);

        stateChangeEvent.call();

        ChatMessage.TOGGLED.send(corePlayer, ability.getName(), Ability.booleanToState(state));
    }

    @Override
    public String getName() {
        return abilityName;
    }

    public Effect asEffect() {
        return this.effect;
    }

    @Override
    public void apply(CorePlayer corePlayer) {
        corePlayer.activateAbility(this);
    }

    @Override
    public void remove(CorePlayer corePlayer) {
        corePlayer.deactivateAbility(this);
    }

    @Override
    public boolean hasActivated(CorePlayer corePlayer) {
        return corePlayer.hasActivated(this);
    }

    public void setAbilityState(CorePlayer corePlayer, boolean newState) {
        setAbilityState(this, corePlayer, newState);
    }

    public void toggleAbilityState(CorePlayer corePlayer) {
        toggleAbilityState(this, corePlayer);
    }

    @Override
    public boolean isInvisible() {
        return invisible;
    }

    public Command asCommand(String name, String[] aliases, String description, String... permissions) {
        Command command = new Command(name, aliases, description) {
            @Override
            public CommandListener defaultListener() {
                return new CommandListener(false, permissions) {
                    @Override
                    public void onRun() {

                        if (modifiers.containsKey("set")) {
                            boolean setState = Boolean.parseBoolean(modifiers.get("set"));
                            set(Ability.this, CorePlayer.get((Player) commandSender), setState);
                            return;
                        }

                        toggle(Ability.this, CorePlayer.get((Player) commandSender));
                    }

                    private void set(Ability ability, CorePlayer corePlayer, boolean state) {
                        ability.setAbilityState(corePlayer, state);
                    }

                    private void toggle(Ability ability, CorePlayer corePlayer) {
                        ability.toggleAbilityState(corePlayer);
                    }
                };
            }
        };

        List<String> names = new ArrayList<>();
        Collections.addAll(names, name.split(" "));

        if (names.size() > 1) {
            Command previous = CommandManager.getManager().getCommand(names.get(names.size() - 2));

            if (previous != null) {
                command.setParent(previous);
            }
        }

        return command;
    }

    public Command asCommand(String name, String... permissions) {
        return asCommand(name, new String[]{}, "%default%", permissions);
    }

    public Command asCommand(String name, String[] aliases, String... permissions) {
        return asCommand(name, aliases, "%default%", permissions);
    }
}
