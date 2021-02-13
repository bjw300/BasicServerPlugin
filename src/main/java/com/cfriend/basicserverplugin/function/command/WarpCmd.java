package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.function.manager.WarpManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCmd implements CommandExecutor {

    ///command  args[0]   args[1]
    ///command   args1     args2
    /// warp   [set/del] [warpName]
    /// warp   [warpName]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //warp(command)
            if (command.getName().equalsIgnoreCase("warp")) {
                if (args.length == 0) {
                    WarpManager.sendWarpList(p);
                } else if (args.length == 1) {
                    WarpManager.warpPlayer(p, args[0]);
                } else if (args.length == 2 && args[0].equals("set") || args.length == 2 && args[0].equals("save")) {
                    WarpManager.saveWarp(p, args[1]);
                } else if (args.length == 2 && args[0].equals("del")) {
                    WarpManager.delWarp(p, args[1]);
                } else {
                    p.sendMessage(ChatColor.RED + "Command is not correct!");
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}