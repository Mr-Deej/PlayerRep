package me.deej.playerrep.commands;

import org.bukkit.entity.Player;

public abstract class RepCommand {
    public abstract boolean execute(Player player, String args[]);
}
