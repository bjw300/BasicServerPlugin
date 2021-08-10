package com.cfriend.basicserverplugin.bukkit.api.manager;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TitleManager implements Listener {



    public String getTitle(Player player) { //get player's title
        try {
            String title = FileManager.getPlFile().getString(player.getUniqueId() + ".title.title");
            String titleColor = FileManager.getPlFile().getString(player.getUniqueId() + ".title.color");
            if (!(title.contains("title_is_not_set")) || title != null) {
                return ChatColor.valueOf(titleColor) + title;
            } else {
                return null;
            }
        } catch (Exception e) {
            Bukkit.getLogger().warning("[BasicServerPlugin] Error getting title: " + e);
        }
        return null;
    }

    public void showTitle(Player player) { //show player's title
        String title = FileManager.getPlFile().getString(player.getUniqueId() + ".title.title");
        String titleColor = FileManager.getPlFile().getString(player.getUniqueId() + ".title.color");
        if (title.equals("title_is_not_set")) {
            player.sendMessage(ChatColor.YELLOW + "The title has not been set!");
        } else {
            player.sendMessage(ChatColor.YELLOW + "Your title is [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "].");
        }
    }

    public void setTitle(Player player, String title) { //set player's title(-color)
        FileManager.getPlFile().set(player.getUniqueId() + ".title.title", title);
        FileManager.getPlFile().set(player.getUniqueId() + ".title.color", "RESET");
        FileManager.savePlFile();
        player.sendMessage(ChatColor.YELLOW + "Set the title to [" + ChatColor.RESET + title + ChatColor.YELLOW + "].");
    }

    public void setTitle(Player player, String title, String titleColor) { //set player's title(+color)
        FileManager.getPlFile().set(player.getUniqueId() + ".title.title", title);
        FileManager.getPlFile().set(player.getUniqueId() + ".title.color", titleColor.toUpperCase());
        FileManager.savePlFile();
        player.sendMessage(ChatColor.YELLOW + "Set the title to [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "].");
    }

    public void setTitle(Player player, String tg, String title, String titleColor) { //set target's title(+color)
        if (player.hasPermission("default.op")) {
            Player target = Bukkit.getPlayer(tg);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "The player of " + tg + " does not exist!");
            } else if (target == player) {
                setTitle(player, title, titleColor);
            } else {
                FileManager.getPlFile().set(target.getUniqueId() + ".title.title", title);
                FileManager.getPlFile().set(target.getUniqueId() + ".title.color", titleColor.toUpperCase());
                FileManager.savePlFile();
                player.sendMessage(ChatColor.YELLOW + "You set the title of " + target.getName() + " to [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "].");
                target.sendMessage(ChatColor.YELLOW + player.getName() + " has set your title to [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "].");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
        }
    }

    public void delTitle(Player player) { //delete player's title
        String title = FileManager.getPlFile().getString(player.getUniqueId() + ".title.title");
        String titleColor = FileManager.getPlFile().getString(player.getUniqueId() + ".title.color");
        if (!title.equals("title_is_not_set")) {
            FileManager.getPlFile().set(player.getUniqueId() + ".title.title", "title_is_not_set");
            FileManager.getPlFile().set(player.getUniqueId() + ".title.color", "title_color_is_not_set");
            FileManager.savePlFile();
            player.sendMessage(ChatColor.YELLOW + "The title has been deleted. [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
        } else if (title.equals("title_is_not_set")) {
            player.sendMessage(ChatColor.YELLOW + "The title has already been deleted!");
        }
    }

    public void delTitle(Player player, String tg) { //delete target's title
        if (player.hasPermission("default.op")) {
            Player target = Bukkit.getPlayer(tg);
            String title = FileManager.getPlFile().getString(target.getUniqueId() + ".title.title");
            String titleColor = FileManager.getPlFile().getString(target.getUniqueId() + ".title.color");
            if (target == null) {
                player.sendMessage(ChatColor.RED + "The player of " + tg + " does not exist!");
            } else if (target == player) {
                delTitle(player);
            } else {
                if (!title.equals("title_is_not_set")) {
                    FileManager.getPlFile().set(target.getUniqueId() + ".title.title", "title_is_not_set");
                    FileManager.getPlFile().set(target.getUniqueId() + ".title.color", "title_color_is_not_set");
                    FileManager.savePlFile();
                    player.sendMessage(ChatColor.YELLOW + target.getName() + "'s title was removed. [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
                    target.sendMessage(ChatColor.YELLOW + player.getName() + " removed your title. [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
                } else if (title.equals("title_is_not_set")) {
                    player.sendMessage(ChatColor.YELLOW + "The title of " + target.getName() + " has already been deleted!");
                }
            }
        } else {
            player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
        }
    }

    //title(message format)
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String format = e.getFormat();
        try {
            if (PluginTools.isSettingOn("title")) {
                if (!getTitle(player).contains("title_is_not_set") && getTitle(player) != null) {
                    e.setFormat("[" + getTitle(player) + ChatColor.RESET + "] " + format);
                }
            }
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[BasicServerPlugin] Title application error: " + ex);
        }
    }
}