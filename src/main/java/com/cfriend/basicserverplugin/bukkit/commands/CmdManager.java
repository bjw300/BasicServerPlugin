package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.Main;
import com.cfriend.basicserverplugin.bukkit.commands.tabcompleter.*;

public class CmdManager {

    public static void registerCommands() {
        registerCmd(Main.getInstance());
        registerTC(Main.getInstance());
    }

    private static void registerCmd(Main plugin) { //register command
        plugin.getCommand("bs").setExecutor(new BSCmd());
        plugin.getCommand("title").setExecutor(new TitleCmd());
        plugin.getCommand("warp").setExecutor(new WarpCmd());
        plugin.getCommand("sb").setExecutor(new SBCmd());
        plugin.getCommand("money").setExecutor(new MoneyCmd());
        plugin.getCommand("privatewarehouse").setExecutor(new PWCmd());
        plugin.getCommand("menu").setExecutor(new GuiCmd());
        plugin.getCommand("taskmenu").setExecutor(new GuiCmd());
        plugin.getCommand("setting").setExecutor(new GuiCmd());
        plugin.getCommand("worldmenu").setExecutor(new GuiCmd());
    }

    private static void registerTC(Main plugin) { //register tab completer
        plugin.getCommand("b").setTabCompleter(new BasicTC());
        plugin.getCommand("title").setTabCompleter(new TitleTC());
        plugin.getCommand("warp").setTabCompleter(new WarpTC());
        plugin.getCommand("sb").setTabCompleter(new SBTC());
        plugin.getCommand("money").setTabCompleter(new MoneyTC());
        plugin.getCommand("menu").setTabCompleter(new GuiTC());
        plugin.getCommand("taskmenu").setTabCompleter(new GuiTC());
    }
}