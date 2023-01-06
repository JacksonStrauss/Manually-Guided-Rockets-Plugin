package com.francais.manuallyguidedrockets.Files;

import com.francais.manuallyguidedrockets.ManuallyGuidedRockets;
import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public class FilesUtil {

    public static YamlConfiguration langFileYML = null;
    public static YamlConfiguration rocketmodeFileYML = null;
    public static YamlConfiguration inventoriesFileYML = null;
    public static FileConfiguration configFileYML = null;

    public static File langFile = null;
    public static File rocketmodeFile = null;
    public static File inventoriesFile = null;

    public static void initializeFile(String name, JavaPlugin plugin) throws IOException {
        File file = new File(plugin.getDataFolder(), name);
        if (!file.exists()) {
            file.createNewFile();
            if (name.equals("lang.yml")) {
                langFile = file;
                try {
                    InputStream in = plugin.getResource("lang.yml");
                    OutputStream out = new FileOutputStream(langFile);
                    ByteStreams.copy(in, out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        switch (name) {
            case "lang.yml":
                langFile = file;
                langFileYML = YamlConfiguration.loadConfiguration(file);
            case "rocketmode.yml":
                rocketmodeFileYML = YamlConfiguration.loadConfiguration(file);
                rocketmodeFile = file;
            case "inventories.yml":
                inventoriesFileYML = YamlConfiguration.loadConfiguration(file);
                inventoriesFile = file;
            default:
                return;
        }
    }

    public static void initializeConfig(JavaPlugin plugin) {
        configFileYML = plugin.getConfig();
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
    }
    public static void initializeFiles(JavaPlugin plugin) throws IOException {
        initializeConfig(plugin);
        initializeFile("lang.yml", plugin);
        initializeFile("rocketmode.yml", plugin);
        initializeFile("inventories.yml", plugin);
    }

    public static void saveFile(String name) throws IOException {
        switch (name) {
            case "lang.yml":
                langFileYML.save(langFile);
            case "rocketmode.yml":
                rocketmodeFileYML.save(rocketmodeFile);
            case "inventories.yml":
                inventoriesFileYML.save(inventoriesFile);
            default:
                return;
        }


    }

}
