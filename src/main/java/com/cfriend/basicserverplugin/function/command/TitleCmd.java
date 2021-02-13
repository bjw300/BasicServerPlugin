package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.function.manager.TitleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TitleCmd implements CommandExecutor {

    ///command  args[0]   args[1] args[2] arg[3]
    ///command   args1     args2   args3   arg4
    /// title  [set/del] [player] [title] [color] (op)
    /// title  [set/del] [title]  [color]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //title(command)
            if (command.getName().equalsIgnoreCase("title")) {
                if (args.length == 0) {
                    TitleManager.showTitle(p);
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (args.length == 2) {
                        TitleManager.setTitle(p, args[1]);
                    } else if (args.length == 3) {
                        TitleManager.setTitle(p, args[1], args[2].toUpperCase());
                    } else if (args.length == 4) {
                        TitleManager.setTitle(p, args[1], args[2], args[3].toUpperCase());
                    } else {
                        p.sendMessage(ChatColor.RED + "Command is not correct!");
                    }
                } else if (args[0].equalsIgnoreCase("del")) {
                    if (args.length == 1) {
                        TitleManager.delTitle(p);
                    } else if (args.length == 2) {
                        TitleManager.delTitle(p, args[1]);
                    } else {
                        p.sendMessage(ChatColor.RED + "Command is not correct!");
                    }
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}