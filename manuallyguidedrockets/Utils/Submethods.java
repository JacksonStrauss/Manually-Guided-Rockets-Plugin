package com.francais.manuallyguidedrockets.Utils;

import com.francais.manuallyguidedrockets.Files.FilesUtil;
import com.francais.manuallyguidedrockets.Items.RocketItem;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class Submethods {

    public static double determineVelocity(double timePassed) {
        if (timePassed > 4.44) {
            return 1.3;
        } else {
            return 50/(5*timePassed+16);
        }
    }

    public static boolean checkSurroundingBlocks(Player p, JavaPlugin plugin) {
        for (int z = -1; z <= 1; z++) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (!p.getWorld().getBlockAt((int)p.getLocation().getX()+x, (int)p.getLocation().getY()+y, (int)p.getLocation().getZ()+z).getType().equals(Material.AIR)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void clearInventory(Player p) throws IOException {
        FilesUtil.inventoriesFileYML.set(p.getUniqueId().toString(), p.getInventory().getContents());
        FilesUtil.saveFile("inventories.yml");
        p.getInventory().clear();
        setRocketHat(p);
    }

    public static void restoreInventory(Player p) throws IOException {
        p.getInventory().setContents((ItemStack[])FilesUtil.inventoriesFileYML.get(p.getUniqueId().toString()));
        FilesUtil.inventoriesFileYML.set(p.getUniqueId().toString(), null);
        FilesUtil.saveFile("inventories.yml");
    }

    public static boolean checkRockets(Player p) {

        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && RocketItem.isRocketItem(item)) {
                item.setAmount(item.getAmount() - 1);
                p.updateInventory();
                return true;
            }
        }
        p.sendMessage(FilesUtil.langFileYML.getString("messages.notEnoughAmmo"));
        return false;
    }

    public static void spawnParticle(Player p) {
        Location loc = p.getLocation();
        loc.setY(loc.getY()+1);
        p.spawnParticle(Particle.FLAME, loc, 0, 0, 0, 0);
        p.spawnParticle(Particle.EXPLOSION_NORMAL, loc, 0,0, 0, 0);
    }

    public static void setRocketHat(Player p) {
        ItemStack rocketItem = new ItemStack(Material.PAPER);
        ItemMeta meta = rocketItem.getItemMeta();
        meta.setCustomModelData(1000);
        meta.setDisplayName(ChatColor.WHITE + "Rocket");
        rocketItem.setItemMeta(meta);
        p.getInventory().setHelmet(rocketItem);
    }

    public static void initialSound(Player p) {
        p.playSound(p, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 2, 1);
        p.playSound(p, Sound.ENTITY_TNT_PRIMED, 2, 1);
        p.playSound(p, Sound.ITEM_ELYTRA_FLYING, 2, 1);
        p.playSound(p, Sound.BLOCK_LAVA_EXTINGUISH, 2, 1);
    }

    public static void checkSound(Player p, double timePassed) {
        if ((int)timePassed % 10 == 0 && timePassed > 1) {
            p.stopSound(Sound.ITEM_ELYTRA_FLYING);
            p.playSound(p, Sound.ITEM_ELYTRA_FLYING, 2, 1);
        }
    }

    public static void initialActions(Player p) throws IOException {
        p.setInvulnerable(true);
        p.setInvisible(true);
        initialSound(p);
        Location saveLoc = p.getLocation();
        FilesUtil.rocketmodeFileYML.set(p.getUniqueId().toString(), saveLoc);
        FilesUtil.saveFile("rocketmode.yml");
    }

    public static void endActions(Player p, JavaPlugin plugin) throws IOException {
        Location saveLoc = (Location) FilesUtil.rocketmodeFileYML.get(p.getUniqueId().toString());
        restoreInventory(p);
        p.teleport(saveLoc);
        p.setFallDistance(0f);
        p.setInvulnerable(false);
        p.setInvisible(false);
        FilesUtil.rocketmodeFileYML.set(p.getUniqueId().toString(), null);
        FilesUtil.saveFile("rocketmode.yml");
    }

}
