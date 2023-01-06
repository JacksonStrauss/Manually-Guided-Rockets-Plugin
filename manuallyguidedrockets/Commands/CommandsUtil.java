package com.francais.manuallyguidedrockets.Commands;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandsUtil {

    public static void initializeCommands(JavaPlugin plugin) {
        plugin.getCommand("giverocketlauncher").setExecutor(new GiveRocketLauncherCommand());
        plugin.getCommand("giverocket").setExecutor(new GiveRocketCommand());
    }

}
