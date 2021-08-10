package com.cfriend.basicserverplugin.bukkit.commands.tabcompleter;

import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpTC implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //(command)warp auto completion
            if (command.getName().equalsIgnoreCase("warp")) {
                List<String> arguments = new ArrayList<String>();
                if (args.length == 1) {
                    if (player.hasPermission("default.op")) {
                        arguments.add("set");
                        arguments.add("del");
                        arguments.add("rename");
                        String[] wl = new String[FileManager.getWpFile().getKeys(false).size()];
                        FileManager.getWpFile().getKeys(false).toArray(wl);
                        for (int i = 0; i < wl.length; i++) {
                            arguments.add(wl[i]);
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
                if (args[0].equalsIgnoreCase("del")) {
                    if (args.length == 2) {
                        if (player.hasPermission("default.op")) {
                            String[] wl = new String[FileManager.getWpFile().getKeys(false).size()];
                            FileManager.getWpFile().getKeys(false).toArray(wl);
                            for (int i = 0; i < wl.length; i++) {
                                arguments.add(wl[i]);
                            }
                            //argument return
                            List<String> result = new ArrayList<String>();
                            for (String a : arguments) {
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