package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.gui.BS.BSPluginMenuGui;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BSCmd implements CommandExecutor {

    PluginTools PT = new PluginTools();
    BSPluginMenuGui BMG = new BSPluginMenuGui();

    ///command      args[0]
    ///command       args1
    ///   b    [reload/info/help]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) { //player command
            if (command.getName().equalsIgnoreCase("bs")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Type the command to execute.");
                } else if (args[0].equalsIgnoreCase("reload")) { ///plugin reload(config)
                    PT.BSPluginReload(sender);
                } else if (args[0].equals("menu")) {
                    BMG.openBSPluginMenu((Player) sender);
                } else if (args[0].equalsIgnoreCase("info")) { //plugin information
                    PT.BSPluginInfo(sender);
                } else if (args[0].equals("help")) { //plugin help
                    PT.BSPluginHelp(sender);
                } else if (args[0].equals("help.command")) { //plugin help(command)(+op)
                    PT.BSPluginCmdHelp(sender);
                } else {
                    sender.sendMessage(ChatColor.RED + "Command is not correct!");
                }
            }
        } else { //console command
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Type the command to execute.");
            } else if (args[0].equalsIgnoreCase("reload")) { ///plugin reload(config)
                PT.BSPluginReload(sender);
            } else if (args[0].equalsIgnoreCase("info")) { //plugin information
                PT.BSPluginInfo(sender);
            } else if (args[0].equals("help")) { //plugin help
                PT.BSPluginHelp(sender);
            } else {
                sender.sendMessage(ChatColor.RED + "Command is not correct!");
            }
        }
        return false;
    }
}