package com.cfriend.basicserverplugin.bukkit.api.manager.scoreboard;

import com.cfriend.basicserverplugin.bukkit.Main;
import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SBManager implements Listener {

    private static int taskID;
    SBAMManager board;

    @EventHandler
    //scoreboard(start animation)
    public void onJoin(PlayerJoinEvent e) {
        if (PluginTools.isSettingOn("scoreboard")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                board = new SBAMManager(player.getUniqueId());
                if (board.hasID()) {
                    setScoreboard(player);
                }
            }
            setScoreboard(e.getPlayer());
            SAStart(e.getPlayer());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (PluginTools.isSettingOn("scoreboard")) {
            board = new SBAMManager(e.getPlayer().getUniqueId());
            if (board.hasID())
                board.stop();
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        board = new SBAMManager(player.getUniqueId());
                        if (board.hasID()) {
                            setScoreboard(player);
                        }
                    }
                }
            }.runTaskLater(Main.getInstance(), 10);
        }
    }

    //scoreboard(set)
    public void setScoreboard(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        String sbName = FileManager.getConfig().getString("scoreboard.name.basic");
        if (sbName != null) {
            Objective obj = sb.registerNewObjective("sb", "dummy");
            obj.setDisplayName(formatColor(formatScoreboard(sbName, player)));
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            for (int i = 0; i <= FileManager.getConfig().getConfigurationSection("scoreboard").getKeys(false).size() - 2; i++) {
                try {
                    String score = FileManager.getConfig().getString("scoreboard." + i);
                    if (score != null) {
                        obj.getScore(formatColor(formatScoreboard(score, player))).setScore(i);
                    } else {
                        Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load scoreboard settings so scoreboard won't work!");
                    }
                } catch (Exception e) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] Scoreboard application error: " + e);
                }
            }
            player.setScoreboard(sb);
        } else {
            Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load scoreboard name so scoreboard won't work!");
            return;
        }
    }

    //scoreboard(remove)
    public void removeScoreboard(Player player) {
        board = new SBAMManager(player.getUniqueId());
        if (board.hasID()) {
            board.stop();
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        } else {
            for (int i = 0; i <= FileManager.getConfig().getConfigurationSection("scoreboard").getKeys(false).size() - 2; i++) {
                String score = FileManager.getConfig().getString("scoreboard." + i);
                if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
                    if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getDisplayName().contains(score)) {
                        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
                    }
                }
            }
        }
    }

    //scoreboard(reload)
    public void reloadScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            SBAMManager board = new SBAMManager(player.getUniqueId());
            if (board.hasID()) {
                setScoreboard(player);
                SAStart(player);
            } else {
                for (int i = 0; i <= FileManager.getConfig().getConfigurationSection("scoreboard").getKeys(false).size() - 2; i++) {
                    String score = FileManager.getConfig().getString("scoreboard." + i);
                    if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
                        if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getDisplayName().contains(score)) {
                            setScoreboard(player);
                            SAStart(player);
                        }
                    }
                }
            }
        }
    }

    public void SAStart(Player player) {
        Long repeatDelay = FileManager.getConfig().getLong("scoreboard.name.animation.repeating-delay");
        board = new SBAMManager(player.getUniqueId());
        if (FileManager.getConfig().getString("scoreboard.name.animation.repeating-delay") == null) {
            Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load scoreboard repeating-delay so scoreboard animation won't work!");
            return;
        } else if (board.hasID())
            board.stop();
        board.setID(taskID);

        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            int count = 0;

            @Override
            public void run() {
                try {
                    if (!board.hasID())
                        return;
                    String sbName = FileManager.getConfig().getString("scoreboard.name.animation." + count);
                    //Bukkit.broadcastMessage("bsplugin: " + count + "/" + sbName);
                    player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(formatColor(sbName));
                    count++;
                    if (count == FileManager.getConfig().getConfigurationSection("scoreboard.name.animation").getKeys(false).size() - 1)
                        count = 0;
                } catch (Exception e) {
                    Bukkit.getLogger().warning("[BasicServerPlugin] Couldn't load scoreboard animation settings: " + e);
                }
            }
        }, 0, repeatDelay);
    }

    private static String formatScoreboard(String s, Player player) {
        if (s != null) {
            s = s
                    .replace("%player%", player.getName())
                    .replace("%onlineplayers%", Integer.toString(Bukkit.getOnlinePlayers().size()))
                    .replace("%maxplayers%", Integer.toString(Bukkit.getMaxPlayers()))
                    .replace("%playermoney%", FileManager.getPlFile().getInt(player.getUniqueId() + ".money") + " ")
                    .replace("%worldtime%", String.valueOf(player.getWorld().getTime()));
            return s;
        }
        return s;
    }

    private static String formatColor(String msg) { //format color
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}