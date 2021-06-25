package me.deej.playerrep.listeners.player;

import me.deej.playerrep.utils.RepCalc;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.annotation.processing.SupportedSourceVersion;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat(event.getFormat().replace("{REP_AMOUNT}", new StringBuilder().append(RepCalc.getRep(event.getPlayer())).toString()));
    }
}
