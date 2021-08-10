package com.cfriend.basicserverplugin.bukkit.api.gui.Warp;

import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WarpListGui {

    ItemStack warp;ItemMeta meta;
    ItemStack empty;ItemMeta meta2;
    ItemStack next;ItemMeta meta3;
    Inventory inv;

    public void openWarpList(Player player) { //open warp list (gui)
        int number = FileManager.getWpFile().getKeys(false).size();
        if (number >= 29) {
            for (int i = 1; i <= number / 28; i++) {
                inv = Bukkit.createInventory(null, 54, "Warp list(page/" + i + ")");
            }
        } else if (number >= 22) {
            inv = Bukkit.createInventory(null, 54, "Warp list");
        } else if (number >= 15) {
            inv = Bukkit.createInventory(null, 45, "Warp list");
        } else if (number >= 8) {
            inv = Bukkit.createInventory(null, 36, "Warp list");
        } else if (number >= 1) {
            inv = Bukkit.createInventory(null, 27, "Warp list");
        } else if (number == 0) {
            inv = Bukkit.createInventory(null, 27, "Warp list");
            empty = new ItemStack(Material.BARRIER);meta2 = empty.getItemMeta();meta2.setDisplayName("warp is empty!");empty.setItemMeta(meta2);
            inv.setItem(13, new ItemStack(empty));
            player.openInventory(inv);
            return;
        }
        next = new ItemStack(Material.ARROW);meta3 = next.getItemMeta();meta3.setDisplayName("Next page");meta3.setLore(Arrays.asList("move on to the next"));next.setItemMeta(meta3);
        for (int i = 0; i < number; i++) {
            String[] wl = new String[number];
            FileManager.getWpFile().getKeys(false).toArray(wl);
            warp = new ItemStack(Material.GRASS);meta = warp.getItemMeta();meta.setDisplayName(wl[i]);meta.setLore(Arrays.asList("left click : move on warp point", "right click : edit warp"));warp.setItemMeta(meta);
            if (i == 27) {
                inv.setItem(52, new ItemStack(next));
                inv.setItem(i + 18, new ItemStack(warp));
            } else if (i >= 21) {
                inv.setItem(i + 16, new ItemStack(warp));
            } else if (i >= 14) {
                inv.setItem(i + 14, new ItemStack(warp));
            } else if (i >= 7) {
                inv.setItem(i + 12, new ItemStack(warp));
            } else if (i < 7) {
                inv.setItem(i + 10, new ItemStack(warp));
            }
        }
        player.openInventory(inv);
    }
}