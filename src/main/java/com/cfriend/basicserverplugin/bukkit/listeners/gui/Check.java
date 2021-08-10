package com.cfriend.basicserverplugin.bukkit.listeners.gui;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Check implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        //server menu(event)
        if (e.getView().getTitle().equals("Do you want to proceed?")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 12:

                    break;
                case 14:

                    break;
            }
        }
    }
}