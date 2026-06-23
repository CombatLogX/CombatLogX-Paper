package com.github.combatlogx.paper.event;

import org.jetbrains.annotations.NotNull;

import org.bukkit.event.Cancellable;

import com.github.combatlogx.paper.player.CombatPlayerPaper;

public abstract class CancellableCombatPlayerEvent extends CombatPlayerEvent implements Cancellable {
    private boolean cancelled;

    public CancellableCombatPlayerEvent(@NotNull CombatPlayerPaper player, boolean isAsync) {
        super(player, isAsync);
        this.cancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
