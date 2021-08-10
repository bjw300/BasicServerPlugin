package com.cfriend.basicserverplugin.bukkit.listeners;

import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    private void playerRecord(Player player) { //Save the necessary information related to the plugin in the player info file.
        String playerName = player.getName();
        int money = 0;
        FileManager.getPlFile().addDefault(player.getUniqueId() + ".player-name", playerName);
        FileManager.getPlFile().addDefault(player.getUniqueId() + ".title.title", "title_is_not_set");
        FileManager.getPlFile().addDefault(player.getUniqueId() + ".title.color", "title_color_is_not_set");
        FileManager.getPlFile().addDefault(player.getUniqueId() + ".money", money);
        FileManager.savePlFile();
    }

    @EventHandler
    //join message
    public void onJoin(PlayerJoinEvent e) {
        String joinMessage = FileManager.getConfig().getString("join&quit-message.join-message");
        if (!joinMessage.equals("default")) {
            e.setJoinMessage(formatColor(formatJoinMessage(joinMessage, e)));
        }
        playerRecord(e.getPlayer());
    }

    @EventHandler
    //quit message
    public void onQuit(PlayerQuitEvent e) {
        String quitMessage = FileManager.getConfig().getString("join&quit-message.quit-message");
        e.setQuitMessage(formatColor(formatQuitMessage(quitMessage, e)));
    }

    private String formatJoinMessage(String s, PlayerJoinEvent e) {
        if (s != null) {
            s = s
                    .replace("%player%", e.getPlayer().getName());
            return s;
        }
        return s;
    }

    private String formatQuitMessage(String s, PlayerQuitEvent e) {
        if (s != null) {
            s = s
                    .replace("%player%", e.getPlayer().getName());
            return s;
        }
        return s;
    }

    private String formatColor(String s) { //format color
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}