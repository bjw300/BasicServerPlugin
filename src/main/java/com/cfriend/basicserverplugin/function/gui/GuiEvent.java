package com.cfriend.basicserverplugin.function.gui;

import com.cfriend.basicserverplugin.function.manager.PrivateWarehouseManager;
import com.cfriend.basicserverplugin.function.manager.TitleManager;
import com.cfriend.basicserverplugin.function.manager.scoreboard.SBAMManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiEvent implements Listener {

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getItemInHand().getType().equals(Material.CLOCK) && e.getAction() == Action.RIGHT_CLICK_AIR) { //server menu(item event)
            if (p.getItemInHand().getItemMeta().getDisplayName().equals("서버 메뉴")) {
                Menu.openMenu(p);
            }
        } else if (p.getItemInHand().getType().equals(Material.NETHER_STAR) && e.getAction() == Action.RIGHT_CLICK_AIR) { //task menu(item event)
            if (p.getItemInHand().getItemMeta().getDisplayName().equals("관리자 메뉴")) {
                if (p.hasPermission("default.op")) {
                    TaskMenu.openTaskMenu(p);
                } else {
                    p.sendMessage(ChatColor.RED + "당신은 권한이 없기 때문에 이 작업을 할 수 없음!");
                }
            }
        }
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        //server menu(event)
        if (e.getView().getTitle().equals("서버 메뉴")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType().isItem()) {
                p.playSound(p.getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    WorldMenu.openWorldMenu(p);
                    break;
                case 11:
                    p.performCommand("shop");
                    break;
                case 12:
                    PrivateWarehouseManager.openPW(p);
                    break;
                case 13:
                    p.performCommand("minecraft:kill @s");
                    break;
                case 15:
                    p.performCommand("b help.command");
                    break;
                case 16:
                    Setting.openSetting(p);
                    break;
            }
        }
        //bank(event)
        if (e.getView().getTitle().equals("ATM")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() != Material.AIR) {
                p.playSound(p.getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    p.performCommand("");
                    ItemStack item = new ItemStack(Material.YELLOW_DYE);
                    ItemMeta im = item.getItemMeta();
                    im.setDisplayName(ChatColor.translateAlternateColorCodes('&', "5만원"));
                    item.setItemMeta(im);
                    p.getInventory().addItem(item);
                    break;
                case 12:
                    p.performCommand("");
                    ItemStack item2 = new ItemStack(Material.GREEN_DYE);
                    ItemMeta im2 = item2.getItemMeta();
                    im2.setDisplayName(ChatColor.translateAlternateColorCodes('&', "1만원"));
                    item2.setItemMeta(im2);
                    p.getInventory().addItem(item2);
                    break;
                case 14:
                    p.performCommand("");
                    ItemStack item3 = new ItemStack(Material.ORANGE_DYE);
                    ItemMeta im3 = item3.getItemMeta();
                    im3.setDisplayName(ChatColor.translateAlternateColorCodes('&', "5천원"));
                    item3.setItemMeta(im3);
                    p.getInventory().addItem(item3);
                    break;
                case 16:
                    p.performCommand("");
                    ItemStack item4 = new ItemStack(Material.BLUE_DYE);
                    ItemMeta im4 = item4.getItemMeta();
                    im4.setDisplayName(ChatColor.translateAlternateColorCodes('&', "천원"));
                    item4.setItemMeta(im4);
                    p.getInventory().addItem(item4);
                    break;
            }
        }
        //task menu(event)
        if (e.getView().getTitle().equals("관리자 메뉴")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() != Material.AIR) {
                p.playSound(p.getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    p.performCommand("minecraft:gamemode survival");
                    break;
                case 11:
                    p.performCommand("minecraft:gamemode creative");
                    break;
                case 12:
                    p.performCommand("op @p");
                    break;
                case 13:
                    p.performCommand("deop @p");
                    break;
                case 14:
                    p.performCommand("minecraft:weather clear");
                    break;
                case 15:
                    p.performCommand("minecraft:time set 0");
                    break;
                case 16:
                    p.performCommand("minecraft:kill @e[type=!player]");
                    break;
                case 19:
                    p.performCommand("reload");
                    break;
                case 20:
                    p.performCommand("b reload");
                    break;
                case 25:
                    p.performCommand("stop");
                    break;
            }
        }
        //world menu(event)
        if (e.getView().getTitle().equals("월드 메뉴")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() != Material.AIR) {
                p.playSound(p.getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            }
            switch(e.getSlot()) {
                case 10:
                    p.performCommand("mv tp world");
                    break;
                case 12:
                    p.performCommand("mv tp world2");
                    break;
                case 14:
                    p.performCommand("mv tp world3");
                    break;
                case 16:
                    p.performCommand("mv tp world4");
                    break;
                case 26:
                    Menu.openMenu(p);
                    break;
            }
        }
        //setting(event)
        if (e.getView().getTitle().equals("개인 설정")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() != Material.AIR) {
                p.playSound(p.getLocation(), Sound.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, 1.0f, 1.0f);
            }
            switch (e.getSlot()) {
                case 10:
                    SBAMManager board = new SBAMManager(p.getPlayer().getUniqueId());
                    if (!board.hasID()) {
                        p.performCommand("sb on");
                    } else if (board.hasID()) {
                        p.performCommand("sb off");
                    }
                    break;
                case 11:
                    TitleManager.showTitle(p);
                    break;
                case 26:
                    Menu.openMenu(p);
                    break;
            }
        }
    }
}