package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.manager.PrivateWarehouseManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PWCmd implements CommandExecutor {

    PrivateWarehouseManager PW = new PrivateWarehouseManager();


    ///command
    ///  pw

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("privatewarehouse")) {
                if (PluginTools.isSettingOn("privatewarehouse", player))
                    PW.openPW(player);
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}