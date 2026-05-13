package com.github.combatlogx.paper.player;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.github.combatlogx.api.player.CombatPlayer;

public final class CombatPlayerPaper implements CombatPlayer {
    private final UUID playerId;
    private transient OfflinePlayer offlinePlayer;

    CombatPlayerPaper(@NotNull UUID playerId) {
        this.playerId = playerId;
    }

    CombatPlayerPaper(@NotNull OfflinePlayer offlinePlayer) {
        this(offlinePlayer.getUniqueId());
        this.offlinePlayer = offlinePlayer;
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return this.playerId;
    }

    @Override
    public boolean isOnline() {
        OfflinePlayer offlinePlayer = getOfflinePlayer();
        return offlinePlayer.isOnline();
    }

    public @NotNull OfflinePlayer getOfflinePlayer() {
        if (this.offlinePlayer == null) {
            UUID playerId = getUniqueId();
            this.offlinePlayer = Bukkit.getOfflinePlayer(playerId);
        }

        return this.offlinePlayer;
    }

    public @Nullable Player getPlayer() {
        OfflinePlayer offlinePlayer = getOfflinePlayer();
        return offlinePlayer.getPlayer();
    }
}
