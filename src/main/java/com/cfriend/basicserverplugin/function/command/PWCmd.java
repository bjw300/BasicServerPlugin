package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.function.manager.PrivateWarehouseManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PWCmd implements CommandExecutor {

    ///command
    ///  pw

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (command.getName().equalsIgnoreCase("pw")) {
                PrivateWarehouseManager.openPW(p);
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}