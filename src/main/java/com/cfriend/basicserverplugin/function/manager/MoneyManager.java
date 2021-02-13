package com.cfriend.basicserverplugin.function.manager;

import com.cfriend.basicserverplugin.function.manager.scoreboard.SBManager;
import com.cfriend.basicserverplugin.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MoneyManager {

    public static void showMoney(Player p) { //show p's money
        int money = FileManager.getPlFile().getInt(p.getUniqueId() + ".money");
        p.sendMessage(ChatColor.YELLOW + "" + money + "원이 있습니다.");
    }

    public static void setMoney(Player p, String m) { //set p's money
        if (p.hasPermission("default.op")) {
            try {
                int money = Integer.parseInt(m);
                FileManager.getPlFile().set(p.getUniqueId() + ".money", money);
                FileManager.savePlFile();
                SBManager.setScoreboard(p);
                p.sendMessage(ChatColor.YELLOW + "당신의 돈을 " + money + "원 으로 설정하였습니다.");
            } catch (Exception e) {
                p.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                System.out.println("[BasicServerPlugin] " + ChatColor.RED + "money error: " + e);
            }
        } else {
            p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
        }
    }

    public static void setMoney(Player p, String tg, String m) { //set target's money
        Player target = Bukkit.getPlayer(tg);
        if (target.hasPermission("default.op")) {
            if (target == null) {
                p.sendMessage(ChatColor.RED + tg + "의 플레이어가 존재하지 않습니다!");
            } else if (target == p) {
                setMoney(p, m);
            } else {
                try {
                    int money = Integer.parseInt(m);
                    FileManager.getPlFile().set(target.getUniqueId() + ".money", money);
                    FileManager.savePlFile();
                    SBManager.setScoreboard(target);
                    p.sendMessage(ChatColor.YELLOW + target.getName() + "님의 돈을 " + money + "원 으로 설정하였습니다.");
                } catch (Exception e) {
                    p.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                    System.out.println("[BasicServerPlugin] " + ChatColor.RED + "money error: " + e);
                }
            }
        } else {
            target.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
        }
    }

    public static void plusMoney(Player p, String tg, String plusm) { //add money from existing money
        Player target = Bukkit.getPlayer(tg);
        if (target.hasPermission("default.op")) {
            if (target == null) {
                p.sendMessage(ChatColor.RED + tg + "의 플레이어가 존재하지 않습니다!");
            } else if (target == p) {
                try {
                    int plusmoney = Integer.parseInt(plusm);
                    int m = FileManager.getPlFile().getInt(p.getUniqueId() + ".money");
                    int money = m + plusmoney;
                    FileManager.getPlFile().set(p.getUniqueId() + ".money", money);
                    FileManager.savePlFile();
                    SBManager.setScoreboard(p);
                    target.sendMessage(ChatColor.YELLOW + "당신의 돈 " + m + "원에서 " + plusmoney + "원이 추가되 총 " + money + "원 입니다.");
                } catch (Exception e) {
                    p.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                    System.out.println("[BasicServerPlugin] " + ChatColor.RED + "money error: " + e);
                }
            } else {
                try {
                    int plusmoney = Integer.parseInt(plusm);
                    int m = FileManager.getPlFile().getInt(target.getUniqueId() + ".money");
                    int money = m + plusmoney;
                    FileManager.getPlFile().set(target.getUniqueId() + ".money", money);
                    FileManager.savePlFile();
                    SBManager.setScoreboard(target);
                    target.sendMessage(ChatColor.YELLOW + "당신의 돈 " + m + "원에서 " + plusmoney + "원이 추가되 총 " + money + "원 입니다.");
                    p.sendMessage(ChatColor.YELLOW + target.getName() + "의 돈 " + m + "원에서 " + plusmoney + "원이 추가되 총 " + money + "원 입니다.");
                } catch (Exception e) {
                    p.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                    System.out.println("[BasicServerPlugin] " + ChatColor.RED + "money error: " + e);
                }
            }
        } else {
            target.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
        }
    }
}