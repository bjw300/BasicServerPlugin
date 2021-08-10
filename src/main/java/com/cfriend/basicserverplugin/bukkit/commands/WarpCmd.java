package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.gui.Warp.WarpListGui;
import com.cfriend.basicserverplugin.bukkit.api.manager.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCmd implements CommandExecutor {

    WarpManager WM = new WarpManager();
    WarpListGui WLG = new WarpListGui();


    ///command  args[0]   args[1]
    ///command   args1     args2
    /// warp   [set/del] [warpName]
    /// warp   [warpName]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //warp(command)
            if (command.getName().equalsIgnoreCase("warp")) {
                if (PluginTools.isSettingOn("warp", player)) {
                    try {
                        if (args.length == 0) { //open warp list command
                            WLG.openWarpList(player);
                        } else if (args.length == 1) { //move on warp point command
                            WM.warpPlayer(player, args[0]);
                        } else if (args[0].equalsIgnoreCase("set")) { //save warp command
                            if (args.length == 2) {
                                WM.setWarp(player, args[1]);
                            } else if (args.length == 5) {
                                try {
                                    WM.setWarp(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                                } catch (Exception e) {
                                    player.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                                    Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "Command is not correct!");
                            }
                        } else if (args[0].equalsIgnoreCase("del") && args.length == 2) { //delete warp command
                            WM.delWarp(player, args[1]);
                        } else if (args[0].equalsIgnoreCase("rename") && args.length == 3) {
                            WM.reNameWarp(player, args[1], args[2]);
                        } else {
                            player.sendMessage(ChatColor.RED + "Command is not correct!");
                        }
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Command is not correct!");
                        Bukkit.getLogger().warning("[BasicServerPlugin] Warp command error: " + e);
                    }
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}