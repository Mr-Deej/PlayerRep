package me.deej.playerrep.listeners;

import me.deej.playerrep.PlayerRep;
import me.deej.playerrep.listeners.player.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class ListenerManager {

    public static void registerListeners() {
        register(new ChatListener());
    }

    private static void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, PlayerRep.getPlugin());
    }
}
