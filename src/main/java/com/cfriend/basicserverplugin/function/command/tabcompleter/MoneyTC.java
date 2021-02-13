package com.cfriend.basicserverplugin.function.command.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MoneyTC implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //(command)money auto completion
            if (command.getName().equalsIgnoreCase("money")) {
                List<String> arguments = new ArrayList<String>();
                if (args.length == 1) {
                    if (p.hasPermission("default.op")) {
                        arguments.add("set");
                        arguments.add("add");
                        //argument return
                        List<String> result = new ArrayList<String>();
                        for (String a : arguments) {
                            if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                                result.add(a);
                        }
                        return result;
                    }
                } else if (args.length == 2) {
                    if (p.hasPermission("default.op")) {
                        List<String> playerNames = new ArrayList<>();
                        Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                        Bukkit.getOnlinePlayers().toArray(players);
                        for (int i = 0; i < players.length; i++) {
                            playerNames.add(players[i].getName());
                        }
                        //argument return
                        List<String> result = new ArrayList<String>();
                        for (String a : playerNames) {
                            if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                                result.add(a);
                        }
                        return result;
                    }
                } else if (args.length == 3) {
                    if (p.hasPermission("default.op")) {
                        arguments.add("<(money)>");
                    }
                }
            }
        }
        return null;
    }
}