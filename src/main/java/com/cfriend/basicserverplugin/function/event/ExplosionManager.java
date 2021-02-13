package com.cfriend.basicserverplugin.function.event;

import com.cfriend.basicserverplugin.Main;
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

    Main plugin;

    public ExplosionManager(Main main) {
        plugin = main;
    }

    @EventHandler
    public void onExplodeEntity(EntityExplodeEvent e) {
        List<Block> blocks = e.blockList();
        new ExplosionRegen(blocks).runTaskTimer(plugin, 1, 1);
        e.setYield(0);
    }
    @EventHandler
    public void onExplodeBlock(BlockExplodeEvent e) {
        List<Block> blocks = e.blockList();
        new ExplosionRegen(blocks).runTaskTimer(plugin, 1, 1);
        e.setYield(0);
    }

    @EventHandler
    //Explosion prevention
    public void onPlace(BlockPlaceEvent e) {
        if (e.getBlock().getType().equals(Material.TNT)) {
            Player p = e.getPlayer();
            Bukkit.broadcastMessage(ChatColor.RED + "tnt를 '왜' 설치하나 " + p.getName() + "?");
        }
    }
}