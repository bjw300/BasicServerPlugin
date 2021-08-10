package com.cfriend.basicserverplugin.bukkit.api.gui.servermenu.taskmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class OtherMenuGui {

    ItemStack item;ItemMeta meta;
    ItemStack item2;ItemMeta meta2;
    ItemStack item3;ItemMeta meta3;
    ItemStack item4;ItemMeta meta4;
    ItemStack item5;ItemMeta meta5;
    ItemStack item6;ItemMeta meta6;
    ItemStack item7;ItemMeta meta7;
    ItemStack item8;ItemMeta meta8;
    ItemStack item9;ItemMeta meta9;
    ItemStack item10;ItemMeta meta10;
    ItemStack item11;ItemMeta meta11;
    ItemStack item12;ItemMeta meta12;
    ItemStack item13;ItemMeta meta13;
    ItemStack item14;ItemMeta meta14;
    ItemStack item15;ItemMeta meta15;
    Inventory inv;

    public void openMcMenu(Player player) { //open task menu (gui)
        inv = Bukkit.createInventory(null, 36, "Miscellaneous menu");
        item = new ItemStack(Material.ENCHANTED_BOOK);meta = item.getItemMeta();meta.setDisplayName("Survival mod");meta.setLore(Arrays.asList("change to survival mode"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.ENCHANTED_BOOK);meta2 = item2.getItemMeta();meta2.setDisplayName("Creative mod");meta2.setLore(Arrays.asList("change to creative mode"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.ENCHANTED_BOOK);meta3 = item3.getItemMeta();meta3.setDisplayName("Inventory clear");item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.ENCHANTED_BOOK);meta4 = item4.getItemMeta();meta4.setDisplayName("Effect clear");item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.ENCHANTED_BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("Clearing the weather");item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.ENCHANTED_BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("Change to daytime");item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.ITEM_FRAME);meta7 = item7.getItemMeta();meta7.setDisplayName("Item kill");meta7.setLore(Arrays.asList("kill 'all' fallen items"));item7.setItemMeta(meta7);
        item8 = new ItemStack(Material.ENCHANTED_BOOK);meta8 = item8.getItemMeta();meta8.setDisplayName("Get op");item8.setItemMeta(meta8);
        item9 = new ItemStack(Material.ENCHANTED_BOOK);meta9 = item9.getItemMeta();meta9.setDisplayName("Revoke op");item9.setItemMeta(meta9);
        item10 = new ItemStack(Material.TNT);meta10 = item10.getItemMeta();meta10.setDisplayName("Suicide");meta10.setLore(Arrays.asList("..!"));item10.setItemMeta(meta10);
        item11 = new ItemStack(Material.ENCHANTED_BOOK);meta11 = item11.getItemMeta();meta11.setDisplayName("22");meta11.setLore(Arrays.asList("22"));item11.setItemMeta(meta11);
        item12 = new ItemStack(Material.ENCHANTED_BOOK);meta12 = item12.getItemMeta();meta12.setDisplayName("23");meta12.setLore(Arrays.asList("23"));item12.setItemMeta(meta12);
        item13 = new ItemStack(Material.ENCHANTED_BOOK);meta13 = item13.getItemMeta();meta13.setDisplayName("24");meta13.setLore(Arrays.asList("24"));item13.setItemMeta(meta13);
        item14 = new ItemStack(Material.ENCHANTED_BOOK);meta14 = item14.getItemMeta();meta14.setDisplayName("25");meta14.setLore(Arrays.asList("25"));item14.setItemMeta(meta14);
        item15 = new ItemStack(Material.ARROW);meta15 = item15.getItemMeta();meta15.setDisplayName("Back to the taskmenu");item15.setItemMeta(meta15);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(11, new ItemStack(item2));
        inv.setItem(12, new ItemStack(item3));
        inv.setItem(13, new ItemStack(item4));
        inv.setItem(14, new ItemStack(item5));
        inv.setItem(15, new ItemStack(item6));
        inv.setItem(16, new ItemStack(item7));
        inv.setItem(19, new ItemStack(item8));
        inv.setItem(20, new ItemStack(item9));
        inv.setItem(21, new ItemStack(item10));
        inv.setItem(22, new ItemStack(item11));
        inv.setItem(23, new ItemStack(item12));
        inv.setItem(24, new ItemStack(item13));
        inv.setItem(25, new ItemStack(item14));
        inv.setItem(35, new ItemStack(item15));
        player.openInventory(inv);
    }
}