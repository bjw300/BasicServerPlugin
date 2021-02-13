package com.cfriend.basicserverplugin.function.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ExplosionRegen extends BukkitRunnable {

    ArrayList<BlockState> updatedBlockList = new ArrayList<>();

    public ExplosionRegen(List<Block> blocks) {
        for (Block b : blocks) {
            updatedBlockList.add(b.getState());
        }
    }

    @Override
    public void run() {
        int max = updatedBlockList.size() - 1;
        if (max > -1) {
            if (!updatedBlockList.get(max).getType().equals(Material.TNT)) {
                updatedBlockList.get(max).update(true, false);
            }
            updatedBlockList.remove(max);
        } else {
            this.cancel();
        }
    }
}