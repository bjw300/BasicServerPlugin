package com.cfriend.basicserverplugin.bukkit.api.gui.Warp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WarpManagementGui {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    ItemStack item5;ItemMeta meta5;
    Inventory inv;

    public void openWarpManagement(Player player) { //open warp management gui (gui)
        inv = Bukkit.createInventory(null, 27, "warp management menu");
        item = new ItemStack(Material.GRASS);meta = item.getItemMeta();meta.setDisplayName("Set warp");meta.setLore(Arrays.asList("this will be added soon"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.GRASS);meta2 = item2.getItemMeta();meta2.setDisplayName("12");meta2.setLore(Arrays.asList("this will be added soon"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.GRASS);meta3 = item3.getItemMeta();meta3.setDisplayName("14");meta3.setLore(Arrays.asList("this will be added soon"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.REDSTONE_BLOCK);meta4 = item4.getItemMeta();meta4.setDisplayName("16");meta4.setLore(Arrays.asList("delete this warp point"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.ARROW);meta5 = item5.getItemMeta();meta5.setDisplayName(ChatColor.RED + "back to the warp list");item5.setItemMeta(meta5);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(12, new ItemStack(item2));
        inv.setItem(14, new ItemStack(item3));
        inv.setItem(16, new ItemStack(item4));
        inv.setItem(26, new ItemStack(item5));
        player.openInventory(inv);
    }
}