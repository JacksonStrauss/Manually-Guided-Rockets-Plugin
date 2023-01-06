package com.francais.manuallyguidedrockets.Commands;

import com.francais.manuallyguidedrockets.Files.FilesUtil;
import com.francais.manuallyguidedrockets.Items.RocketItem;
import com.francais.manuallyguidedrockets.Items.RocketLauncherItem;
import com.francais.manuallyguidedrockets.Permissions.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveRocketLauncherCommand implements CommandExecutor {

    private String permission = "manuallyguidedrockets.giverocketlauncher";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (PermissionsManager.checkPermission(p, permission)) {

                if (args.length == 0) {
                    p.getInventory().addItem(new RocketLauncherItem());
                    return false;
                }

                if (args.length > 1) {
                    p.sendMessage(FilesUtil.langFileYML.getString("messages.tooManyArguments"));
                    return false;
                }

                 if (args.length == 1) {
                    Player receiver = Bukkit.getPlayerExact(args[0]);
                    if (receiver != null) {
                        RocketLauncherItem rocketLauncherItem = new RocketLauncherItem();
                        receiver.getInventory().addItem(rocketLauncherItem);
                    } else {
                        p.sendMessage(FilesUtil.langFileYML.getString("messages.invalidPlayer"));
                    }
                }
            }
        } else {

            if (args.length == 1) {
                Player receiver = Bukkit.getPlayerExact(args[0]);
                if (receiver != null) {
                    RocketLauncherItem rocketLauncherItem = new RocketLauncherItem();
                    receiver.getInventory().addItem(rocketLauncherItem);
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
