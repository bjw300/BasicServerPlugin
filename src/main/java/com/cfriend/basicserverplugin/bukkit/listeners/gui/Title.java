package com.cfriend.basicserverplugin.bukkit.listeners.gui;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.manager.TitleManager;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Title {

    TitleManager TM = new TitleManager();


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        String[] wl = new String[FileManager.getWpFile().getKeys(false).size()];
        FileManager.getWpFile().getKeys(false).toArray(wl);
        if (e.getView().getTitle().equals("title management menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            if (PluginTools.isSettingOn("title", player)) {
                switch (e.getSlot()) {
                    case 10:
                        TM.showTitle(player);
                        break;
                    case 12:
                        //TM.setTitle(player, );
                        break;
                    case 14:
                        break;
                    case 16:
                        TM.delTitle(player);
                        break;
                }
            }
        }
    }
}