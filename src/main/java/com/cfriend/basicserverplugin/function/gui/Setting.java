package com.cfriend.basicserverplugin.function.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Setting {

    static ItemStack item;static ItemMeta meta;
    static ItemStack item2;static ItemMeta meta2;
    static ItemStack item3;static ItemMeta meta3;
    static ItemStack item4;static ItemMeta meta4;
    static ItemStack item5;static ItemMeta meta5;
    static ItemStack item6;static ItemMeta meta6;
    static ItemStack item7;static ItemMeta meta7;
    static ItemStack item8;static ItemMeta meta8;
    static Inventory inv;

    public static void openSetting(Player p) { //open setting (gui)
        inv = Bukkit.createInventory(null, 27, "개인 설정");
        item = new ItemStack(Material.OAK_SIGN);meta = item.getItemMeta();meta.setDisplayName("정보표시창 [켜기/끄기]");meta.setLore(Arrays.asList("정보표시창을 켜거나 끕니다"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.EMERALD);meta2 = item2.getItemMeta();meta2.setDisplayName("칭호 관리");meta2.setLore(Arrays.asList("칭호목록을 실행합니다"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.BOOK);meta3 = item3.getItemMeta();meta3.setDisplayName("12");meta3.setLore(Arrays.asList("12"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.BOOK);meta4 = item4.getItemMeta();meta4.setDisplayName("13");meta4.setLore(Arrays.asList("13"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("14");meta5.setLore(Arrays.asList("14"));item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("15");meta6.setLore(Arrays.asList("15"));item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.BOOK);meta7 = item7.getItemMeta();meta7.setDisplayName("16");meta7.setLore(Arrays.asList("16"));item7.setItemMeta(meta7);
        item8 = new ItemStack(Material.RED_DYE);meta8 = item8.getItemMeta();meta8.setDisplayName(ChatColor.RED + "메뉴로 돌아가기");meta8.setLore(Arrays.asList(ChatColor.RED + "메뉴로 이동합니다"));item8.setItemMeta(meta8);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(11, new ItemStack(item2));
        inv.setItem(12, new ItemStack(item3));
        inv.setItem(13, new ItemStack(item4));
        inv.setItem(14, new ItemStack(item5));
        inv.setItem(15, new ItemStack(item6));
        inv.setItem(16, new ItemStack(item7));
        inv.setItem(26, new ItemStack(item8));
        p.openInventory(inv);
    }
}