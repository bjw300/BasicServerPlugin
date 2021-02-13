package com.cfriend.basicserverplugin.function.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Menu {

    static ItemStack item;static ItemMeta meta;
    static ItemStack item2;static ItemMeta meta2;
    static ItemStack item3;static ItemMeta meta3;
    static ItemStack item4;static ItemMeta meta4;
    static ItemStack item5;static ItemMeta meta5;
    static ItemStack item6;static ItemMeta meta6;
    static ItemStack item7;static ItemMeta meta7;
    static Inventory inv;

    public static void openMenu(Player p) { //open server menu (gui)
        inv = Bukkit.createInventory(null, 27, "서버 메뉴");
        item = new ItemStack(Material.GRASS_BLOCK);meta = item.getItemMeta();meta.setDisplayName("월드");meta.setLore(Arrays.asList("월드 목록"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.EMERALD);meta2 = item2.getItemMeta();meta2.setDisplayName("상점");meta2.setLore(Arrays.asList("상점을 실행합니다"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.CHEST);meta3 = item3.getItemMeta();meta3.setDisplayName("개인 창고");meta3.setLore(Arrays.asList("자신의 창고를", "실행합니다"));item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.SKELETON_SKULL);meta4 = item4.getItemMeta();meta4.setDisplayName("자살하기");meta4.setLore(Arrays.asList("자살합니다"));item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("14");meta5.setLore(Arrays.asList("14"));item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("명령어 도움말 보기");meta6.setLore(Arrays.asList("명령어 도움말을", "실행합니다"));item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.PAPER);meta7 = item7.getItemMeta();meta7.setDisplayName("설정");meta7.setLore(Arrays.asList("설정을 실행합니다"));item7.setItemMeta(meta7);
        inv.setItem(10, new ItemStack(item));
        inv.setItem(11, new ItemStack(item2));
        inv.setItem(12, new ItemStack(item3));
        inv.setItem(13, new ItemStack(item4));
        inv.setItem(14, new ItemStack(item5));
        inv.setItem(15, new ItemStack(item6));
        inv.setItem(16, new ItemStack(item7));
        p.openInventory(inv);
    }

    public static void menuProvide(Player p) { //provide server menu (item)
        item = new ItemStack(Material.CLOCK);
        meta = item.getItemMeta();
        meta.setDisplayName("서버 메뉴");
        meta.setLore(Arrays.asList("서버메뉴를 실행합니다"));
        item.setItemMeta(meta);
        p.getInventory().addItem(new ItemStack(item));
    }
}