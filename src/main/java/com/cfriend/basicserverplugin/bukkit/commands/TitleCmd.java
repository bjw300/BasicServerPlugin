package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.manager.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TitleCmd implements CommandExecutor {

    TitleManager TM = new TitleManager();


    ///command  args[0]   args[1] args[2] arg[3]
    ///command   args1     args2   args3   arg4
    /// title  [set/del] [player] [title] [color] (op)
    /// title  [set/del] [title]  [color]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //title(command)
            if (command.getName().equalsIgnoreCase("title")) {
                if (PluginTools.isSettingOn("title", player)) {
                    try {
                        if (args.length == 0) {
                            TM.showTitle(player);
                        } else if (args[0].equalsIgnoreCase("set")) {
                            if (args.length == 2) {
                                TM.setTitle(player, args[1]);
                            } else if (args.length == 3) {
                                TM.setTitle(player, args[1], args[2].toUpperCase());
                            } else if (args.length == 4) {
                                TM.setTitle(player, args[1], args[2], args[3].toUpperCase());
                            } else {
                                player.sendMessage(ChatColor.RED + "Command is not correct!");
                            }
                        } else if (args[0].equalsIgnoreCase("del")) {
                            if (args.length == 1) {
                                TM.delTitle(player);
                            } else if (args.length == 2) {
                                TM.delTitle(player, args[1]);
                            } else {
                                player.sendMessage(ChatColor.RED + "Command is not correct!");
                            }
                        }
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Command is not correct!");
                        Bukkit.getLogger().warning("[BasicServerPlugin] Title command error: " + e);
                    }
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}