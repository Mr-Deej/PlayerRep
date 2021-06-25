package me.deej.playerrep.listeners.player;

import me.deej.playerrep.PlayerRep;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (PlayerRep.getPlugin().getpadataConfig().getString("PlayerData." + player.getUniqueId().toString())== null) {
            try {
                PlayerRep.getPlugin().getpadataConfig().save(PlayerRep.getPlugin().getpdataConfigFile());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
