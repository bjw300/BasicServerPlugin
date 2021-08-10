package com.cfriend.basicserverplugin.bukkit.api.manager;

import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WarpManager {

    @SuppressWarnings("deprecation")
    public void warpPlayer(Player player, String warp) { //tp to warp
        if (FileManager.getWpFile().get(warp) != null) {
            int x = FileManager.getWpFile().getInt(warp + ".x");
            int y = FileManager.getWpFile().getInt(warp + ".y");
            int z = FileManager.getWpFile().getInt(warp + ".z");
            float pitch = FileManager.getWpFile().getInt(warp + ".pitch");
            float yaw = FileManager.getWpFile().getInt(warp + ".yaw");
            String worldName = FileManager.getWpFile().getString(warp + ".world");
            Location warpLoc = new Location(Bukkit.getWorld(worldName),x,y,z,yaw,pitch);
            player.teleport(warpLoc);
            player.sendTitle("", ChatColor.YELLOW + "Moved to '" + ChatColor.GOLD + warp + ChatColor.YELLOW + "'.");
        } else if (FileManager.getWpFile().get(warp) == null) {
            player.sendMessage(ChatColor.YELLOW + "The warp point doesn't exist!");
        }
    }

    public void setWarp(Player player, String warpName) { //save warp
        if (player.hasPermission("default.op")) {
            Location warpLoc = player.getLocation();
            FileManager.getWpFile().set(warpName + ".x", warpLoc.getBlockX());
            FileManager.getWpFile().set(warpName + ".y", warpLoc.getBlockY());
            FileManager.getWpFile().set(warpName + ".z", warpLoc.getBlockZ());
            FileManager.getWpFile().set(warpName + ".pitch", warpLoc.getPitch());
            FileManager.getWpFile().set(warpName + ".yaw", warpLoc.getYaw());
            FileManager.getWpFile().set(warpName + ".world", warpLoc.getWorld().getName());
            FileManager.getWpFile().set(warpName + ".other.icon", "default");
            FileManager.saveWpFile();
            player.sendMessage(ChatColor.YELLOW + "The location has been saved as '" + warpName + "'. [" + ChatColor.RESET + "x:" + warpLoc.getBlockX() + " y:" + warpLoc.getBlockY() + " z:" + warpLoc.getBlockZ() + ChatColor.YELLOW + "]");
        } else {
            player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
        }
    }

    public void setWarp(String warpName, Integer x, Integer y, Integer z) { //save warp(both in common)
        FileManager.getWpFile().set(warpName + ".x", x);
        FileManager.getWpFile().set(warpName + ".y", y);
        FileManager.getWpFile().set(warpName + ".z", z);
        FileManager.getWpFile().set(warpName + ".pitch", 0);
        FileManager.getWpFile().set(warpName + ".yaw", 0);
        FileManager.getWpFile().set(warpName + ".world", "world");
        FileManager.getWpFile().set(warpName + ".other.icon", "default");
        FileManager.saveWpFile();
        Bukkit.getLogger().info("[BasicServerPlugin] " + ChatColor.YELLOW + "The location has been saved as '" + warpName + "'. [" + ChatColor.RESET + "x:" + x + " y:" + y + " z:" + z + ChatColor.YELLOW + "]");
    }

    public void delWarp(Player player, String warp) { //delete warp
        if (player.hasPermission("default.op")) {
            if (FileManager.getWpFile().get(warp) != null) {
                FileManager.getWpFile().set(warp, null);
                FileManager.saveWpFile();
                player.sendMessage(ChatColor.YELLOW + "The location of '" + warp + "' has been deleted.");
            } else if (FileManager.getWpFile().get(warp) == null) {
                player.sendMessage(ChatColor.YELLOW + "The warp point doesn't exist!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
        }
    }

    public void delWarp(String warp) { //delete warp(console)
        if (FileManager.getWpFile().get(warp) != null) {
            FileManager.getWpFile().set(warp, null);
            FileManager.saveWpFile();
            Bukkit.getLogger().info("[BasicServerPlugin] " + ChatColor.YELLOW + "The location of '" + warp + "' has been deleted.");
        } else if (FileManager.getWpFile().get(warp) == null) {
            Bukkit.getLogger().info("[BasicServerPlugin] " + ChatColor.YELLOW + "The warp point doesn't exist!");
        }
    }

    public void reNameWarp(Player player, String warp, String newWarpName) { //re-name warp
        if (player.hasPermission("default.op")) {
            if (FileManager.getWpFile().get(newWarpName) != null) {
                player.sendMessage(ChatColor.YELLOW + "There is already a warp '" + newWarpName + "' that you want to change.");
            } else if (FileManager.getWpFile().get(warp) != null) {
                FileManager.getWpFile().set(newWarpName + ".x", FileManager.getWpFile().getInt(warp + ".x"));
                FileManager.getWpFile().set(newWarpName + ".y", FileManager.getWpFile().getInt(warp + ".y"));
                FileManager.getWpFile().set(newWarpName + ".z", FileManager.getWpFile().getInt(warp + ".z"));
                FileManager.getWpFile().set(newWarpName + ".pitch", FileManager.getWpFile().getInt(warp + ".pitch"));
                FileManager.getWpFile().set(newWarpName + ".yaw", FileManager.getWpFile().getInt(warp + ".yaw"));
                FileManager.getWpFile().set(newWarpName + ".world", FileManager.getWpFile().getString(warp + ".world"));
                FileManager.getWpFile().set(newWarpName + ".other.icon", FileManager.getWpFile().getString(warp + ".other.icon"));
                FileManager.getWpFile().set(warp, null);
                FileManager.saveWpFile();
                player.sendMessage(ChatColor.YELLOW + "Warp '" + warp + "' has been renamed to '" + newWarpName + "'.");
            } else if (FileManager.getWpFile().get(warp) == null) {
                player.sendMessage(ChatColor.YELLOW + "The warp point doesn't exist!");
            }
        } else {
            player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
        }
    }

    public void sendWarpList(Player player) { //send warp list
        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Warp point list");
        for(String warp : FileManager.getWpFile().getKeys(false)) {
            TextComponent formatting = new TextComponent("- ");
            formatting.setColor(ChatColor.DARK_GRAY);
            TextComponent message = new TextComponent(warp);
            message.setColor(ChatColor.YELLOW);
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp " + warp));
            formatting.addExtra(message);
            player.spigot().sendMessage(formatting);
        }
    }
}