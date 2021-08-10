package com.cfriend.basicserverplugin.bukkit.listeners.gui;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.gui.Warp.WarpEditGui;
import com.cfriend.basicserverplugin.bukkit.api.gui.Warp.WarpListGui;
import com.cfriend.basicserverplugin.bukkit.api.gui.Warp.WarpManagementGui;
import com.cfriend.basicserverplugin.bukkit.api.manager.WarpManager;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Warp implements Listener {

    WarpManager WM = new WarpManager();
    WarpManagementGui WMG = new WarpManagementGui();
    WarpEditGui WEG = new WarpEditGui();
    WarpListGui WLG = new WarpListGui();


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        String[] wl = new String[FileManager.getWpFile().getKeys(false).size()];
        FileManager.getWpFile().getKeys(false).toArray(wl);
        if (e.getView().getTitle().equals("warp management menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            if (PluginTools.isSettingOn("warp", player)) {
                switch (e.getSlot()) {
                    case 10:
                        //WM.setWarp(player, );
                        break;
                    case 12:
                        break;
                    case 14:
                        break;
                    case 16:
                        break;
                }
            }
        } else if (e.getView().getTitle().equals("Warp list")) {
            if (PluginTools.isSettingOn("warp", player)) {
                e.setCancelled(true);
                if (e.getCurrentItem().hasItemMeta()) {
                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                }
                if (PluginTools.isSettingOn("warp", player)) {
                    if (e.isLeftClick()) {
                        for (int i = 0; i < wl.length; i++) {
                            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(wl[i])) {
                                WM.warpPlayer(player, wl[i]);
                            }
                        }
                    } else if (e.isRightClick()) {
                        for (int i = 0; i < wl.length; i++) {
                            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(wl[i])) {
                                WEG.openWarpEdit(player, wl[i]);
                            }
                        }
                    }
                }
            }
            /*if (e.getInventory().getSize() == 54) {
                if (e.getSlot() == 52) {
                    int number = FileManager.getWpFile().getKeys(false).size();
                    if (number >= 29) {
                        for (int i = 1; i < number / 28; i++) {
                            inv = Bukkit.createInventory(null, 54, "Warp list(" + page + "/" + i + ")");
                        }
                    }
                    int nextpage = ;
                    WarpListGui.openWarpList(player, nextpage);
                }
            }*/
        } else if (e.getView().getTitle().contains("' warp edit menu")) {
            for (int i = 0; i < wl.length; i++) {
                if (e.getView().getTitle().equals("'" + wl[i] + "' warp edit menu")) {
                    e.setCancelled(true);
                    if (e.getCurrentItem().hasItemMeta()) {
                        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
                    }
                    if (PluginTools.isSettingOn("warp", player)) {
                        switch (e.getSlot()) {
                            case 10:
                                //WarpManager.reNameWarp(player, );
                                break;
                            case 12:
                                break;
                            case 14:
                                break;
                            case 16:
                                WM.delWarp(player, wl[i]);
                                player.closeInventory();
                                break;
                            case 26:
                                WLG.openWarpList(player);
                                break;
                        }
                    }
                }
            }
        }
    }
}