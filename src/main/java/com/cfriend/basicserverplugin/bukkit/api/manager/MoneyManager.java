package com.cfriend.basicserverplugin.bukkit.api.manager;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard.SBManager;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MoneyManager {

    SBManager SM = new SBManager();


    public void showMoney(Player player) { //show player's money
        int money = FileManager.getPlFile().getInt(player.getUniqueId() + ".money");
        player.sendMessage(ChatColor.YELLOW + "You have " + money + "won.");
    }

    public void setMoney(Player player, Integer money) { //set player's money
        if (money >= 0) {
            try {
                FileManager.getPlFile().set(player.getUniqueId() + ".money", money);
                FileManager.savePlFile();
                if (PluginTools.isSettingOn("scoreboard")) {
                    SM.setScoreboard(player);
                }
                player.sendMessage(ChatColor.YELLOW + "Set your money at " + money + "won.");
            } catch (Exception e) {
                Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
            }
        } else {
            player.sendMessage(ChatColor.RED + "You cannot add negative numbers to money!");
        }
    }

    public void setMoney(Player player, String tg, Integer money) { //set target's money
        Player target = Bukkit.getPlayer(tg);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "The player of " + tg + " does not exist!");
        } else if (target == player) {
            setMoney(player, money);
        } else {
            if (money >= 0) {
                try {
                    FileManager.getPlFile().set(target.getUniqueId() + ".money", money);
                    FileManager.savePlFile();
                    if (PluginTools.isSettingOn("scoreboard")) {
                        SM.setScoreboard(target);
                    }
                    player.sendMessage(ChatColor.YELLOW + "You have set " + target.getName() + "'s money to " + money + "won.");
                } catch (Exception e) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
                }
            } else {
                player.sendMessage(ChatColor.RED + "You cannot add negative numbers to money!");
            }
        }
    }

    public void addMoney(Player player, Integer addmoney) { //add money from existing money
        if (addmoney > 0) {
            try {
                int m = FileManager.getPlFile().getInt(player.getUniqueId() + ".money");
                int money = m + addmoney;
                FileManager.getPlFile().set(player.getUniqueId() + ".money", money);
                FileManager.savePlFile();
                if (PluginTools.isSettingOn("scoreboard")) {
                    SM.setScoreboard(player);
                }
                player.sendMessage(ChatColor.YELLOW + "" + addmoney + "won is added to your existing balance of " + m + "won, so the total balance is " + money + "won.");
            } catch (Exception e) {
                Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
            }
        } else {
            player.sendMessage(ChatColor.RED + "You cannot add negative numbers to money!");
        }
    }

    public void addMoney(Player player, String tg, Integer addmoney) { //add money from existing money
        Player target = Bukkit.getPlayer(tg);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "The player of " + tg + " does not exist!");
        } else if (target == player) {
            addMoney(player, addmoney);
        } else if (addmoney > 0) {
            try {
                int m = FileManager.getPlFile().getInt(target.getUniqueId() + ".money");
                int money = m + addmoney;
                FileManager.getPlFile().set(target.getUniqueId() + ".money", money);
                FileManager.savePlFile();
                if (PluginTools.isSettingOn("scoreboard")) {
                    SM.setScoreboard(target);
                }
                target.sendMessage(ChatColor.YELLOW + "" + addmoney + "won is added to your existing balance of " + m + "won, so the total balance is " + money + "won.");
                player.sendMessage(ChatColor.YELLOW + "The balance of " + target.getName() + " is " + m + "won, and " + addmoney + "won is added, so the total balance is " + money + "won.");
            } catch (Exception e) {
                Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
            }
        } else if (addmoney <= 0) {
            player.sendMessage(ChatColor.RED + "You cannot add negative numbers to money!");
        }
    }

    public void subtractMoney(Player player, Integer subtractMoney) { //subtract money from existing money
        int m = FileManager.getPlFile().getInt(player.getUniqueId() + ".money");
        if (m >= subtractMoney) {
            try {
                int money = m - subtractMoney;
                FileManager.getPlFile().set(player.getUniqueId() + ".money", money);
                FileManager.savePlFile();
                if (PluginTools.isSettingOn("scoreboard")) {
                    SM.setScoreboard(player);
                }
                player.sendMessage(ChatColor.YELLOW + "The balance of " + player.getName() + " is " + m + "won, and " + subtractMoney + "won is deducted, so the total balance is " + money + "won.");
            } catch (Exception e) {
                Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
            }
        } else {
            player.sendMessage(ChatColor.RED + "you don't have enough money.");
        }
    }

    public boolean checkMoney(Player player, Integer subtractMoney) { //A method that returns true if there is as much money as subtract money, and false if there is no money
        int m = FileManager.getPlFile().getInt(player.getUniqueId() + ".money");
        if (m >= subtractMoney) {
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "you don't have enough money.");
            return false;
        }
    }
}