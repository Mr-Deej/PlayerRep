package me.deej.playerrep.utils;

import org.bukkit.ChatColor;

//ToDo: swap out for South-Hollow-Commons Colors.java
public class Utils {

    public static String colour(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
