package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.Main;
import com.cfriend.basicserverplugin.function.manager.scoreboard.SBAMManager;
import com.cfriend.basicserverplugin.function.manager.scoreboard.SBManager;
import com.cfriend.basicserverplugin.util.FileManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BasicCmd implements CommandExecutor {

    private Main plugin;

    public BasicCmd(Main main) {
        plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //plugin tools
            if (command.getName().equalsIgnoreCase("b")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Type the command to execute.");
                } else if (args[0].equalsIgnoreCase("reload")) { ///plugin reload(config)
                    if (p.hasPermission("default.op")) {
                        FileManager.reloadPlFile();
                        FileManager.reloadWpFile();
                        FileManager.reloadPWFile();
                        plugin.reloadConfig();
                        plugin.saveDefaultConfig();
                        for (Player player : plugin.getServer().getOnlinePlayers()) {
                            SBAMManager board = new SBAMManager(player.getUniqueId());
                            if (board.hasID()) {
                                board.stop();
                            }
                            SBManager.setScoreboard(player);
                            SBManager.SAStart(player);
                        }
                        sender.sendMessage(ChatColor.YELLOW + "BSPlugin has been reloaded!");
                    } else {
                        p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
                    }
                } else if (args[0].equalsIgnoreCase("info")) { //plugin information
                    sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - info====");
                    sender.sendMessage(ChatColor.YELLOW + "This Plugin is Made By bjw300 and peanutexe");
                    sender.sendMessage(ChatColor.YELLOW + "version : 0.2.0");
                    sender.sendMessage(ChatColor.GOLD + "==============================");
                } else if (args[0].equals("help")) { //plugin help
                    TextComponent message = new TextComponent("command");
                    message.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/b help.command"));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.GOLD + "Click the text to show you command help!").create()));
                    sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - help====\n ");
                    sender.spigot().sendMessage(message);
                    sender.sendMessage(ChatColor.GOLD + "\n==============================");
                } else if (args[0].equals("help.command")) { //plugin help(command)(+op)
                    TextComponent cm = new TextComponent("> /b <help/info/reload>");cm.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "basicserverplugin tools").create()));
                    TextComponent cm2 = new TextComponent("> /sb [on/off]");cm2.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "scoreboard on/off").create()));
                    TextComponent cm3 = new TextComponent("> /warp <set/del> <(warp name)>");cm3.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "set/del the warp and tp").create()));
                    TextComponent cm4 = new TextComponent("> /title <set/del> [player] <(title)> [color]");cm4.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "set/del the title").create()));
                    TextComponent cm5 = new TextComponent("> /money <set/add> [player] <(money)>");cm5.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "money related command").create()));
                    TextComponent cm6 = new TextComponent("> /menu [provide]");cm6.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm6.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "open the menu,menu provide").create()));
                    TextComponent cm7 = new TextComponent("> /taskmenu [provide]");cm7.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm7.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "open the taskmenu,taskmenu provide(op)").create()));
                    TextComponent cm8 = new TextComponent("> /shop");cm8.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm8.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "open the shop").create()));
                    TextComponent cm9 = new TextComponent("> /setting");cm9.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm9.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "open the setting").create()));
                    TextComponent cm10 = new TextComponent("> /worldmenu");cm10.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
                    cm10.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "open the worldmenu").create()));
                    if (p.hasPermission("default.op")) {
                        sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - Command help====");
                        sender.sendMessage(ChatColor.YELLOW + "command:");
                        sender.spigot().sendMessage(cm);
                        sender.spigot().sendMessage(cm2);
                        sender.spigot().sendMessage(cm3);
                        sender.spigot().sendMessage(cm4);
                        sender.spigot().sendMessage(cm5);
                        sender.sendMessage(ChatColor.YELLOW + "gui command:");
                        sender.spigot().sendMessage(cm6);
                        sender.spigot().sendMessage(cm7);
                        sender.spigot().sendMessage(cm8);
                        sender.spigot().sendMessage(cm9);
                        sender.spigot().sendMessage(cm10);
                        sender.sendMessage(ChatColor.GRAY + "Hover over commands to see the description");
                        sender.sendMessage(ChatColor.GOLD + "======================================");
                    } else { //plugin help(command)(-op)
                        sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - Command help====");
                        sender.sendMessage(ChatColor.YELLOW + "command:");
                        sender.spigot().sendMessage(cm);
                        sender.spigot().sendMessage(cm2);
                        sender.spigot().sendMessage(cm5);
                        sender.spigot().sendMessage(cm4);
                        sender.sendMessage(ChatColor.YELLOW + "gui command:");
                        sender.spigot().sendMessage(cm6);
                        sender.spigot().sendMessage(cm8);
                        sender.spigot().sendMessage(cm9);
                        sender.spigot().sendMessage(cm10);
                        sender.sendMessage(ChatColor.GRAY + "Hover over commands to see the description");
                        sender.sendMessage(ChatColor.GOLD + "======================================");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Command is not correct!");
                }
                return true;
            }
            return false;
        } else {
            //plugin tools(console)
            if (command.getName().equalsIgnoreCase("b")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Type the command to execute.");
                } else if (args[0].equalsIgnoreCase("reload")) { ///plugin reload(config)
                    FileManager.reloadPlFile();
                    FileManager.reloadWpFile();
                    FileManager.reloadPWFile();
                    plugin.reloadConfig();
                    plugin.saveDefaultConfig();
                    for (Player player : plugin.getServer().getOnlinePlayers()) {
                        SBAMManager board = new SBAMManager(player.getUniqueId());
                        if (board.hasID()) {
                            board.stop();
                        }
                        SBManager.setScoreboard(player);
                        SBManager.SAStart(player);
                    }
                    sender.sendMessage(ChatColor.YELLOW + "BSPlugin has been reloaded!");
                } else if (args[0].equalsIgnoreCase("info")) { //plugin information
                    sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - info====");
                    sender.sendMessage(ChatColor.YELLOW + "This Plugin is Made By bjw300 and peanutexe");
                    sender.sendMessage(ChatColor.GOLD + "==================================");
                } else if (args[0].equals("help")) { //plugin help
                    sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - help====");
                    sender.sendMessage("command:\n> /b <help/info/reload>");
                    sender.sendMessage(ChatColor.GRAY + "And more console commands will be added!");
                    sender.sendMessage(ChatColor.GOLD + "==================================");
                } else {
                    sender.sendMessage(ChatColor.RED + "Command is not correct!");
                }
                return true;
            } else {
                sender.sendMessage("[BasicServerPlugin] not support console!");
            }
        }
        return false;
    }
}