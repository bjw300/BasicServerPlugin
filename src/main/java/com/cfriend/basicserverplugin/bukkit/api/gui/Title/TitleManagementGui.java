package com.cfriend.basicserverplugin.bukkit.api.gui.Title;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TitleManagementGui {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    Inventory inv;

    public void openTitleManagement(Player player) { //open title management gui (gui)
        inv = Bukkit.createInventory(null, 27, "title management menu");
        item = new ItemStack(Material.GRASS);meta = item.getItemMeta();meta.setDisplayName("Get title");meta.setLore(Arrays.asList("this will be added soon"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.GRASS);meta2 = item2.getItemMeta();meta2.setDisplayName("Set title");meta2.setLore(Arrays.asList("this will be added soon"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.GRASS);meta3 = item3.getItemMeta();meta3.setDisplayName("14");meta3.setLore(Arrays.asList("this will be added soon"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.REDSTONE_BLOCK);meta4 = item4.getItemMeta();meta4.setDisplayName("Delete title");meta4.setLore(Arrays.asList("delete title"));item4.setItemMeta(meta4);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(12, new ItemStack(item2));
        inv.setItem(14, new ItemStack(item3));
        inv.setItem(16, new ItemStack(item4));
        player.openInventory(inv);
    }
}