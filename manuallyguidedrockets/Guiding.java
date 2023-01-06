package com.francais.manuallyguidedrockets;

import com.francais.manuallyguidedrockets.Utils.Submethods;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.IOException;

public class Guiding {

    public static void guideProjectile(Player p, JavaPlugin plugin) throws IOException {
        long startTime = System.currentTimeMillis();
        Submethods.clearInventory(p);
        new BukkitRunnable() {

            @Override
            public void run() {
                if (Submethods.checkSurroundingBlocks(p, plugin) == true && (System.currentTimeMillis() - startTime > 500)) {
                    p.getWorld().createExplosion(p.getLocation(), 5, true);
                    p.stopSound(Sound.ITEM_ELYTRA_FLYING);
                    try {
                        Submethods.endActions(p, plugin);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cancel();
                } else {
                    double timePassed = (System.currentTimeMillis() - startTime) / 1000;
                    double veloMultiplier = Submethods.determineVelocity(timePassed);
                    Submethods.checkSound(p, timePassed);
                    double playerX = p.getLocation().getDirection().getX();
                    double playerY = p.getLocation().getDirection().getY();
                    double playerZ = p.getLocation().getDirection().getZ();
                    Vector velocityVector = new Vector(playerX * veloMultiplier, playerY * veloMultiplier, playerZ * veloMultiplier);
                    p.setVelocity(velocityVector);
                    Submethods.spawnParticle(p);
                }

            }
        }.runTaskTimer(plugin, 0L, 1);
    }
}
