package com.github.combatlogx.paper.event;

import org.jetbrains.annotations.NotNull;

import org.bukkit.event.HandlerList;

import com.github.combatlogx.api.tag.CombatTag;
import com.github.combatlogx.paper.player.CombatPlayerPaper;

public final class PlayerTagEvent extends CombatPlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return getHandlerList();
    }

    private final CombatTag tag;

    public PlayerTagEvent(@NotNull CombatPlayerPaper player, @NotNull CombatTag tag) {
        super(player, true);
        this.tag = tag;
    }

    public @NotNull CombatTag getTag() {
        return this.tag;
    }
}
