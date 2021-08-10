package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard.SBManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SBCmd implements CommandExecutor {

    SBManager SM = new SBManager();


    ///command  args[0]
    ///command   args1
    ///  sb    [on/off]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //scoreboard on/off
            if (command.getName().equalsIgnoreCase("infoboard")) {
                if (PluginTools.isSettingOn("scoreboard", player)) {
                    if (args[0].equalsIgnoreCase("on")) {
                        SM.setScoreboard(player);
                        SM.SAStart(player);
                    } else if (args[0].equalsIgnoreCase("off")) {
                        SM.removeScoreboard(player);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Command is not correct!");
                    }
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}