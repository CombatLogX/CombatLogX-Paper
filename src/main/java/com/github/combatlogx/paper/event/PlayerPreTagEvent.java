package com.github.combatlogx.paper.event;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import com.github.combatlogx.api.tag.CombatTag;

public final class PlayerPreTagEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private CombatTag tag;

    public PlayerPreTagEvent(@NotNull Player player, @Nullable CombatTag tag) {
        super(player, true);
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return getHandlerList();
    }

    public @NotNull CombatTag getTag() {
        return this.tag;
    }
}
