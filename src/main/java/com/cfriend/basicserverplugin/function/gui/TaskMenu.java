package com.cfriend.basicserverplugin.function.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class TaskMenu {

    static ItemStack item;static ItemMeta meta;
    static ItemStack item2;static ItemMeta meta2;
    static ItemStack item3;static ItemMeta meta3;
    static ItemStack item4;static ItemMeta meta4;
    static ItemStack item5;static ItemMeta meta5;
    static ItemStack item6;static ItemMeta meta6;
    static ItemStack item7;static ItemMeta meta7;
    static ItemStack item8;static ItemMeta meta8;
    static ItemStack item9;static ItemMeta meta9;
    static ItemStack item10;static ItemMeta meta10;
    static ItemStack item11;static ItemMeta meta11;
    static ItemStack item12;static ItemMeta meta12;
    static ItemStack item13;static ItemMeta meta13;
    static ItemStack item14;static ItemMeta meta14;
    static Inventory inv;


    public static void openTaskMenu(Player p) { //open task menu (gui)
        inv = Bukkit.createInventory(null, 36, "관리자 메뉴");
        item = new ItemStack(Material.BOOK);meta = item.getItemMeta();meta.setDisplayName("서바이벌 모드");meta.setLore(Arrays.asList("서바이벌 모드로 바꿔줍니다"));item.setItemMeta(meta);
        item2 = new ItemStack(Material.BOOK);meta2 = item2.getItemMeta();meta2.setDisplayName("크레이티브 모드");meta2.setLore(Arrays.asList("크레이티브 모드로 바꿔줍니다"));item2.setItemMeta(meta2);
        item3 = new ItemStack(Material.BOOK);meta3 = item3.getItemMeta();meta3.setDisplayName("op 받기");item3.setItemMeta(meta3);
        item4 = new ItemStack(Material.BOOK);meta4 = item4.getItemMeta();meta4.setDisplayName("op 없애기");item4.setItemMeta(meta4);
        item5 = new ItemStack(Material.BOOK);meta5 = item5.getItemMeta();meta5.setDisplayName("날씨 맑게 하기");meta5.setLore(Arrays.asList("비,눈이 올때 실행하세요"));item5.setItemMeta(meta5);
        item6 = new ItemStack(Material.BOOK);meta6 = item6.getItemMeta();meta6.setDisplayName("낮으로 바꾸기");meta6.setLore(Arrays.asList("해가 뜰 때로 바꿔줍니다"));item6.setItemMeta(meta6);
        item7 = new ItemStack(Material.WITHER_SKELETON_SKULL);meta7 = item7.getItemMeta();meta7.setDisplayName("죽이기");meta7.setLore(Arrays.asList("플레이어 빼고 '다' 죽입니다"));item7.setItemMeta(meta7);
        item8 = new ItemStack(Material.BOOK);meta8 = item8.getItemMeta();meta8.setDisplayName("서버 리로드");meta8.setLore(Arrays.asList("서버를 다시 로딩합니다"));item8.setItemMeta(meta8);
        item9 = new ItemStack(Material.BOOK);meta9 = item9.getItemMeta();meta9.setDisplayName("플러그인 리로드");meta9.setLore(Arrays.asList("플러그인을 다시 로딩합니다"));item9.setItemMeta(meta9);
        item10 = new ItemStack(Material.BOOK);meta10 = item10.getItemMeta();meta10.setDisplayName("10");meta10.setLore(Arrays.asList("10"));item10.setItemMeta(meta10);
        item11 = new ItemStack(Material.BOOK);meta11 = item11.getItemMeta();meta11.setDisplayName("11");meta11.setLore(Arrays.asList("11"));item11.setItemMeta(meta11);
        item12 = new ItemStack(Material.BOOK);meta12 = item12.getItemMeta();meta12.setDisplayName("12");meta12.setLore(Arrays.asList("12"));item12.setItemMeta(meta12);
        item13 = new ItemStack(Material.BOOK);meta13 = item13.getItemMeta();meta13.setDisplayName("13");meta13.setLore(Arrays.asList("13"));item13.setItemMeta(meta13);
        item14 = new ItemStack(Material.BARRIER);meta14 = item14.getItemMeta();meta14.setDisplayName("서버중지");meta14.setLore(Arrays.asList("서버를 중지합니다"));item14.setItemMeta(meta14);
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
        p.openInventory(inv);
    }

    public static void taskMenuProvide(Player p) { //provide task menu (item)
        item = new ItemStack(Material.NETHER_STAR);
        meta = item.getItemMeta();
        meta.setDisplayName("관리자 메뉴");
        meta.setLore(Arrays.asList("관리자 메뉴를 실행합니다"));
        item.setItemMeta(meta);
        p.getInventory().addItem(new ItemStack(item));
    }
}