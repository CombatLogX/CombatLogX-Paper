package com.github.combatlogx.paper.tag;

import org.jetbrains.annotations.NotNull;

import com.github.combatlogx.api.tag.TagReason;
import com.github.combatlogx.api.tag.TagType;
import com.github.combatlogx.paper.player.CombatPlayerPaper;

public final class PlayerTag extends CombatTagPaper {
    private final CombatPlayerPaper enemy;

    public PlayerTag(@NotNull CombatPlayerPaper player, @NotNull CombatPlayerPaper enemy, @NotNull TagReason tagReason, long expireMillis) {
        super(player, TagType.PLAYER, tagReason, expireMillis);
        this.enemy = enemy;
    }

    public @NotNull CombatPlayerPaper getEnemy() {
        return this.enemy;
    }
}
