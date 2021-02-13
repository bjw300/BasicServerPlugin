package com.cfriend.basicserverplugin.function.event;

import com.cfriend.basicserverplugin.util.FileManager;
import com.cfriend.basicserverplugin.function.manager.scoreboard.SBAMManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener {

    private void playerRecord(Player p) { //Save the necessary information related to the plugin in the player info file.
        FileManager.getPlFile().addDefault(p.getUniqueId() + ".player-name", p.getName());
        FileManager.getPlFile().addDefault(p.getUniqueId() + ".title", "title_is_not_set");
        FileManager.getPlFile().addDefault(p.getUniqueId() + ".title-color", "title_color_is_not_set");
        FileManager.getPlFile().addDefault(p.getUniqueId() + ".money", 0);
        FileManager.savePlFile();
    }

    @EventHandler
    //join message
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + "님이 서버에 참여했습니다.");
        playerRecord(e.getPlayer());
    }

    @EventHandler
    //quit message
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + "님이 서버를 떠났습니다.");
        SBAMManager board = new SBAMManager(e.getPlayer().getUniqueId());
        if (board.hasID()) {
            board.stop();
        }
    }
}