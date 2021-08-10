package com.cfriend.basicserverplugin.bukkit.listeners.gui;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.*;
import com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.taskmenu.*;
import com.cfriend.basicserverplugin.bukkit.api.manager.MoneyManager;
import com.cfriend.basicserverplugin.bukkit.api.manager.PrivateWarehouseManager;
import com.cfriend.basicserverplugin.bukkit.api.manager.TitleManager;
import com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ServerMenu implements Listener {

    PluginTools PT = new PluginTools();
    TitleManager TM = new TitleManager();
    MoneyManager MM = new MoneyManager();
    SBManager SM = new SBManager();
    PrivateWarehouseManager PW = new PrivateWarehouseManager();
    MenuGui MG = new MenuGui();
    TaskMenuGui TMG = new TaskMenuGui();
    OtherMenuGui AddMG = new OtherMenuGui();
    PlayerSettingGui PSG = new PlayerSettingGui();
    WorldMenuGui WMG = new WorldMenuGui();

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getItemInHand().getType().equals(Material.BOOK) && e.getAction() == Action.RIGHT_CLICK_AIR) { //server menu(item event)
            if (player.getItemInHand().getItemMeta().getDisplayName().equals("Server menu")) {
                MG.openMenu(player);
            }
        } else if (player.getItemInHand().getType().equals(Material.NETHER_STAR) && e.getAction() == Action.RIGHT_CLICK_AIR) { //task menu(item event)
            if (player.getItemInHand().getItemMeta().getDisplayName().equals("Task menu")) {
                if (player.hasPermission("default.op")) {
                    TMG.openTaskMenu(player);
                } else {
                    player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        //server menu(event)
        if (e.getView().getTitle().equals("Server menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    WMG.openWorldMenu(player);
                    break;
                case 11:
                    player.performCommand("shop");
                    break;
                case 12:
                    if (PluginTools.isSettingOn("privatewarehouse", player))
                        PW.openPW(player);
                    break;
                case 13:
                    player.performCommand("minecraft:kill @s");
                    break;
                case 15:
                    player.performCommand("b help.command");
                    break;
                case 16:
                    PSG.openPlayerSetting(player);
                    break;
            }
        }
        //bank(event)
        if (e.getView().getTitle().equals("ATM")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    if (PluginTools.isSettingOn("money", player)) {
                        ItemStack item = new ItemStack(Material.PAPER);ItemMeta im = item.getItemMeta();
                        im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "5만원"));item.setItemMeta(im);
                        if (MM.checkMoney(player, 50000)) {
                            MM.subtractMoney(player, 50000);
                            player.getInventory().addItem(item);
                        }
                    }
                    break;
                case 12:
                    if (PluginTools.isSettingOn("money", player)) {
                        ItemStack item2 = new ItemStack(Material.PAPER);ItemMeta im2 = item2.getItemMeta();
                        im2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "1만원"));item2.setItemMeta(im2);
                        if (MM.checkMoney(player, 10000)) {
                            MM.subtractMoney(player, 10000);
                            player.getInventory().addItem(item2);
                        }
                    }
                    break;
                case 14:
                    if (PluginTools.isSettingOn("money", player)) {
                        ItemStack item3 = new ItemStack(Material.PAPER);ItemMeta im3 = item3.getItemMeta();
                        im3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "5천원"));item3.setItemMeta(im3);
                        if (MM.checkMoney(player, 5000)) {
                            MM.subtractMoney(player, 5000);
                            player.getInventory().addItem(item3);
                        }
                    }
                    break;
                case 16:
                    if (PluginTools.isSettingOn("money", player)) {
                        ItemStack item4 = new ItemStack(Material.PAPER);ItemMeta im4 = item4.getItemMeta();
                        im4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "천원"));item4.setItemMeta(im4);
                        if (MM.checkMoney(player, 1000)) {
                            MM.subtractMoney(player, 1000);
                            player.getInventory().addItem(item4);
                        }
                    }
                    break;
            }
        }
        //task menu(event)
        if (e.getView().getTitle().equals("Task menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + ": " + ChatColor.GREEN + "Start reloading...");
                    Bukkit.reload();
                    Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + ": " + ChatColor.GREEN + "Reload complete.");
                    break;
                case 11:
                    PT.BSPluginReload(player);
                    break;
                case 15:
                    AddMG.openMcMenu(player);
                    break;
                case 16:
                    player.performCommand("stop");
                    break;
            }
        }
        //Miscellaneous menu(event)
        if (e.getView().getTitle().equals("Miscellaneous menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("Your gamemode has been changed to survival");
                    break;
                case 11:
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("Your gamemode has been changed to creative");
                    break;
                case 12:
                    player.getInventory().clear();
                    player.sendMessage("removed all items from your inventory");
                    break;
                case 13:
                    player.performCommand("minecraft:effect clear @s");
                    break;
                case 14:
                    player.performCommand("minecraft:weather clear");
                    break;
                case 15:
                    player.getWorld().setTime(0);
                    Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + ":" + ChatColor.RESET + " Set the time to 0");
                    break;
                case 16:
                    player.performCommand("minecraft:kill @e[type=item]");
                    break;
                case 19:
                    player.performCommand("op " + player.getName());
                    break;
                case 20:
                    player.performCommand("deop " + player.getName());
                    break;
                case 21:
                    player.setHealth(0);
                    Bukkit.broadcastMessage("Killed " + player.getName());
                    break;
                case 35:
                    TMG.openTaskMenu(player);
                    break;
            }
        }
        //world menu(event)
        if (e.getView().getTitle().equals("World menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch(e.getSlot()) {
                case 10:
                    player.performCommand("mv tp world");
                    break;
                case 12:
                    player.performCommand("mv tp world2");
                    break;
                case 14:
                    player.performCommand("mv tp world3");
                    break;
                case 16:
                    player.performCommand("mv tp world4");
                    break;
                case 26:
                    MG.openMenu(player);
                    break;
            }
        }
        //setting(event)
        if (e.getView().getTitle().equals("Personal setting")) {
            e.setCancelled(true);
            if (e.getCurrentItem().hasItemMeta()) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    if (PluginTools.isSettingOn("scoreboard", player)) {
                        SBAMManager board = new SBAMManager(player.getPlayer().getUniqueId());
                        if (!board.hasID()) {
                            SM.setScoreboard(player);
                            SM.SAStart(player);
                            ItemStack on = new ItemStack(Material.MAP);ItemMeta onmeta = on.getItemMeta(); //This line is meaningless and you have to set the item meta.
                            onmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "scoreboard [on/&4off&f]"));
                            onmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "turns the scoreboard on or off"), "status: on"));
                            e.getCurrentItem().setType(Material.MAP);
                            e.getCurrentItem().setItemMeta(onmeta);
                        } else if (board.hasID()) {
                            SM.removeScoreboard(player);
                            ItemStack off = new ItemStack(Material.PAPER);ItemMeta offmeta = off.getItemMeta(); //This line is meaningless and you have to set the item meta.
                            offmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "scoreboard [&9on&f/off]"));
                            offmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "turns the scoreboard on or off"), "status: off"));
                            e.getCurrentItem().setType(Material.PAPER);
                            e.getCurrentItem().setItemMeta(offmeta);
                        }
                        break;
                    }
                case 11:
                    if (PluginTools.isSettingOn("title", player)) {
                        TM.showTitle(player);
                    }
                    break;
                case 26:
                    MG.openMenu(player);
                    break;
            }
        }
    }
}