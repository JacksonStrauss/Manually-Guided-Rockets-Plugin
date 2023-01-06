package com.francais.manuallyguidedrockets.Utils;

import com.francais.manuallyguidedrockets.Commands.CommandsUtil;
import com.francais.manuallyguidedrockets.Files.FilesUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Startup {

    public static void pluginStartup(JavaPlugin plugin) throws IOException {
        FilesUtil.initializeFiles(plugin);
        CommandsUtil.initializeCommands(plugin);
        System.out.println("[!] Manually Guided Rockets Plugin successfully enabled!");
    }

}
