package com.cfriend.basicserverplugin.bukkit.commands;

import com.cfriend.basicserverplugin.bukkit.api.PluginTools;
import com.cfriend.basicserverplugin.bukkit.api.manager.MoneyManager;
import com.cfriend.basicserverplugin.bukkit.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCmd implements CommandExecutor {

    MoneyManager MM = new MoneyManager();

    ///command        args[0]      args[1]  args[2]
    ///command         args1        args2    args3
    /// money       [set/add]      [player] [money] (op)
    /// money   [set/add/subtract] [money] (op)

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            //money(command)
            if (command.getName().equals("money")) {
                if (PluginTools.isSettingOn("money", player)) {
                    if (FileManager.getPlFile().get(player.getUniqueId() + ".money") != null) {
                        if (args.length == 0) {
                            MM.showMoney(player);
                        } else if (args[0].equalsIgnoreCase("set")) {
                            if (player.hasPermission("default.op")) {
                                if (args.length == 2) {
                                    try {
                                        MM.setMoney(player, Integer.parseInt(args[1]));
                                    } catch (Exception e) {
                                        player.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                                        Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
                                    }
                                } else if (args.length == 3) {
                                    try {
                                        MM.setMoney(player, args[1], Integer.parseInt(args[2]));
                                    } catch (Exception e) {
                                        player.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                                        Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
                                    }
                                } else {
                                    sender.sendMessage(ChatColor.RED + "Command is not correct!");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
                            }
                        } else if (args[0].equalsIgnoreCase("add")) {
                            if (player.hasPermission("default.op")) {
                                if (args.length == 2) {
                                    try {
                                        MM.addMoney(player, Integer.parseInt(args[1]));
                                    } catch (Exception e) {
                                        player.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                                        Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
                                    }
                                } else if (args.length == 3) {
                                    try {
                                        MM.addMoney(player, args[1], Integer.parseInt(args[2]));
                                    } catch (Exception e) {
                                        player.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                                        Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
                                    }
                                } else {
                                    sender.sendMessage(ChatColor.RED + "Command is not correct!");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
                            }
                        } else if (args[0].equalsIgnoreCase("subtract")) {
                            if (player.hasPermission("default.op")) {
                                if (args.length == 2) {
                                    try {
                                        MM.subtractMoney(player, Integer.parseInt(args[1]));
                                    } catch (Exception e) {
                                        player.sendMessage(ChatColor.RED + "숫자를 입력해 주세요!");
                                        Bukkit.getLogger().warning("[BasicServerPlugin] money error: " + e);
                                    }
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "You can't do this because you don't have permission!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.RED + "Command is not correct!");
                        }
                    } else {
                        FileManager.getPlFile().set(player.getUniqueId() + ".money", 0);
                        player.sendMessage(ChatColor.YELLOW + "0원 으로 기본 돈이 설정되었습니다!");
                    }
                }
            }
        } else {
            sender.sendMessage("[BasicServerPlugin] not support console!");
        }
        return false;
    }
}