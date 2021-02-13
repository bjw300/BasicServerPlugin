package com.cfriend.basicserverplugin.function.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Bank implements Listener {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    Inventory inv;

    //bank
    @EventHandler
    public void PlayerClickBlock(PlayerInteractEvent e) throws InterruptedException {
        openBank(e);
    }

    public void openBank(PlayerInteractEvent e) { //open bank (gui)
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && b.getType().equals(Material.POLISHED_ANDESITE_STAIRS)) {
            inv = Bukkit.createInventory(null, 27, "ATM");
            item = new ItemStack(Material.YELLOW_DYE);meta = item.getItemMeta();meta.setDisplayName("5만원");meta.setLore(Arrays.asList("자신의 통장에서 5만원을 인출합니다"));item.setItemMeta(meta);
            item2 = new ItemStack(Material.GREEN_DYE);meta2 = item2.getItemMeta();meta2.setDisplayName("1만원");meta2.setLore(Arrays.asList("자신의 통장에서 1만원을 인출합니다"));item2.setItemMeta(meta2);
            item3 = new ItemStack(Material.ORANGE_DYE);meta3 = item3.getItemMeta();meta3.setDisplayName("5천원");meta3.setLore(Arrays.asList("자신의 통장에서 5천원을 인출합니다"));item3.setItemMeta(meta3);
            item4 = new ItemStack(Material.YELLOW_DYE);meta4 = item4.getItemMeta();meta4.setDisplayName("천원");meta4.setLore(Arrays.asList("자신의 통장에서 천원을 인출합니다"));item4.setItemMeta(meta4);
            inv.setItem(10, new ItemStack(item));
            inv.setItem(12, new ItemStack(item2));
            inv.setItem(14, new ItemStack(item3));
            inv.setItem(16, new ItemStack(item4));
            p.openInventory(inv);
        }
    }
}