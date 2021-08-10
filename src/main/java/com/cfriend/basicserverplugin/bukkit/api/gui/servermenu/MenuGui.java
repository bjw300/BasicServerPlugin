package com.cfriend.basicserverplugin.bukkit.api.gui.servermenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuGui {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    ItemStack item5;ItemMeta meta5;
    ItemStack item6;ItemMeta meta6;
    ItemStack item7;ItemMeta meta7;
    Inventory inv;

    public void openMenu(Player player) { //open server menu (gui)
        inv = Bukkit.createInventory(null, 27, "Server menu");
        item = new ItemStack(Material.GRASS);meta = item.getItemMeta();meta.setDisplayName("World menu");item.setItemMeta(meta);
        item2 = new ItemStack(Material.EMERALD);meta2 = item2.getItemMeta();meta2.setDisplayName("Shop");meta2.setLore(Arrays.asList("open shop"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.CHEST);meta3 = item3.getItemMeta();meta3.setDisplayName("Private warehouse");meta3.setLore(Arrays.asList("open PW"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.TNT);meta4 = item4.getItemMeta();meta4.setDisplayName("Suicide");meta4.setLore(Arrays.asList("..!"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("14");meta5.setLore(Arrays.asList("14"));item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("Command help");meta6.setLore(Arrays.asList("run command help"));item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.PAPER);meta7 = item7.getItemMeta();meta7.setDisplayName("Setting");meta7.setLore(Arrays.asList("open setting"));item7.setItemMeta(meta7);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(11, new ItemStack(item2));
        inv.setItem(12, new ItemStack(item3));
        inv.setItem(13, new ItemStack(item4));
        inv.setItem(14, new ItemStack(item5));
        inv.setItem(15, new ItemStack(item6));
        inv.setItem(16, new ItemStack(item7));
        player.openInventory(inv);
    }

    public void menuProvide(Player player) { //provide server menu (item)
        item = new ItemStack(Material.BOOK);
        meta = item.getItemMeta();
        meta.setDisplayName("Server menu");
        meta.setLore(Arrays.asList("open server menu"));
        item.setItemMeta(meta);
        player.getInventory().addItem(new ItemStack(item));
    }
}