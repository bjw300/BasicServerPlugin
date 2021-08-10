package com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.taskmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TaskMenuGui {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    ItemStack item5;ItemMeta meta5;
    ItemStack item6;ItemMeta meta6;
    ItemStack item7;ItemMeta meta7;
    Inventory inv;

    public void openTaskMenu(Player player) { //open task menu (gui)
        inv = Bukkit.createInventory(null, 27, "Task menu");
        item = new ItemStack(Material.ENCHANTED_BOOK);meta = item.getItemMeta();meta.setDisplayName("Server reload");meta.setLore(Arrays.asList("reload the server"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.ENCHANTED_BOOK);meta2 = item2.getItemMeta();meta2.setDisplayName("Plugin reload");meta2.setLore(Arrays.asList("reload the plugin"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.ENCHANTED_BOOK);meta3 = item3.getItemMeta();meta3.setDisplayName("12");meta3.setLore(Arrays.asList("12"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.ENCHANTED_BOOK);meta4 = item4.getItemMeta();meta4.setDisplayName("13");meta4.setLore(Arrays.asList("13"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.ENCHANTED_BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("14");meta5.setLore(Arrays.asList("14"));item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.ENCHANTED_BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("Miscellaneous menu");meta6.setLore(Arrays.asList("open miscellaneous menu"));item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.BARRIER);meta7 = item7.getItemMeta();meta7.setDisplayName("Server stop");item7.setItemMeta(meta7);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(11, new ItemStack(item2));
        inv.setItem(12, new ItemStack(item3));
        inv.setItem(13, new ItemStack(item4));
        inv.setItem(14, new ItemStack(item5));
        inv.setItem(15, new ItemStack(item6));
        inv.setItem(16, new ItemStack(item7));
        player.openInventory(inv);
    }

    public void taskMenuProvide(Player player) { //provide task menu (item)
        item = new ItemStack(Material.NETHER_STAR);
        meta = item.getItemMeta();
        meta.setDisplayName("Task menu");
        meta.setLore(Arrays.asList("open taskmenu"));
        item.setItemMeta(meta);
        player.getInventory().addItem(new ItemStack(item));
    }
}