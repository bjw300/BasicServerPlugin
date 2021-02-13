package com.cfriend.basicserverplugin;

import com.cfriend.basicserverplugin.function.command.*;
import com.cfriend.basicserverplugin.function.manager.*;
import com.cfriend.basicserverplugin.function.manager.scoreboard.*;
import com.cfriend.basicserverplugin.util.*;
import com.cfriend.basicserverplugin.function.event.*;
import com.cfriend.basicserverplugin.function.gui.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public TabListManager tabList;
    PrivateWarehouseManager PW;

    //registerEvents
    private void registerEvent() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ExplosionManager(this), this);
        Bukkit.getPluginManager().registerEvents(new PrivateWarehouseManager(), this);
        Bukkit.getPluginManager().registerEvents(new TitleManager(), this);
        Bukkit.getPluginManager().registerEvents(new SBManager(this), this);
        Bukkit.getPluginManager().registerEvents(new GuiEvent(), this);
        Bukkit.getPluginManager().registerEvents(new Bank(), this);
    }

    //Main registration
    private void registerMain() {
        new CmdManager(this);
        new ExplosionManager(this);
        new SBManager(this);
        tabList = new TabListManager(this);
        PW = new PrivateWarehouseManager();
    }

    //setup Files
    private void setupFiles() {
        FileManager.setupFiles();
        FileManager.getPlFile().options().copyDefaults(true);
        FileManager.getWpFile().options().copyDefaults(true);
        FileManager.getPWFile().options().copyDefaults(true);
        getConfig().options().copyDefaults(true);
        FileManager.savePWFile();
        FileManager.savePlFile();
        FileManager.saveWpFile();
        saveDefaultConfig();
    }

    @Override
    //plugin Activate
    public void onEnable() {
        registerEvent();
        registerMain();
        setupFiles();
        if (FileManager.getPWFile().contains("data")) {
            PW.restorePW();
        }
        tabList.showTab();
        getLogger().info("Activate!");
    }

    @Override
    //plugin Deactivate
    public void onDisable() {
        if (!PW.menus.isEmpty()) {
            PW.savePW();
        }
        setupFiles();
        getLogger().info("Deactivate!");
    }
}