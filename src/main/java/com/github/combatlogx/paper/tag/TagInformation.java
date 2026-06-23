package com.github.combatlogx.paper.tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;

import com.github.combatlogx.api.player.CombatPlayer;
import com.github.combatlogx.api.tag.CombatTag;

public final class TagInformation {
    private final CombatPlayer player;
    private final List<CombatTag> tagList;

    public TagInformation(@NotNull CombatPlayer player) {
        this.player = player;
        this.tagList = new ArrayList<>();
    }

    public @NotNull CombatPlayer getPlayer() {
        return this.player;
    }

    public @NotNull @Unmodifiable List<CombatTag> getTagList() {
        this.tagList.removeIf(CombatTag::isExpired);
        return Collections.unmodifiableList(this.tagList);
    }

    public void addTag(@NotNull CombatTag tag) {
        if (tag.isExpired()) {
            throw new IllegalArgumentException("Tag is expired");
        }

        this.tagList.add(tag);
    }

    public void removeTag(@NotNull CombatTag tag) {
        this.tagList.remove(tag);
    }

    public void removeTag(@Range(from = 0, to = Integer.MAX_VALUE) int index) {
        int tagListSize = this.tagList.size();
        if (index >= tagListSize) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + tagListSize);
        }

        this.tagList.remove(index);
    }
}
