package com.cfriend.basicserverplugin.bukkit.listeners.gui;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BS implements Listener {

    PluginTools PT = new PluginTools();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("BS Plugin Menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 11:
                    PT.BSPluginReload(player);
                    break;
                case 13:
                    PT.BSPluginHelp(player);
                    break;
                case 15:
                    PT.BSPluginInfo(player);
                    break;
            }
        }
    }
}