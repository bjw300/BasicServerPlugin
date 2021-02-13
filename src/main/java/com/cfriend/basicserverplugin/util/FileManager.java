package com.cfriend.basicserverplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static File playersInfo;
    private static File warps;
    private static File pwarehouse;
    private static FileConfiguration plFile;
    private static FileConfiguration wpFile;
    private static FileConfiguration pwFile;

    public static void setupFiles() {
        playersInfo = new File(Bukkit.getServer().getPluginManager().getPlugin("BasicServerPlugin").getDataFolder(), "players info.yml");
        warps = new File(Bukkit.getServer().getPluginManager().getPlugin("BasicServerPlugin").getDataFolder(), "warps.yml");
        pwarehouse = new File(Bukkit.getServer().getPluginManager().getPlugin("BasicServerPlugin").getDataFolder(), "private warehouse data.yml");

        if (playersInfo.exists()) {
            try {
                playersInfo.createNewFile();
            } catch (IOException e) {
                //
            }
        }
        if (warps.exists()) {
            try {
                warps.createNewFile();
            } catch (IOException e) {
                //
            }
        }
        if (pwarehouse.exists()) {
            try {
                pwarehouse.createNewFile();
            } catch (IOException e) {
                //
            }
        }
        plFile = YamlConfiguration.loadConfiguration(playersInfo);
        wpFile = YamlConfiguration.loadConfiguration(warps);
        pwFile = YamlConfiguration.loadConfiguration(pwarehouse);
    }

    public static FileConfiguration getPlFile() {
        return plFile;
    }
    public static FileConfiguration getWpFile() {
        return wpFile;
    }
    public static FileConfiguration getPWFile() {
        return pwFile;
    }

    public static void savePlFile() {
        try {
            plFile.save(playersInfo);
        } catch (IOException e) {
            System.out.println("[BasicServerPlugin] " + ChatColor.RED + "Couldn't save players info file!");
        }
    }
    public static void saveWpFile() {
        try {
            wpFile.save(warps);
        } catch (IOException e) {
            System.out.println("[BasicServerPlugin] " + ChatColor.RED + "Couldn't save warps file!");
        }
    }
    public static void savePWFile() {
        try {
            pwFile.save(pwarehouse);
        } catch (IOException e) {
            System.out.println("[BasicServerPlugin] " + ChatColor.RED + "Couldn't save private warehouse file!");
        }
    }

    public static void reloadPlFile() {
        plFile = YamlConfiguration.loadConfiguration(playersInfo);
    }
    public static void reloadWpFile() {
        wpFile = YamlConfiguration.loadConfiguration(warps);
    }
    public static void reloadPWFile() {
        pwFile = YamlConfiguration.loadConfiguration(pwarehouse);
    }
}