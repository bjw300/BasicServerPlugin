package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.function.manager.MoneyManager;
import com.cfriend.basicserverplugin.util.FileManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCmd implements CommandExecutor {

    ///command  args[0]   args[1] args[2]
    ///command   args1     args2   args3
    /// money  [set/add] [player] [money] (op)
    /// money  [set/add] [money] (op)

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //money(command)
            if (command.getName().equals("money")) {
                if (FileManager.getPlFile().get(p.getUniqueId() + ".money") != null) {
                    if (args.length == 0) {
                        MoneyManager.showMoney(p);
                    } else if (args[0].equalsIgnoreCase("set")) {
                        if (args.length == 2) {
                            MoneyManager.setMoney(p, args[1]);
                        } else if (args.length == 3) {
                            MoneyManager.setMoney(p, args[1], args[2]);
                        } else {
                            sender.sendMessage(ChatColor.RED + "Command is not correct!");
                        }
                    } else if (args[0].equalsIgnoreCase("add")) {
                        if (args.length == 3) {
                            MoneyManager.plusMoney(p, args[1], args[2]);
                        } else {
                            sender.sendMessage(ChatColor.RED + "Command is not correct!");
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Command is not correct!");
                    }
                } else {
                    FileManager.getPlFile().set(p.getUniqueId() + ".money", 0);
                    p.sendMessage(ChatColor.YELLOW + "0원 으로 기본 돈이 설정되었습니다!");
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}