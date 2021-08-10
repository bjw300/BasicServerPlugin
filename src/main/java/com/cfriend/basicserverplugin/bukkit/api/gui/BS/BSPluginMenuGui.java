package com.cfriend.basicserverplugin.bukkit.api.gui.BS;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BSPluginMenuGui {



    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    ItemStack item5;ItemMeta meta5;
    ItemStack item6;ItemMeta meta6;
    ItemStack item7;ItemMeta meta7;
    Inventory inv;

    public void openBSPluginMenu(Player player) { //open plugin menu
        inv = Bukkit.createInventory(null, 36, "BS Plugin Menu");
        item = new ItemStack(Material.BOOK);meta = item.getItemMeta();meta.setDisplayName("Plugin reload");item.setItemMeta(meta);
        item2 = new ItemStack(Material.BOOK);meta2 = item2.getItemMeta();meta2.setDisplayName("Help");item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.BOOK);meta3 = item3.getItemMeta();meta3.setDisplayName("Info");item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.BOOK);meta4 = item4.getItemMeta();meta4.setDisplayName("19");meta4.setLore(Arrays.asList(""));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("21");meta5.setLore(Arrays.asList(""));item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("23");meta6.setLore(Arrays.asList(""));item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.BOOK);meta7 = item7.getItemMeta();meta7.setDisplayName("25");meta7.setLore(Arrays.asList(""));item7.setItemMeta(meta7);
        inv.setItem(11, new ItemStack(item));
        inv.setItem(13, new ItemStack(item2));
        inv.setItem(15, new ItemStack(item3));
        inv.setItem(19, new ItemStack(item4));
        inv.setItem(21, new ItemStack(item5));
        inv.setItem(23, new ItemStack(item6));
        inv.setItem(25, new ItemStack(item7));
        player.openInventory(inv);
    }
}