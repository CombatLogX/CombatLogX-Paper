package com.github.combatlogx.paper.tag;

import org.jetbrains.annotations.NotNull;

import com.github.combatlogx.api.tag.CombatTag;
import com.github.combatlogx.api.tag.TagReason;
import com.github.combatlogx.api.tag.TagType;
import com.github.combatlogx.paper.player.CombatPlayerPaper;

public abstract class CombatTagPaper implements CombatTag {
    private final CombatPlayerPaper player;
    private final TagType tagType;
    private final TagReason tagReason;
    private long expireMillis;

    public CombatTagPaper(@NotNull CombatPlayerPaper player, @NotNull TagType tagType, @NotNull TagReason tagReason, long expireMillis) {
        this.player = player;
        this.tagType = tagType;
        this.tagReason = tagReason;
        this.expireMillis = expireMillis;
    }

    @Override
    public @NotNull CombatPlayerPaper getCombatPlayer() {
        return player;
    }

    @Override
    public @NotNull TagType getTagType() {
        return this.tagType;
    }

    @Override
    public @NotNull TagReason getTagReason() {
        return this.tagReason;
    }

    @Override
    public long getExpirationMillis() {
        return this.expireMillis;
    }

    @Override
    public void setExpirationMillis(long expirationMillis) {
        this.expireMillis = expirationMillis;
    }

    @Override
    public boolean isExpired() {
        long systemMillis = System.currentTimeMillis();
        long expireMillis = getExpirationMillis();
        return (systemMillis >= expireMillis);
    }
}
