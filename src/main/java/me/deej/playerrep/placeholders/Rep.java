package me.deej.playerrep.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.deej.playerrep.PlayerRep;
import me.deej.playerrep.utils.RepCalc;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Rep extends PlaceholderExpansion {

    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return PlayerRep.getPlugin().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "rep";
    }

    @Override
    public @NotNull String getVersion() {
        return PlayerRep.getPlugin().getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        if (identifier.equals("amount")) {
            return String.valueOf(RepCalc.getRep(player));
        }
        return null;
    }
}
