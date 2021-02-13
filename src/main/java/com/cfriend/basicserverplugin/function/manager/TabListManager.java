package com.cfriend.basicserverplugin.function.manager;

import com.cfriend.basicserverplugin.Main;

import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TabListManager {

    private List<ChatComponentText> headers = new ArrayList<>();
    private List<ChatComponentText> footers = new ArrayList<>();

    private Main plugin;

    public TabListManager(Main main) {
        plugin = main;
    }

    public void showTab() {

        if (headers.isEmpty() && footers.isEmpty()) {
            for (int i = 0; i < plugin.getConfig().getConfigurationSection("tablist.header").getKeys(false).size(); i++) {
                headers.add(new ChatComponentText(format(plugin.getConfig().getString("tablist.header." + i))));
            }
            for (int i = 0; i < plugin.getConfig().getConfigurationSection("tablist.footer").getKeys(false).size(); i++) {
                footers.add(new ChatComponentText(format(plugin.getConfig().getString("tablist.footer." + i))));
            }
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

            int count1 = 0;
            int count2 = 0;

            @Override
            public void run() {
                try {
                    Field a = packet.getClass().getDeclaredField("header");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("footer");
                    b.setAccessible(true);

                    if (count1 >= headers.size())
                        count1 = 0;
                    if (count2 >= footers.size())
                        count2 = 0;
                    a.set(packet, headers.get(count1));
                    b.set(packet, footers.get(count2));

                    if (Bukkit.getOnlinePlayers().size() != 0) {
                        for (Player player : Bukkit.getOnlinePlayers())
                            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
                    }

                    count1++;
                    count2++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10, 40);
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}