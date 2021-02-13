package com.cfriend.basicserverplugin.function.manager;

import com.cfriend.basicserverplugin.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TitleManager implements Listener {

    public static void showTitle(Player p) { //show p's title
        String title = FileManager.getPlFile().getString(p.getUniqueId() + ".title");
        String titleColor = FileManager.getPlFile().getString(p.getUniqueId() + ".title-color");
        if (title.equals("title_is_not_set")) {
            p.sendMessage(ChatColor.YELLOW + "칭호가 설정되지 않았습니다!");
        } else {
            p.sendMessage(ChatColor.YELLOW + "당신의 칭호는 [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "] 입니다");
        }
    }

    public static void setTitle(Player p, String title) { //set p's title(-color)
        FileManager.getPlFile().set(p.getUniqueId() + ".title", title);
        FileManager.getPlFile().set(p.getUniqueId() + ".title-color", "RESET");
        FileManager.savePlFile();
        p.sendMessage(ChatColor.YELLOW + "칭호를 설정하였습니다 [" + ChatColor.RESET + title + ChatColor.YELLOW + "]");
    }

    public static void setTitle(Player p, String title, String titleColor) { //set p's title(+color)
        FileManager.getPlFile().set(p.getUniqueId() + ".title", title);
        FileManager.getPlFile().set(p.getUniqueId() + ".title-color", titleColor.toUpperCase());
        FileManager.savePlFile();
        p.sendMessage(ChatColor.YELLOW + "칭호를 설정하였습니다 [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
    }

    public static void setTitle(Player p, String tg, String title, String titleColor) { //set target's title(+color)
        if (p.hasPermission("default.op")) {
            Player target = Bukkit.getPlayer(tg);
            if (target == null) {
                p.sendMessage(ChatColor.RED + tg + "의 플레이어가 존재하지 않습니다!");
            } else if (target == p) {
                setTitle(p, title, titleColor);
            } else {
                FileManager.getPlFile().set(target.getUniqueId() + ".title", title);
                FileManager.getPlFile().set(target.getUniqueId() + ".title-color", titleColor.toUpperCase());
                FileManager.savePlFile();
                p.sendMessage(ChatColor.YELLOW + target.getName() + "님의 칭호를 설정하였습니다 [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
                target.sendMessage(ChatColor.YELLOW + p.getName() + "님이 당신의 칭호를 설정하였습니다 [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
            }
        } else {
            p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
        }
    }

    public static void delTitle(Player p) { //delete p's title
        String title = FileManager.getPlFile().getString(p.getUniqueId() + ".title");
        String titleColor = FileManager.getPlFile().getString(p.getUniqueId() + ".title-color");
        if (!title.equals("title_is_not_set")) {
            FileManager.getPlFile().set(p.getUniqueId() + ".title", "title_is_not_set");
            FileManager.getPlFile().set(p.getUniqueId() + ".title-color", "title_color_is_not_set");
            FileManager.savePlFile();
            p.sendMessage(ChatColor.YELLOW + "칭호를 삭제하였습니다 [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
        } else if (title.equals("title_is_not_set")) {
            p.sendMessage(ChatColor.YELLOW + "이미 칭호가 삭제되었습니다!");
        }
    }

    public static void delTitle(Player p, String tg) { //delete target's title
        if (p.hasPermission("default.op")) {
            Player target = Bukkit.getPlayer(tg);
            String title = FileManager.getPlFile().getString(target.getUniqueId() + ".title");
            String titleColor = FileManager.getPlFile().getString(target.getUniqueId() + ".title-color");
            if (target == null) {
                p.sendMessage(ChatColor.RED + tg + "의 플레이어가 존재하지 않습니다!");
            } else if (target == p) {
                delTitle(p);
            } else {
                if (!title.equals("title_is_not_set")) {
                    FileManager.getPlFile().set(target.getUniqueId() + ".title", "title_is_not_set");
                    FileManager.getPlFile().set(target.getUniqueId() + ".title-color", "title_color_is_not_set");
                    FileManager.savePlFile();
                    p.sendMessage(ChatColor.YELLOW + target.getName() + "님의 칭호를 삭제하였습니다 [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
                    target.sendMessage(ChatColor.YELLOW + p.getName() + "님이 당신의 칭호를 삭제하였습니다 [" + ChatColor.valueOf(titleColor) + title + ChatColor.YELLOW + "]");
                } else if (title.equals("title_is_not_set")) {
                    p.sendMessage(ChatColor.YELLOW + target.getName() + "의 칭호가 이미 삭제되었습니다!");
                }
            }
        } else {
            p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
        }
    }

    //title(message format)
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String format = e.getFormat();
        String title = FileManager.getPlFile().getString(p.getUniqueId() + ".title");
        String titleColor = FileManager.getPlFile().getString(p.getUniqueId() + ".title-color");
        try {
            if (!title.equals("title_is_not_set") && !titleColor.equals("title_color_is_not_set")) {
                e.setFormat("[" + ChatColor.valueOf(titleColor) + title + ChatColor.RESET + "] " + format);
            }
        } catch (Exception ex) {
            System.out.println("[BasicServerPlugin] " + ChatColor.RED + "Title application error: " + ex);
        }
    }
}