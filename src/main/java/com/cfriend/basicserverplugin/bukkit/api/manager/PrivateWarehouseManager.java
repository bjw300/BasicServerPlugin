package com.cfriend.basicserverplugin.bukkit.api.manager;

import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
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

    public void openPW(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, player.getName() + "'s private warehouse");
        if (menus.containsKey(player.getUniqueId().toString()))
            inv.setContents(menus.get(player.getUniqueId().toString()));
        player.openInventory(inv);
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
        if (e.getView().getTitle().contains(e.getPlayer().getName() + "'s private warehouse"))
            menus.put(e.getPlayer().getUniqueId().toString(), e.getInventory().getContents());
    }
}