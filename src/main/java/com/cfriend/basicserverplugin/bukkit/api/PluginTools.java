package com.cfriend.basicserverplugin.bukkit.api;

import com.cfriend.basicserverplugin.bukkit.Main;
import com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard.SBManager;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginTools {

    SBManager SM = new SBManager();

    public void BSPluginReload(CommandSender sender) { //plugin files reload(player/console)
        if (sender instanceof Player) {
            if (sender.hasPermission("default.op")) {
                Main.getInstance().reloadConfig();
                FileManager.reloadPlFile();
                FileManager.reloadWpFile();
                FileManager.reloadPWFile();
                if (isSettingOn("scoreboard")) {
                    SM.reloadScoreboard();
                }
                sender.sendMessage(ChatColor.YELLOW + "BSPlugin has been reloaded!");
            } else {
                sender.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
            }
        } else {
            Main.getInstance().reloadConfig();
            FileManager.reloadPlFile();
            FileManager.reloadWpFile();
            FileManager.reloadPWFile();
            if (isSettingOn("scoreboard")) {
                SM.reloadScoreboard();
            }
            sender.sendMessage("[BasicServerPlugin] " + ChatColor.YELLOW + "BSPlugin has been reloaded!");
        }
    }

    public void BSPluginInfo(CommandSender sender) { //plugin info(both in common)
        sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - info====");
        sender.sendMessage(ChatColor.YELLOW + "This Plugin is Made By bjw300 and peanutexe");
        sender.sendMessage(ChatColor.YELLOW + "version : 0.2.1.1 alpha");
        sender.sendMessage(ChatColor.GOLD + "==============================");
    }

    public void BSPluginHelp(CommandSender sender) { //plugin help(player/console)
        if (sender instanceof Player) {
            TextComponent message = new TextComponent("command");
            message.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/b help.command"));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.GOLD + "Click the text to show you command help!").create()));
            sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - help====\n ");
            sender.spigot().sendMessage(message);
            sender.sendMessage(ChatColor.GOLD + "\n==============================");
        } else {
            sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - help====");
            sender.sendMessage("command:\n> /b <help/info/reload>");
            sender.sendMessage(ChatColor.GRAY + "And more console commands will be added!");
            sender.sendMessage(ChatColor.GOLD + "==================================");
        }
    }

    public void BSPluginCmdHelp(CommandSender sender) { //plugin cmdHelp(player)
        if (sender instanceof Player) {
            TextComponent sign = new TextComponent(ChatColor.GRAY + "How to read command sign");sign.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            sign.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "<>: Required command\n[]: Commands that may or may not be entered\n(): Input value or additional information\n[player]: Target this player, but requires permission unless you are yourself").create()));
            TextComponent cm = new TextComponent("> /bs");cm.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: basicserverplugin tools\naliases: [b,bsplugin]\nusage:\n  > /b <help/info/reload/menu>(op)").create()));
            TextComponent cm2 = new TextComponent("> /infoboard");cm2.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: on/off informationboard\naliases: [ib,informationboard,sb,bscoreboard]\nusage:\n  > /informationboard <on/off>").create()));
            TextComponent cm3 = new TextComponent("> /warp");cm3.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: tp/edit the warp\naliases: [bwarp]\nusage:\n  > /warp [(warp name)](op)\n  > /warp <set/del> <(warp name)>(op)\n  > /warp <rename> <(old warp name)> <(new warp name)>(op)").create()));
            TextComponent cm4 = new TextComponent("> /title");cm4.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: edit the title\naliases: [btitle]\nusage:\n  > /title\n  > /title <set> [player] <(title)> [color]\n  > /title <del> [player]").create()));
            TextComponent cm5 = new TextComponent("> /money");cm5.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: money related command\naliases: [bmoney]\nusage:\n  > /money\n  > /money <set/add> [player] <(money)>(op)\n  > /money <subtract> <(money)>(op)").create()));
            TextComponent cm6 = new TextComponent("> /privatewarehouse");cm6.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm6.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: open private warehouse\naliases: [pw,privatevault]\nusage:\n  > /privatewarehouse").create()));
            TextComponent cm7 = new TextComponent("> /menu");cm7.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm7.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: menu open/provide\naliases: [m,bmenu]\nusage:\n  > /menu [provide]").create()));
            TextComponent cm8 = new TextComponent("> /taskmenu");cm8.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm8.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: taskmenu open/provide\naliases: [tmenu,btaskmenu,adminmenu]\nusage:\n  > /taskmenu [provide](op)").create()));
            /*TextComponent cm9 = new TextComponent("> /shop");cm9.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm9.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: open shop\naliases: \nusage:\n  > /shop").create()));*/
            TextComponent cm10 = new TextComponent("> /setting");cm10.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm10.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: open setting\naliases: [bsetting]\nusage:\n  > /setting").create()));
            TextComponent cm11 = new TextComponent("> /worldmenu");cm11.setColor(net.md_5.bungee.api.ChatColor.YELLOW);
            cm11.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(net.md_5.bungee.api.ChatColor.YELLOW + "description: open worldmenu\naliases: [bworldmenu]\nusage:\n  > /worldmenu").create()));
            if (sender.hasPermission("default.op")) {
                sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - Command help====");
                sender.spigot().sendMessage(sign);
                sender.sendMessage(ChatColor.YELLOW + "command:");
                sender.spigot().sendMessage(cm);
                sender.spigot().sendMessage(cm2);
                sender.spigot().sendMessage(cm3);
                sender.spigot().sendMessage(cm4);
                sender.spigot().sendMessage(cm5);
                sender.spigot().sendMessage(cm6);
                sender.spigot().sendMessage(cm7);
                sender.spigot().sendMessage(cm8);
                //sender.spigot().sendMessage(cm9);
                sender.spigot().sendMessage(cm10);
                sender.spigot().sendMessage(cm11);
                sender.sendMessage(ChatColor.GRAY + "Hover over commands to see the description");
                sender.sendMessage(ChatColor.GOLD + "======================================");
            } else { //plugin help(command)(-op)
                sender.sendMessage(ChatColor.GOLD + "====Basic Server Plugin - Command help====");
                sender.spigot().sendMessage(sign);
                sender.sendMessage(ChatColor.YELLOW + "command:");
                sender.spigot().sendMessage(cm2);
                sender.spigot().sendMessage(cm5);
                sender.spigot().sendMessage(cm4);
                sender.spigot().sendMessage(cm6);
                sender.spigot().sendMessage(cm7);
                //sender.spigot().sendMessage(cm9);
                sender.spigot().sendMessage(cm10);
                sender.spigot().sendMessage(cm11);
                sender.sendMessage(ChatColor.GRAY + "Hover over commands to see the description");
                sender.sendMessage(ChatColor.GOLD + "======================================");
            }
        } else {
            Bukkit.getLogger().info("[BasicServerPlugin] not support console!");
        }
    }

    public String getServerVersion() { //Returns the server version
        String sversion = "N/A";
        try {
            sversion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            //
        }
        return sversion;
    }

    public boolean versionComparison(String comparisonVersion) { //Compare the server version and the entered version(Format: ex:1.17+ = v1_17_R1~3)
        try {
            String sversion = getServerVersion()
                    .replace("v", "")
                    .replace("_", "")
                    .replace("R", "");
            comparisonVersion = comparisonVersion
                    .replace("v", "")
                    .replace("_", "")
                    .replace("R", "");
            if (sversion.equals("N/A")) {
                Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't get server version");
                return false;
            } else if (Integer.parseInt(sversion) >= Integer.parseInt(comparisonVersion)) {
                return true;
            } else if (Integer.parseInt(sversion) < Integer.parseInt(comparisonVersion)) {
                return false;
            }
        } catch (Exception e) {
            //Bukkit.getLogger().warning("[BasicServerPlugin] ");
            return false;
        }
        return false;
    }

    public static boolean isSettingOn(String function) { //Compare whether the setting value of 'Function' is on or off
        try {
            if (FileManager.getConfig().getConfigurationSection("function_setting").get(function) != null) {
                if (FileManager.getConfig().getString("function_setting." + function).equals("on")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (FileManager.getConfig().getConfigurationSection("function_setting").get(function) == null) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] '" + function + "' is a function that does not exist");
                    return false;
                } else if (FileManager.getConfig().get("function_setting") == null) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] The '" + function + "' configuration path does not exist in the config file");
                    return false;
                } else if (FileManager.getConfig() == null) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] config file does not exist");
                    return false;
                }
                return false;
            }
        } catch (Exception e) {
            Bukkit.getLogger().warning("[BasicServerPlugin] Failed to load '" + function + "' settings");
            return false;
        }
    }

    public static boolean isSettingOn(String function, Player player) { //Compare whether the setting value of 'Function' is on or off(+send warning text to player)
        try {
            if (FileManager.getConfig().getConfigurationSection("function_setting").get(function) != null) {
                if (FileManager.getConfig().getString("function_setting." + function).equals("on")) {
                    return true;
                } else {
                    if (player.isOp()) {
                        player.sendMessage(ChatColor.RED + "[BasicServerPlugin] This feature is not enabled. Turn on this feature to use!");
                        Bukkit.getLogger().warning("[BasicServerPlugin] You have accessed a '" + function + "' for which '" + player.getName() + "' is not active");
                    } else {
                        player.sendMessage(ChatColor.RED + "[BasicServerPlugin] This feature is not enabled. Contact your server administrator to enable!");
                        Bukkit.getLogger().warning("[BasicServerPlugin] You have accessed a '" + function + "' for which '" + player.getName() + "' is not active");
                    }
                    return false;
                }
            } else {
                if (FileManager.getConfig().getConfigurationSection("function_setting").get(function) == null) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] '" + function + "' is a function that does not exist");
                    return false;
                } else if (FileManager.getConfig().get("function_setting") == null) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] The '" + function + "' configuration path does not exist in the config file");
                    return false;
                } else if (FileManager.getConfig() == null) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] config file does not exist");
                    return false;
                }
                return false;
            }
        } catch (Exception e) {
            Bukkit.getLogger().warning("[BasicServerPlugin] Failed to load '" + function + "' settings");
            return false;
        }
    }
}