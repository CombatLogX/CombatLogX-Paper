package com.github.combatlogx.paper.event;

import org.jetbrains.annotations.NotNull;

import org.bukkit.event.HandlerList;

import com.github.combatlogx.api.tag.CombatTag;
import com.github.combatlogx.api.tag.UntagReason;
import com.github.combatlogx.paper.player.CombatPlayerPaper;

public final class PlayerUntagEvent extends CombatPlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return getHandlerList();
    }

    private final CombatTag tag;
    private final UntagReason reason;

    public PlayerUntagEvent(@NotNull CombatPlayerPaper player, @NotNull CombatTag tag, @NotNull UntagReason reason) {
        super(player, true);
        this.tag = tag;
        this.reason = reason;
    }

    public @NotNull CombatTag getTag() {
        return this.tag;
    }

    public @NotNull UntagReason getReason() {
        return this.reason;
    }
}
