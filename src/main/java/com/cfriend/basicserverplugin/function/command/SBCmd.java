package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.function.manager.scoreboard.SBAMManager;
import com.cfriend.basicserverplugin.function.manager.scoreboard.SBManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SBCmd implements CommandExecutor {

    ///command  args[0]
    ///command   args1
    ///  sb    [on/off]

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //scoreboard on/off
            if (command.getName().equalsIgnoreCase("sb")) {
                SBAMManager board = new SBAMManager(p.getPlayer().getUniqueId());
                if (args[0].equalsIgnoreCase("on")) {
                    if (board.hasID())
                        board.stop();
                    p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                    SBManager.setScoreboard(p);
                    SBManager.SAStart(p);
                } else if (args[0].equalsIgnoreCase("off")) {
                    if (board.hasID())
                        board.stop();
                    p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                } else {
                    sender.sendMessage(ChatColor.RED + "Command is not correct!");
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}