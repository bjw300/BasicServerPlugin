package com.cfriend.basicserverplugin.bukkit.listeners;

import com.cfriend.basicserverplugin.bukkit.Main;
import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

public class ExplosionManager implements Listener {



    @EventHandler
    public void onExplodeEntity(EntityExplodeEvent e) {
        if (PluginTools.isSettingOn("ExplosionRegen")) {
            List<Block> blocks = e.blockList();
            new ExplosionRegen(blocks).runTaskTimer(Main.getInstance(), 1, 1);
            e.setYield(0);
        }
    }
    @EventHandler
    public void onExplodeBlock(BlockExplodeEvent e) {
        if (PluginTools.isSettingOn("ExplosionRegen")) {
            List<Block> blocks = e.blockList();
            new ExplosionRegen(blocks).runTaskTimer(Main.getInstance(), 1, 1);
            e.setYield(0);
        }
    }

    @EventHandler
    //Explosion prevention
    public void onPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType().equals(Material.TNT)) {
            if (PluginTools.isSettingOn("tnt_installation_warning_message")) {
                Player player = e.getPlayer();
                Bukkit.broadcastMessage(ChatColor.RED + "The " + player.getName() + " has installed tnt(" + e.getBlock().getX() + "," + e.getBlock().getY() + "," + e.getBlock().getZ() + ")");
            }
        }
    }
}