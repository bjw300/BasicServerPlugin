package com.cfriend.basicserverplugin.bukkit.api.manager.tablist;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabListManager {

    PluginTools PT = new PluginTools();

    public List getHeader(Player player) {
        List<String> headers = new ArrayList<String>();
        if (FileManager.getConfig().getString("tablist.repeating-delay") == null) {
            Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load tablist repeating-delay so tablist won't work!");
            return null;
        } else {
            for (int i = 0; i <= FileManager.getConfig().getConfigurationSection("tablist.header").getKeys(false).size() - 1; i++) {
                String header = FileManager.getConfig().getString("tablist.header." + i);
                if (header != null) {
                    headers.add(formatColor(formatTablist(header, player)));
                } else {
                    Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load tablist settings so tablist won't work!");
                    return null;
                }
            }
            return headers;
        }
    }

    public List getFooter(Player player) {
        List<String> footers = new ArrayList<String>();
        if (FileManager.getConfig().getString("tablist.repeating-delay") == null) {
            Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load tablist repeating-delay so tablist won't work!");
            return null;
        } else {
            for (int i = 0; i <= FileManager.getConfig().getConfigurationSection("tablist.footer").getKeys(false).size() - 1; i++) {
                String footer = FileManager.getConfig().getString("tablist.footer." + i);
                if (footer != null) {
                    footers.add(formatColor(formatTablist(footer, player)));
                } else {
                    Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load tablist settings so tablist won't work!");
                    return null;
                }
            }
            return footers;
        }
    }

    private String formatTablist(String s, Player player) {
        if (s != null) {
            s = s
                    .replace("%player%", player.getName())
                    .replace("%onlineplayers%", Integer.toString(Bukkit.getOnlinePlayers().size()))
                    .replace("%maxplayers%", Integer.toString(Bukkit.getMaxPlayers()))
                    .replace("%playermoney%", FileManager.getPlFile().getInt(player.getUniqueId() + ".money") + " ")
                    .replace("%worldtime%", String.valueOf(player.getWorld().getTime()));
                    if (PT.versionComparison("v1_17_R1")) {
                        s = s.replace("%ping%", Integer.toString(player.getPing()));
                    } else if (PT.getServerVersion().equals("v1_12_R1")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer)player).getHandle().ping)));
                    } else if (PT.getServerVersion().equals("v1_13_R1")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_13_R1.entity.CraftPlayer)player).getHandle().ping)));
                    } else if (PT.getServerVersion().equals("v1_13_R2")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer)player).getHandle().ping)));
                    } else if (PT.getServerVersion().equals("v1_14_R1")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer)player).getHandle().ping)));
                    } else if (PT.getServerVersion().equals("v1_15_R1")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer)player).getHandle().ping)));
                    } else if (PT.getServerVersion().equals("v1_16_R1")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_16_R1.entity.CraftPlayer)player).getHandle().ping)));
                    } else if (PT.getServerVersion().equals("v1_16_R2")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer)player).getHandle().ping)));
                    } else if (PT.getServerVersion().equals("v1_16_R3")) {
                        s = s.replace("%ping%", Integer.toString((((org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer)player).getHandle().ping)));
                    }
            return s;
        }
        return s;
    }

    private String formatColor(String s) { //format color
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}