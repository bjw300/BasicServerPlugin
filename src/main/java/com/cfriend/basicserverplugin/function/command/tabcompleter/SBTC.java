package com.cfriend.basicserverplugin.function.command.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SBTC implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //(command)sb auto completion
            if (command.getName().equalsIgnoreCase("sb")) {
                List<String> arguments = new ArrayList<String>();
                if (args.length == 1) {
                    arguments.add("on");
                    arguments.add("off");
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