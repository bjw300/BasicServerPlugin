package com.cfriend.basicserverplugin.bukkit.commands.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class BasicTC implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //(command)b auto completion
            if (command.getName().equalsIgnoreCase("b")) {
                List<String> arguments = new ArrayList<String>();
                if (args.length == 1) {
                    arguments.add("info");
                    arguments.add("help");
                    if (player.hasPermission("default.op")) {
                        arguments.add("reload");
                        arguments.add("menu");
                    }
                    //argument return
                    List<String> result = new ArrayList<String>();
                    for (String a : arguments) {
                        if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                            result.add(a);
                    }
                    return result;
                }
            }
        }
        return null;
    }
}