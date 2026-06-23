package com.github.combatlogx.paper.event;

import org.jetbrains.annotations.NotNull;

import org.bukkit.event.Event;

import com.github.combatlogx.paper.player.CombatPlayerPaper;

public abstract class CombatPlayerEvent extends Event {
    private final CombatPlayerPaper player;

    public CombatPlayerEvent(@NotNull CombatPlayerPaper player, boolean isAsync) {
        super(isAsync);
        this.player = player;
    }

    public @NotNull CombatPlayerPaper getPlayer() {
        return this.player;
    }
}
