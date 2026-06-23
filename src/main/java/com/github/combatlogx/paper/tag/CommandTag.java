package com.github.combatlogx.paper.tag;

import org.jetbrains.annotations.NotNull;

import com.github.combatlogx.api.tag.TagReason;
import com.github.combatlogx.api.tag.TagType;
import com.github.combatlogx.paper.player.CombatPlayerPaper;

public final class CommandTag extends CombatTagPaper {
    public CommandTag(@NotNull CombatPlayerPaper player, long expireMillis) {
        super(player, TagType.COMMAND, TagReason.COMMAND, expireMillis);
    }
}
