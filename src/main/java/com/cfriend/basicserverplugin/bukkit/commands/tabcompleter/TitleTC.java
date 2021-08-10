package com.cfriend.basicserverplugin.bukkit.commands.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TitleTC implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //(command)title auto completion
            if (command.getName().equalsIgnoreCase("title")) {
                List<String> arguments = new ArrayList<String>();
                if (args.length == 1) {
                    arguments.add("set");
                    arguments.add("del");
                    //argument return
                    List<String> result = new ArrayList<String>();
                    for (String a : arguments) {
                        if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                            result.add(a);
                    }
                    return result;
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (args.length == 2) {
                        if (player.hasPermission("default.op")) {
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
                        if (Bukkit.getPlayer(args[1]) != Bukkit.getServer().getOnlinePlayers()) {
                            arguments.add("Red");arguments.add("Dark_Red");arguments.add("Dark_Green");arguments.add("Gold");
                            arguments.add("Yellow");arguments.add("Gray");arguments.add("Aqua");arguments.add("Dark_Aqua");
                            arguments.add("Dark_Blue");arguments.add("Blue");arguments.add("Dark_Purple");arguments.add("White");
                            arguments.add("Dark_Gray");arguments.add("Light_Purple");arguments.add("Black");
                            //argument return
                            List<String> result = new ArrayList<String>();
                            for (String a : arguments) {
                                if (a.toLowerCase().startsWith(args[2].toLowerCase()))
                                    result.add(a);
                            }
                            return result;
                        } else {
                            arguments.add("<title>");
                        }
                    } else if (args.length == 4) {
                        arguments.add("Red");arguments.add("Dark_Red");arguments.add("Dark_Green");arguments.add("Gold");
                        arguments.add("Yellow");arguments.add("Gray");arguments.add("Aqua");arguments.add("Dark_Aqua");
                        arguments.add("Dark_Blue");arguments.add("Blue");arguments.add("Dark_Purple");arguments.add("White");
                        arguments.add("Dark_Gray");arguments.add("Light_Purple");arguments.add("Black");
                        //argument return
                        List<String> result = new ArrayList<String>();
                        for (String a : arguments) {
                            if (a.toLowerCase().startsWith(args[3].toLowerCase()))
                                result.add(a);
                        }
                        return result;
                    }
                } else if (args[0].equalsIgnoreCase("del")) {
                    if (args.length == 2) {
                        if (player.hasPermission("default.op")) {
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
                    }
                }
            }
        }
        return null;
    }
}