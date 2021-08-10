package com.cfriend.basicserverplugin.bukkit.api.gui.servermenu;

import com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard.SBAMManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PlayerSettingGui {

    ItemStack on;ItemMeta onmeta;
    ItemStack off;ItemMeta offmeta;
    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    ItemStack item5;ItemMeta meta5;
    ItemStack item6;ItemMeta meta6;
    ItemStack item7;ItemMeta meta7;
    Inventory inv;

    public void openPlayerSetting(Player player) { //open player setting (gui)
        SBAMManager board = new SBAMManager(player.getUniqueId());
        inv = Bukkit.createInventory(null, 27, "Personal setting");
        if (board.hasID()) {
            on = new ItemStack(Material.MAP);onmeta = on.getItemMeta();
            onmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Scoreboard [on/&4off&f]"));
            onmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "turns the scoreboard on or off"), "status: on"));
            on.setItemMeta(onmeta);
            inv.setItem(10, new ItemStack(on));
        } else {
            off = new ItemStack(Material.PAPER);offmeta = off.getItemMeta();
            offmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Scoreboard [&9on&f/off]"));
            offmeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "turns the scoreboard on or off"), "status: off"));
            off.setItemMeta(offmeta);
            inv.setItem(10, new ItemStack(off));
        }
        item = new ItemStack(Material.EMERALD);meta = item.getItemMeta();meta.setDisplayName("Title management");meta.setLore(Arrays.asList("11"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.BOOK);meta2 = item2.getItemMeta();meta2.setDisplayName("12");meta2.setLore(Arrays.asList("12"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.BOOK);meta3 = item3.getItemMeta();meta3.setDisplayName("13");meta3.setLore(Arrays.asList("13"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.BOOK);meta4 = item4.getItemMeta();meta4.setDisplayName("14");meta4.setLore(Arrays.asList("14"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("15");meta5.setLore(Arrays.asList("15"));item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("16");meta6.setLore(Arrays.asList("16"));item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.ARROW);meta7 = item7.getItemMeta();meta7.setDisplayName(ChatColor.RED + "back to the menu");item7.setItemMeta(meta7);
        inv.setItem(11, new ItemStack(item));
        inv.setItem(12, new ItemStack(item2));
        inv.setItem(13, new ItemStack(item3));
        inv.setItem(14, new ItemStack(item4));
        inv.setItem(15, new ItemStack(item5));
        inv.setItem(16, new ItemStack(item6));
        inv.setItem(26, new ItemStack(item7));
        player.openInventory(inv);
    }
}