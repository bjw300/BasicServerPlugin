package com.cfriend.basicserverplugin.bukkit.api.manager.tablist.v1_17;

import com.cfriend.basicserverplugin.bukkit.Main;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.TabListInterface;
import com.cfriend.basicserverplugin.bukkit.api.manager.tablist.TabListManager;
//import net.minecraft.network.chat.ChatComponentText;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class TabListManager_1_17_R1 implements TabListInterface {

    TabListManager TM = new TabListManager();
    public HashMap<Player, List> headerList = new HashMap<Player, List>();
    public HashMap<Player, List> footerList = new HashMap<Player, List>();

    public void showTab() {
        Long repeatDelay = FileManager.getConfig().getLong("tablist.repeating-delay");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            int count1 = 0;
            int count2 = 0;
            @Override
            public void run() {
                try {
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
                            if ((headerList.get(player)) != null && (footerList.get(player)) != null)
                                player.setPlayerListHeaderFooter((headerList.get(player)).get(count1).toString(), (footerList.get(player)).get(count2).toString());
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