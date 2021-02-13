package com.cfriend.basicserverplugin.function.manager;

import com.cfriend.basicserverplugin.util.FileManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WarpManager {

    public static void warpPlayer(Player p, String warp) { //tp to warp
         if (FileManager.getWpFile().get(warp) != null) {
             int x = FileManager.getWpFile().getInt(warp + ".x");
             int y = FileManager.getWpFile().getInt(warp + ".y");
             int z = FileManager.getWpFile().getInt(warp + ".z");
             float pitch = FileManager.getWpFile().getInt(warp + ".pitch");
             float yaw = FileManager.getWpFile().getInt(warp + ".yaw");
             String worldName = FileManager.getWpFile().getString(warp + ".world");
             Location warpLoc = new Location(Bukkit.getWorld(worldName),x,y,z,yaw,pitch);
             p.teleport(warpLoc);
             p.sendTitle(ChatColor.GOLD + "'" + warp + "'" + ChatColor.YELLOW + " 으로", ChatColor.YELLOW + "이동하였습니다!");
         } else if (FileManager.getWpFile().get(warp) == null) {
            p.sendMessage(ChatColor.YELLOW + "워프 지점이 존재하지 않습니다!");
         }
    }

    public static void saveWarp(Player p, String warpName) { //save warp
        if (p.hasPermission("default.op")) {
            Location warpLoc = p.getLocation();
            FileManager.getWpFile().set(warpName + ".x", warpLoc.getBlockX());
            FileManager.getWpFile().set(warpName + ".y", warpLoc.getBlockY());
            FileManager.getWpFile().set(warpName + ".z", warpLoc.getBlockZ());
            FileManager.getWpFile().set(warpName + ".pitch", warpLoc.getPitch());
            FileManager.getWpFile().set(warpName + ".yaw", warpLoc.getYaw());
            FileManager.getWpFile().set(warpName + ".world", warpLoc.getWorld().getName());
            FileManager.saveWpFile();
            p.sendMessage(ChatColor.YELLOW + "'" + warpName + "' 으로 위치가 저장되었습니다! [" + ChatColor.RESET + "x:" + warpLoc.getBlockX() + " y:" + warpLoc.getBlockY() + " z:" + warpLoc.getBlockZ() + ChatColor.YELLOW + "]");
        } else {
            p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
        }
    }

    public static void delWarp(Player p, String warp) { //delete warp
        if (p.hasPermission("default.op")) {
            if (FileManager.getWpFile().get(warp) != null) {
                FileManager.getWpFile().set(warp, null);
                FileManager.saveWpFile();
                p.sendMessage(ChatColor.YELLOW + "'" + warp + "' 의 위치를 삭제 하였습니다!");
            } else if (FileManager.getWpFile().get(warp) == null) {
                p.sendMessage(ChatColor.YELLOW + "워프 지점이 존재하지 않습니다!");
            }
        } else {
            p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
        }
    }

    public static void sendWarpList(Player p) { //send warp list
        p.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "워프 목록");
        for(String warp : FileManager.getWpFile().getKeys(false)) {
            TextComponent formatting = new TextComponent("- ");
            formatting.setColor(ChatColor.DARK_GRAY);
            TextComponent message = new TextComponent(warp);
            message.setColor(ChatColor.YELLOW);
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp " + warp));
            formatting.addExtra(message);
            p.spigot().sendMessage(formatting);
        }
    }
}