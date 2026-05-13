package com.github.combatlogx.paper;

import org.jetbrains.annotations.NotNull;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.combatlogx.api.CombatLogX;
import com.github.combatlogx.api.expansion.ExpansionManager;
import com.github.combatlogx.api.player.PlayerManager;
import com.github.combatlogx.api.tag.TagManager;
import com.github.combatlogx.api.timer.TimerManager;

public final class CombatPlugin extends JavaPlugin implements CombatLogX {
    @Override
    public void onLoad() {
        // TODO
    }

    @Override
    public void onEnable() {
        // TODO
    }

    @Override
    public void onDisable() {
        // TODO
    }

    @Override
    public void reloadConfiguration() {
        // TODO
    }

    @Override
    public @NotNull ExpansionManager getExpansionManager() {
        // TODO
        return null;
    }

    @Override
    public @NotNull PlayerManager getDataManager() {
        // TODO
        return null;
    }

    @Override
    public @NotNull TagManager getTagManager() {
        // TODO
        return null;
    }

    @Override
    public @NotNull TimerManager getTimerManager() {
        // TODO
        return null;
    }
}
