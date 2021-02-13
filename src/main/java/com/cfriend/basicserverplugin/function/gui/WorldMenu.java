package com.cfriend.basicserverplugin.function.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WorldMenu {

    static ItemStack item;static ItemMeta meta;
    static ItemStack item2;static ItemMeta meta2;
    static ItemStack item3;static ItemMeta meta3;
    static ItemStack item4;static ItemMeta meta4;
    static ItemStack item5;static ItemMeta meta5;
    static Inventory inv;

    public static void openWorldMenu(Player p) { //open world menu (gui)
        inv = Bukkit.createInventory(null, 27, "월드 메뉴");
        item = new ItemStack(Material.GRASS_BLOCK);meta = item.getItemMeta();meta.setDisplayName("월드");meta.setLore(Arrays.asList("월드(기본월드)로 이동합니다"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.GRASS_BLOCK);meta2 = item2.getItemMeta();meta2.setDisplayName("월드 2");meta2.setLore(Arrays.asList("월드 2로 이동합니다"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.GRASS_BLOCK);meta3 = item3.getItemMeta();meta3.setDisplayName("월드 3");meta3.setLore(Arrays.asList("월드 3로 이동합니다"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.GRASS_BLOCK);meta4 = item4.getItemMeta();meta4.setDisplayName("월드 4");meta4.setLore(Arrays.asList("월드 4로 이동합니다"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.RED_DYE);meta5 = item5.getItemMeta();meta5.setDisplayName(ChatColor.RED + "메뉴로 돌아가기");meta5.setLore(Arrays.asList(ChatColor.RED + "메뉴로 이동합니다"));item5.setItemMeta(meta5);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(12, new ItemStack(item2));
        inv.setItem(14, new ItemStack(item3));
        inv.setItem(16, new ItemStack(item4));
        inv.setItem(26, new ItemStack(item5));
        p.openInventory(inv);
    }
}