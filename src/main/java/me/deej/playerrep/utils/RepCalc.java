package me.deej.playerrep.utils;

import me.deej.playerrep.PlayerRep;
import org.bukkit.entity.Player;

public class RepCalc {
    public static int getRep(Player player) {
        if (PlayerRep.getPlugin().getpadataConfig().getString("PlayerData." + player.getUniqueId().toString()) != null) {
            return PlayerRep.getPlugin().getpadataConfig().getInt("PlayerData." + player.getUniqueId().toString());
        }
        return 0;
    }

    public static int getRepPlace(int amount) {
        return PlayerRep.getPlugin().getConfig().getInt("RepPlaces." + amount);
    }
}
