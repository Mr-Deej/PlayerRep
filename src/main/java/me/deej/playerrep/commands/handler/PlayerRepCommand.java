package me.deej.playerrep.commands.handler;

import me.deej.playerrep.PlayerRep;
import me.deej.playerrep.commands.RepCommand;
import me.deej.playerrep.utils.RepCalc;
import me.deej.playerrep.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.deej.playerrep.PlayerRep.getPlugin;

public class PlayerRepCommand extends RepCommand {

    public boolean execute(Player player, String[] args) {
        if (args.length == 0) {
            if (!player.hasPermission(getPlugin().getConfig().getString("AdminPermission"))) {
                for (String msg : getPlugin().getConfig().getStringList("rep-dshow-message")) {
                    if (msg.contains("%repamount%")) {
                        msg = msg.replace("%repamount%", new StringBuilder(String.valueOf(RepCalc.getRep(player))).toString());
                    }
                    player.sendMessage(Utils.colour(msg));
                }
                return true;
            }
            final int limit = getPlugin().getConfig().getInt("RepPlacements");
            player.sendMessage(Utils.colour("&f "));
            player.sendMessage(Utils.colour("&b&l  \u25ba REPUTATION ADMIN COMMANDS \u25ba"));
            player.sendMessage(Utils.colour("&f "));
            player.sendMessage(Utils.colour(" &f/rep show {player}&7 - shows current rep points"));
            player.sendMessage(Utils.colour(" &f/rep add {player} {1-" + limit + "}&7 - adds rep points"));
            player.sendMessage(Utils.colour(" &f/rep remove {player} {amount}&7 - removes rep points"));
            player.sendMessage(Utils.colour(" &f/rep reload&7 - reloads config"));
            player.sendMessage(Utils.colour("&f "));
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (player.hasPermission(getPlugin().getConfig().getString("ShowPermission"))) {
                getPlugin().reloadConfig();
                player.sendMessage(Utils.colour(getPlugin().getConfig().getString("rep-reload-message")));
            } else {
                player.sendMessage(Utils.colour("&c&lNo Permissions!"));
            }
        }
        if (args.length == 2) {
            if (player.hasPermission(getPlugin().getConfig().getString("ShowPermission"))) {
                if (args[0].equalsIgnoreCase("show")) {
                    final Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        for (String msg2 : getPlugin().getConfig().getStringList("rep-show-message")) {
                            if (msg2.contains("%repamount%") && msg2.contains("%player%")) {
                                msg2 = msg2.replace("%repamount%", new StringBuilder(String.valueOf(RepCalc.getRep(target))).toString());
                            }
                            msg2 = msg2.replace("%player%", new StringBuilder(target.getName()));
                            player.sendMessage(Utils.colour(msg2));
                        }
                    } else {
                        player.sendMessage(Utils.colour("&7Player does not exist!"));
                    }
                }
            } else {
                player.sendMessage(Utils.colour("&c&lNo Permissions!"));
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("add")) {
                final Player target = Bukkit.getPlayer(args[1]);
                final int amount = Integer.parseInt(args[2]);
                final int limit2 = getPlugin().getConfig().getInt("RepPlacements");
                if (player.hasPermission(getPlugin().getConfig().getString("AdminPermission"))) {
                    if (amount <= limit2) {
                        getPlugin().getpadataConfig().set("PlayerData." + target.getUniqueId().toString(), (Object) (getPlugin().getpadataConfig().getInt("PlayerData." + target.getUniqueId().toString()) + RepCalc.getRepPlace(amount)));
                        try {
                            PlayerRep.getPlugin().getpadataConfig().save(PlayerRep.getPlugin().getpdataConfigFile());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (String msg3 : getPlugin().getConfig().getStringList("rep-add-message")) {
                            if (msg3.contains("%repadded%") && msg3.contains("%player%")) {
                                msg3 = msg3.replace("%repadded%", new StringBuilder(String.valueOf(RepCalc.getRepPlace(amount))));
                            }
                            msg3 = msg3.replace("%player%", new StringBuilder(target.getName()));
                            player.sendMessage(Utils.colour(msg3));
                        }
                        for (String msg4 : getPlugin().getConfig().getStringList("rep-recieved-message")) {
                            if (msg4.contains("%reprec%")) {
                                msg4 = msg4.replace("%reprec%", new StringBuilder(String.valueOf(RepCalc.getRepPlace(amount))));
                            }
                            target.sendMessage(Utils.colour(msg4));
                        }
                    } else {
                        player.sendMessage(Utils.colour("&f/rep add {player} {1-" + limit2 + "}"));
                    }
                } else {
                    player.sendMessage(Utils.colour("&c&lNo Permissions!"));
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                final Player target = Bukkit.getPlayer(args[1]);
                final int amount = Integer.parseInt(args[2]);
                if (player.hasPermission(getPlugin().getConfig().getString("AdminPermission"))) {
                    if (RepCalc.getRep(target) >= amount) {
                        getPlugin().getpadataConfig().set("PlayerData." + target.getUniqueId().toString(), (Object) (getPlugin().getpadataConfig().getInt("PlayerData." + target.getUniqueId().toString()) - amount));
                        try {
                            PlayerRep.getPlugin().getpadataConfig().save(PlayerRep.getPlugin().getpdataConfigFile());
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        for (String msg5 : getPlugin().getConfig().getStringList("rep-remove-message")) {
                            if (msg5.contains("%reptaken%") && msg5.contains("%player%")) {
                                msg5 = msg5.replace("%reptaken%", new StringBuilder(String.valueOf(amount)));
                            }
                            msg5 = msg5.replace("%player%", new StringBuilder(target.getName()));
                            player.sendMessage(Utils.colour(msg5));
                        }
                        for (String msg5 : getPlugin().getConfig().getStringList("rep-taken-message")) {
                            if (msg5.contains("%reptaken%")) {
                                msg5 = msg5.replace("%retaken%", new StringBuilder(amount));
                            }
                            target.sendMessage(Utils.colour(msg5));
                        }
                    } else {
                        player.sendMessage(Utils.colour("&cYou cannot give a player negative rep points!"));
                    }
                } else {
                    player.sendMessage("No Permissions!");
                }
            }
        }
        return false;
    }
}
