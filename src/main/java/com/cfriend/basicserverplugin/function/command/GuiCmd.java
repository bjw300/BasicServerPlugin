package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.function.gui.Menu;
import com.cfriend.basicserverplugin.function.gui.Setting;
import com.cfriend.basicserverplugin.function.gui.TaskMenu;
import com.cfriend.basicserverplugin.function.gui.WorldMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuiCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //server menu
            if (command.getName().equalsIgnoreCase("menu")) {
                if (args.length == 0) {
                    Menu.openMenu(p);
                }
                //server menu(provide)
                else if (args[0].equalsIgnoreCase("provide")) {
                    Menu.menuProvide(p);
                } else {
                    sender.sendMessage(ChatColor.RED + "Command usage: /menu [provide]");
                }
            }
            //task menu
            if (command.getName().equalsIgnoreCase("taskmenu")) {
                if (p.hasPermission("default.op")) {
                    if (args.length == 0) {
                        TaskMenu.openTaskMenu(p);
                    }
                    //task menu(provide)
                    else if (args[0].equalsIgnoreCase("provide")) {
                        TaskMenu.taskMenuProvide(p);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Command usage: /taskmenu [provide]");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
                }
            }
            //setting
            if (command.getName().equalsIgnoreCase("setting")) {
                if (args.length == 0) {
                    Setting.openSetting(p);
                }
            }
            //world menu
            if (command.getName().equalsIgnoreCase("worldmenu")) {
                if (args.length == 0) {
                    WorldMenu.openWorldMenu(p);
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}