package com.cfriend.basicserverplugin.function.command;

import com.cfriend.basicserverplugin.Main;
import com.cfriend.basicserverplugin.function.command.tabcompleter.*;

public class CmdManager {

    private Main plugin;

    public CmdManager(Main main) {
        plugin = main;
        registerCmd(plugin);
        registerTC(plugin);
    }

    private void registerCmd(Main plugin) { //register
        plugin.getCommand("b").setExecutor(new BasicCmd(plugin));
        plugin.getCommand("title").setExecutor(new TitleCmd());
        plugin.getCommand("warp").setExecutor(new WarpCmd());
        plugin.getCommand("sb").setExecutor(new SBCmd());
        plugin.getCommand("money").setExecutor(new MoneyCmd());
        plugin.getCommand("pw").setExecutor(new PWCmd());
        plugin.getCommand("menu").setExecutor(new GuiCmd());
        plugin.getCommand("taskmenu").setExecutor(new GuiCmd());
        plugin.getCommand("setting").setExecutor(new GuiCmd());
        plugin.getCommand("worldmenu").setExecutor(new GuiCmd());
    }

    private void registerTC(Main plugin) {
        plugin.getCommand("b").setTabCompleter(new BasicTC());
        plugin.getCommand("title").setTabCompleter(new TitleTC());
        plugin.getCommand("warp").setTabCompleter(new WarpTC());
        plugin.getCommand("sb").setTabCompleter(new SBTC());
        plugin.getCommand("money").setTabCompleter(new MoneyTC());
        plugin.getCommand("menu").setTabCompleter(new GuiTC());
        plugin.getCommand("taskmenu").setTabCompleter(new GuiTC());
    }
}