package com.github.combatlogx.paper.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import org.bukkit.plugin.PluginManager;

import com.github.combatlogx.api.player.CombatPlayer;
import com.github.combatlogx.api.tag.CombatTag;
import com.github.combatlogx.api.tag.TagManager;
import com.github.combatlogx.api.tag.UntagReason;
import com.github.combatlogx.paper.CombatPlugin;
import com.github.combatlogx.paper.event.PlayerPreTagEvent;
import com.github.combatlogx.paper.event.PlayerTagEvent;
import com.github.combatlogx.paper.event.PlayerUntagEvent;
import com.github.combatlogx.paper.player.CombatPlayerPaper;

public final class TagManagerPaper implements TagManager {
    private final CombatPlugin plugin;
    private final Map<UUID, TagInformation> tagMap;

    public TagManagerPaper(@NotNull CombatPlugin plugin) {
        this.plugin = plugin;
        this.tagMap = new HashMap<>();
    }

    private @NotNull CombatPlugin getPlugin() {
        return this.plugin;
    }

    @Override
    public boolean addTag(@NotNull CombatPlayer player, @NotNull CombatTag tag) {
        if (!(player instanceof CombatPlayerPaper playerPaper)) {
            throw new IllegalStateException("player is not a valid instance of CombatPlayerPaper!");
        }

        PluginManager pluginManager = getPlugin().getServer().getPluginManager();
        PlayerPreTagEvent preEvent = new PlayerPreTagEvent(playerPaper, tag);
        pluginManager.callEvent(preEvent);

        if (preEvent.isCancelled()) {
            return false;
        }

        TagInformation tagInformation = getTagInformation(player);
        tagInformation.addTag(tag);

        PlayerTagEvent postEvent = new PlayerTagEvent(playerPaper, tag);
        pluginManager.callEvent(postEvent);
        return true;
    }

    @Override
    public void untag(@NotNull CombatPlayer player, @NotNull CombatTag tag, @NotNull UntagReason reason) {
        if (!(player instanceof CombatPlayerPaper playerPaper)) {
            throw new IllegalStateException("player is not a valid instance of CombatPlayerPaper!");
        }

        TagInformation tagInformation = getTagInformation(player);
        tagInformation.removeTag(tag);

        PluginManager pluginManager = getPlugin().getServer().getPluginManager();
        PlayerUntagEvent untagEvent = new PlayerUntagEvent(playerPaper, tag, reason);
        pluginManager.callEvent(untagEvent);

        if (tagInformation.getTagList().isEmpty()) {
            UUID playerId = player.getUniqueId();
            this.tagMap.remove(playerId);
        }
    }

    @Override
    public boolean isTagged(@NotNull CombatPlayer player) {
        List<CombatTag> tagList = getTags(player);
        return !tagList.isEmpty();
    }

    @Override
    public @NotNull List<CombatTag> getTags(@NotNull CombatPlayer player) {
        TagInformation tagInformation = getTagInformation(player);
        return tagInformation.getTagList();
    }

    @Override
    public @NotNull List<CombatPlayer> getTaggedPlayers() {
        List<CombatPlayer> playerList = new ArrayList<>();
        Collection<TagInformation> tagMapValues = this.tagMap.values();
        for (TagInformation tagInformation : tagMapValues) {
            List<CombatTag> tagList = tagInformation.getTagList();
            if (tagList.isEmpty()) {
                playerList.add(tagInformation.getPlayer());
            }
        }

        return playerList;
    }

    private @NotNull TagInformation getTagInformation(@NotNull CombatPlayer player) {
        if (!(player instanceof CombatPlayerPaper playerPaper)) {
            throw new IllegalStateException("player is not a valid instance of CombatPlayerPaper!");
        }

        UUID playerId = playerPaper.getUniqueId();
        if (this.tagMap.containsKey(playerId)) {
            return this.tagMap.get(playerId);
        }

        TagInformation tagInformation = new TagInformation(player);
        this.tagMap.put(playerId, tagInformation);
        return tagInformation;
    }
}
