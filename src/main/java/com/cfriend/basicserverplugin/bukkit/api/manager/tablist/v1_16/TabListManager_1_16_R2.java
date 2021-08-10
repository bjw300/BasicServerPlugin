package com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_16;

import com.cfriend.basicserverplugin.bukkit.Main;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.TabListInterface;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.TabListManager;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class TabListManager_1_16_R2 implements TabListInterface {

    TabListManager TM = new TabListManager();
    public HashMap<Player, List> headerList = new HashMap<Player, List>();
    public HashMap<Player, List> footerList = new HashMap<Player, List>();

    public void showTab() {
        Long repeatDelay = FileManager.getConfig().getLong("tablist.repeating-delay");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
            int count1 = 0;
            int count2 = 0;
            @Override
            public void run() {
                try {
                    Field a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    if (Bukkit.getOnlinePlayers().size() != 0) {
                        headerList.clear();
                        footerList.clear();
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            headerList.put(player, TM.getHeader(player));
                            footerList.put(player, TM.getFooter(player));
                            if (count1 >= (headerList.get(player)).size())
                                count1 = 0;
                            if (count2 >= (footerList.get(player)).size())
                                count2 = 0;
                            if ((headerList.get(player)) != null && (footerList.get(player)) != null) {
                                a.set(packet, new ChatComponentText((headerList.get(player)).get(count1).toString()));
                                b.set(packet, new ChatComponentText((footerList.get(player)).get(count2).toString()));
                                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
                            }
                        }
                    }
                    count1++;
                    count2++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10, repeatDelay);
    }
}