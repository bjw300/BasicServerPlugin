package com.cfriend.basicserverplugin.bukkit.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CheckGui {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    Inventory inv;

    public void openCheckMenu(Player player) { //open check menu (gui)
        inv = Bukkit.createInventory(null, 27, "Do you want to proceed?");
        item = new ItemStack(Material.LAPIS_BLOCK);meta = item.getItemMeta();meta.setDisplayName("Yes");meta.setLore(Arrays.asList("move on to the next"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.REDSTONE_BLOCK);meta2 = item2.getItemMeta();meta2.setDisplayName("No");meta2.setLore(Arrays.asList("Cancel operation"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.ARROW);meta3 = item3.getItemMeta();meta3.setDisplayName(ChatColor.RED + "back to the menu");item3.setItemMeta(meta3);
        inv.setItem(12, new ItemStack(item));
        inv.setItem(14, new ItemStack(item2));
        inv.setItem(26, new ItemStack(item3));
        player.openInventory(inv);
    }
}