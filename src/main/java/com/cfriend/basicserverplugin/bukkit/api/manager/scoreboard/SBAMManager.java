package com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard;

import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SBAMManager {

    private static Map<UUID, Integer> TASKS = new HashMap<UUID, Integer>();
    private final UUID uuid;

    public SBAMManager(UUID uuid) {
        this.uuid = uuid;
    }

    public void setID(int id) {
        TASKS.put(uuid, id);
    }

    public int getID() {
        return TASKS.get(uuid);
    }

    public Boolean hasID() {
        if (TASKS.containsKey(uuid))
            return true;
        return false;
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(TASKS.get(uuid));
        TASKS.remove(uuid);
    }
}