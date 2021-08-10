package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.MenuGui;
import com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.PlayerSettingGui;
import com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.taskmenu.TaskMenuGui;
import com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.WorldMenuGui;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiCmd implements CommandExecutor {

    MenuGui MG = new MenuGui();
    TaskMenuGui TMG = new TaskMenuGui();
    PlayerSettingGui PSG = new PlayerSettingGui();
    WorldMenuGui WMG = new WorldMenuGui();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ///command  args[0]
            ///command   args1
            ///  menu  [provide]

            //server menu
            if (command.getName().equalsIgnoreCase("menu")) {
                if (args.length == 0) {
                    MG.openMenu(player);
                }
                //server menu(provide)
                else if (args[0].equalsIgnoreCase("provide")) {
                    MG.menuProvide(player);
                } else {
                    sender.sendMessage(ChatColor.RED + "Command usage: /menu [provide]");
                }
            }
            ///command  args[0]
            ///command   args1
            ///taskmenu [provide]

            //task menu
            if (command.getName().equalsIgnoreCase("taskmenu")) {
                if (player.hasPermission("default.op")) {
                    if (args.length == 0) {
                        TMG.openTaskMenu(player);
                    }
                    //task menu(provide)
                    else if (args[0].equalsIgnoreCase("provide")) {
                        TMG.taskMenuProvide(player);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Command usage: /taskmenu [provide]");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
                }
            }
            //setting
            if (command.getName().equalsIgnoreCase("setting")) {
                if (args.length == 0) {
                    PSG.openPlayerSetting(player);
                }
            }
            //world menu
            if (command.getName().equalsIgnoreCase("worldmenu")) {
                if (args.length == 0) {
                    WMG.openWorldMenu(player);
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}