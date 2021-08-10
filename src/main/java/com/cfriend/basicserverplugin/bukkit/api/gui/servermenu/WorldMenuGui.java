package com.cfriend.basicserverplugin.bukkit.api.gui.servermenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WorldMenuGui {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    ItemStack item5;ItemMeta meta5;
    Inventory inv;

    public void openWorldMenu(Player player) { //open world menu (gui)
        inv = Bukkit.createInventory(null, 27, "World menu");
        item = new ItemStack(Material.GRASS);meta = item.getItemMeta();meta.setDisplayName("World");meta.setLore(Arrays.asList("move to the world (default world)"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.GRASS);meta2 = item2.getItemMeta();meta2.setDisplayName("World 2");meta2.setLore(Arrays.asList("move to the world2"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.GRASS);meta3 = item3.getItemMeta();meta3.setDisplayName("World 3");meta3.setLore(Arrays.asList("move to the world3"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.GRASS);meta4 = item4.getItemMeta();meta4.setDisplayName("World 4");meta4.setLore(Arrays.asList("move to the world4"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.ARROW);meta5 = item5.getItemMeta();meta5.setDisplayName(ChatColor.RED + "back to the menu");item5.setItemMeta(meta5);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(12, new ItemStack(item2));
        inv.setItem(14, new ItemStack(item3));
        inv.setItem(16, new ItemStack(item4));
        inv.setItem(26, new ItemStack(item5));
        player.openInventory(inv);
    }
}