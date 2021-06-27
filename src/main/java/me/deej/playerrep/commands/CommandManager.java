package me.deej.playerrep.commands;

import me.deej.playerrep.commands.handler.PlayerRepCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandManager implements CommandExecutor {
    private static final Map<String, RepCommand> commandList = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        return commandList.get(commandLabel).execute((Player) sender, args);
    }

    public static void registerCommands() {
        commandList.put("rep", new PlayerRepCommand());

        for (String cmdLabel : commandList.keySet()) {
            register(cmdLabel, new CommandManager());
        }
    }

    private static void register(String cmdLabel, CommandExecutor command) {
        Bukkit.getPluginCommand(cmdLabel).setExecutor(command);
    }
}
