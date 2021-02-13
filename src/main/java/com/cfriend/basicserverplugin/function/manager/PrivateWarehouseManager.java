package com.cfriend.basicserverplugin.function.manager;

import com.cfriend.basicserverplugin.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrivateWarehouseManager implements Listener {

    public static Map<String, ItemStack[]> menus = new HashMap<String, ItemStack[]>();

    public static void openPW(Player p) {
        Inventory inv = Bukkit.createInventory(p, 54, p.getName() + "의 개인 창고");
        if (menus.containsKey(p.getUniqueId().toString()))
            inv.setContents(menus.get(p.getUniqueId().toString()));
        p.openInventory(inv);
    }

    public void savePW() {
        for (Map.Entry<String, ItemStack[]> entry : menus.entrySet()) {
            FileManager.getPWFile().set("data." + entry.getKey(), entry.getValue());
        }
        FileManager.savePWFile();
    }

    public void restorePW() {
        FileManager.getPWFile().getConfigurationSection("data").getKeys(false).forEach(key ->{
            @SuppressWarnings("unchecked")
            ItemStack[] content = ((List<ItemStack>) FileManager.getPWFile().get("data." + key)).toArray(new ItemStack[0]);
            menus.put(key, content);
        });
    }

    @EventHandler
    public void onGuiClose(InventoryCloseEvent e) {
        if (e.getView().getTitle().contains(e.getPlayer().getName() + "의 개인 창고"))
            menus.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
    }
}