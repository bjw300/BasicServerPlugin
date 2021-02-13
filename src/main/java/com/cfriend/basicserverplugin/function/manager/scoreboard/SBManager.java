package com.cfriend.basicserverplugin.function.manager.scoreboard;

import com.cfriend.basicserverplugin.Main;
import com.cfriend.basicserverplugin.util.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class SBManager implements Listener {

    private static int taskID;
    private static Main plugin;

    public SBManager(Main main) {
        plugin = main;
    }

    @EventHandler
    //scoreboard(start animation)
    public void onJoin(PlayerJoinEvent e) {
        setScoreboard(e.getPlayer());
        SAStart(e.getPlayer());
    }

    //scoreboard(set)
    public static void setScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard sb = manager.getNewScoreboard();
        Objective obj = sb.registerNewObjective("sb", "dummy" ,ChatColor.translateAlternateColorCodes('&', "&f<Test Server>"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore(ChatColor.BLUE + "============== ").setScore(12);
        obj.getScore(" ").setScore(11);
        obj.getScore("- Your Nickname").setScore(10);
        obj.getScore(">  " + player.getName()).setScore(9);
        obj.getScore("  ").setScore(8);
        obj.getScore("- Your Money").setScore(7);
        obj.getScore(">  " + FileManager.getPlFile().get(player.getUniqueId() + ".money") + " ").setScore(6);
        obj.getScore("   ").setScore(5);
        obj.getScore("- Online Players").setScore(4);
        obj.getScore(">  " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).setScore(3);
        obj.getScore("    ").setScore(2);
        obj.getScore(ChatColor.BLUE + "==============").setScore(1);
        player.setScoreboard(sb);
    }

    //scoreboard(animation)
    public static void SAStart(Player player) {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            int count = 0;
            SBAMManager board = new SBAMManager(player.getUniqueId());

            @Override
            public void run() {
                if (!board.hasID()) {
                    board.setID(taskID);
                }
                if (count == 8) {
                    count = 0;
                }
                switch (count) {
                    case 0:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&f<Test Server>"));
                        break;
                    case 1:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&f<<Test Server>>"));
                        break;
                    case 2:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&e<<Test Server>>"));
                        break;
                    case 3:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&e<<&6Test &eServer>>"));
                        break;
                    case 4:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&e<<Test &6Server&e>>"));
                        break;
                    case 5:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&e<<Test Server>>"));
                        break;
                    case 6:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&f<<Test Server>>"));
                        break;
                    case 7:
                        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(
                                ChatColor.translateAlternateColorCodes('&', "&f<Test Server>"));
                        setScoreboard(player);
                        break;
                }
                count++;
            }
        }, 0, 10);
    }
}