package com.github.combatlogx.paper.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import org.bukkit.OfflinePlayer;

import com.github.combatlogx.api.player.CombatPlayer;
import com.github.combatlogx.api.player.PlayerManager;
import com.github.combatlogx.paper.CombatPlugin;

public final class PlayerManagerPaper implements PlayerManager {
    private final CombatPlugin plugin;
    private final Map<UUID, CombatPlayerPaper> playerMap;

    public PlayerManagerPaper(@NotNull CombatPlugin plugin) {
        this.plugin = plugin;
        this.playerMap = new HashMap<>();
    }

    @Override
    public @NotNull CombatPlayer getCombatPlayer(@NotNull UUID playerId) {
        if (this.playerMap.containsKey(playerId)) {
            return this.playerMap.get(playerId);
        }

        CombatPlayerPaper load = loadCombatPlayer(playerId);
        this.playerMap.put(playerId, load);
        return load;
    }

    public @NotNull CombatPlayer getCombatPlayer(@NotNull OfflinePlayer offlinePlayer) {
        UUID playerId = offlinePlayer.getUniqueId();
        return getCombatPlayer(playerId);
    }

    private @NotNull CombatPlugin getPlugin() {
        return this.plugin;
    }

    private @NotNull CombatPlayerPaper loadCombatPlayer(@NotNull UUID playerId) {
        return new CombatPlayerPaper(playerId);
    }

    private void unloadCombatPlayer(@NotNull CombatPlayer combatPlayer) {
        this.playerMap.remove(combatPlayer.getUniqueId());
    }
}
