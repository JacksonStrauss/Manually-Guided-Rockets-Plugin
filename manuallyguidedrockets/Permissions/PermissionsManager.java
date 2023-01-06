package com.francais.manuallyguidedrockets.Permissions;

import com.francais.manuallyguidedrockets.Files.FilesUtil;
import org.bukkit.entity.Player;

public class PermissionsManager {

    public static boolean checkPermission(Player p, String permission) {
        if (FilesUtil.configFileYML.getBoolean("permissions_enabled") == false) {
            return true;
        }
        if (p.hasPermission(permission)) {
            return true;
        }
        p.sendMessage(FilesUtil.langFileYML.getString("messages.noPermissions"));
        return false;
    }




}
