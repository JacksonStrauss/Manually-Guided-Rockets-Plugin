package com.francais.manuallyguidedrockets.Commands;

import com.francais.manuallyguidedrockets.Files.FilesUtil;
import com.francais.manuallyguidedrockets.Items.RocketItem;
import com.francais.manuallyguidedrockets.Permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GiveRocketCommand implements CommandExecutor {

    private String permission = "manuallyguidedrockets.giverocket";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int inputNum = 1;

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (PermissionsManager.checkPermission(p, permission)) {

                if (args.length == 0) {
                    p.getInventory().addItem(new RocketItem());
                    return false;
                }

                if (args.length > 2) {
                    p.sendMessage(FilesUtil.langFileYML.getString("messages.tooManyArguments"));
                    return false;
                }

                if (args.length >= 1) {
                    try {
                        inputNum = Integer.parseInt(args[0]);
                    } catch (NumberFormatException nfe) {
                        p.sendMessage(FilesUtil.langFileYML.getString("messages.notANumberRockets"));
                        return false;
                    }
                    if (inputNum < 1 || inputNum > 64) {
                        p.sendMessage(FilesUtil.langFileYML.getString("messages.notValidNumber"));
                        return false;
                    }
                }

                if (args.length == 1) {
                    RocketItem rocketItem = new RocketItem();
                    rocketItem.setAmount(inputNum);
                    p.getInventory().addItem(rocketItem);
                } else if (args.length == 2) {
                    Player receiver = Bukkit.getPlayerExact(args[1]);
                    if (receiver != null) {
                        RocketItem rocketItem = new RocketItem();
                        rocketItem.setAmount(inputNum);
                        receiver.getInventory().addItem(rocketItem);
                    } else {
                        p.sendMessage(FilesUtil.langFileYML.getString("messages.invalidPlayer"));
                    }
                }
            }
        } else {

            if (args.length == 2) {
                Player receiver = Bukkit.getPlayerExact(args[1]);
                if (receiver != null) {
                    try {
                        inputNum = Integer.parseInt(args[0]);
                    } catch (NumberFormatException nfe) {
                        System.out.println(FilesUtil.langFileYML.getString("messages.incorrectSyntaxConsole"));
                        return false;
                    }
                    if (inputNum < 1 || inputNum > 64) {
                        System.out.println(FilesUtil.langFileYML.getString("messages.incorrectSyntaxConsole"));
                        return false;
                    }
                    RocketItem rocketItem = new RocketItem();
                    rocketItem.setAmount(inputNum);
                    receiver.getInventory().addItem(rocketItem);
                } else {
                    System.out.println(FilesUtil.langFileYML.getString("messages.incorrectSyntaxConsole"));
                }
            } else {
                System.out.println(FilesUtil.langFileYML.getString("messages.incorrectSyntaxConsole"));
            }
        }
        return false;
    }
}
