package com.cfriend.basicserverplugin.bukkit;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.commands.CmdManager;
import com.cfriend.basicserverplugin.bukkit.listeners.*;
import com.cfriend.basicserverplugin.bukkit.listeners.gui.*;
import com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.BankGui;
import com.cfriend.basicserverplugin.bukkit.api.manager.PrivateWarehouseManager;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.*;
import com.cfriend.basicserverplugin.bukkit.api.manager.TitleManager;
import com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard.SBManager;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_12.*;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_13.*;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_14.*;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_15.*;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_16.*;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_17.*;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    static Main instance;
    TabListInterface tabList;
    PrivateWarehouseManager PW = new PrivateWarehouseManager();
    PluginTools PT = new PluginTools();

    public static Main getInstance() {
        return instance;
    }

    //registerEvents
    private void registerEvent() {
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ExplosionManager(), this);
        Bukkit.getPluginManager().registerEvents(new PrivateWarehouseManager(), this);
        Bukkit.getPluginManager().registerEvents(new TitleManager(), this);
        Bukkit.getPluginManager().registerEvents(new SBManager(), this);
        Bukkit.getPluginManager().registerEvents(new BS(), this);
        Bukkit.getPluginManager().registerEvents(new ServerMenu(), this);
        Bukkit.getPluginManager().registerEvents(new Warp(), this);
        Bukkit.getPluginManager().registerEvents(new BankGui(), this);
    }

    //setup Files
    private void setupFiles() {
        FileManager.setupFiles();
        FileManager.getConfig().options().copyDefaults(true);
        FileManager.getPlFile().options().copyDefaults(true);
        FileManager.getWpFile().options().copyDefaults(true);
        FileManager.getPWFile().options().copyDefaults(true);
        getConfig().options().copyDefaults(true);
        FileManager.savePWFile();
        FileManager.savePlFile();
        FileManager.saveWpFile();
        saveDefaultConfig();
    }

    public void registerTablist(String sversion) {
        if (sversion.equals("v1_12_R1")) {
            tabList = new TabListManager_1_12_R1();
        } else if (sversion.equals("v1_13_R1")) {
            tabList = new TabListManager_1_13_R1();
        } else if (sversion.equals("v1_13_R2")) {
            tabList = new TabListManager_1_13_R2();
        } else if (sversion.equals("v1_14_R1")) {
            tabList = new TabListManager_1_14_R1();
        } else if (sversion.equals("v1_15_R1")) {
            tabList = new TabListManager_1_15_R1();
        } else if (sversion.equals("v1_16_R1")) {
            tabList = new TabListManager_1_16_R1();
        } else if (sversion.equals("v1_16_R2")) {
            tabList = new TabListManager_1_16_R2();
        } else if (sversion.equals("v1_16_R3")) {
            tabList = new TabListManager_1_16_R3();
        } else if (sversion.equals("v1_17_R1")) {
            tabList = new TabListManager_1_17_R1();
        } else {
            getLogger().info("The tablist is unavailable because the specified version is not used.");
        }
    }

    private Boolean versionCheck() {
        String sversion = "N/A";
        try {
            sversion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        registerTablist(sversion);
        if (PT.versionComparison("v1_12_R1")) {
            sversion = sversion
                    .replace("v", "")
                    .replaceFirst("_", ".")
                    .replace("_R1", "+")
                    .replace("_R2", "+")
                    .replace("_R3", "+");
            if (!sversion.equals("1.17+"))
                getLogger().info("You are running version " + sversion + ". 1.17+ version is stable, so please use 1.17+ version");
            return true;
        }
        return false;
    }

    @Override
    //plugin Activate
    public void onEnable() {
        Main.instance = this;
        if (!versionCheck()) {
            getLogger().warning("Running non-compatible version, so failed to enable");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        registerEvent();
        CmdManager.registerCommands();
        setupFiles();
        //Updater updater = new Updater(this, 451388, this.getFile(), Updater.UpdateType.DEFAULT, false);
        if (PluginTools.isSettingOn("tablist") && tabList != null) {
            tabList.showTab();
        }
        if (FileManager.getPWFile().contains("data"))
            PW.restorePW();
    }

    @Override
    //plugin Deactivate
    public void onDisable() {
        setupFiles();
        if (!PW.menus.isEmpty())
            PW.savePW();
    }
}