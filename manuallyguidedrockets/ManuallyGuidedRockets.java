package com.francais.manuallyguidedrockets;

import com.francais.manuallyguidedrockets.Files.FilesUtil;
import com.francais.manuallyguidedrockets.Items.RocketItem;
import com.francais.manuallyguidedrockets.Items.RocketLauncherItem;
import com.francais.manuallyguidedrockets.Permissions.PermissionsManager;
import com.francais.manuallyguidedrockets.Utils.Startup;
import com.francais.manuallyguidedrockets.Utils.Submethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;

public final class ManuallyGuidedRockets extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            Startup.pluginStartup(this);
            Bukkit.getPluginManager().registerEvents(this, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player && e.getClickedInventory() != null) {
            Player p = (Player) e.getWhoClicked();
            ArrayList<ItemStack> items = new ArrayList<>();
            items.add(e.getCurrentItem());
            items.add(e.getCursor());
            items.add((e.getClick() == org.bukkit.event.inventory.ClickType.NUMBER_KEY) ? e.getWhoClicked().getInventory().getItem(e.getHotbarButton()) : e.getCurrentItem());
            for(ItemStack item : items) {
                if(FilesUtil.rocketmodeFileYML.contains(p.getUniqueId().toString()) && item != null && item.hasItemMeta()) {
                    if(RocketItem.isRocketItem(item)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(org.bukkit.event.player.PlayerInteractEvent e) throws IOException {

        Player p = e.getPlayer();

        if (e.getHand().equals(EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                ItemStack heldItem = p.getInventory().getItemInMainHand();
                if (heldItem != null && RocketLauncherItem.isRocketLauncherItem(heldItem)) {
                    if (PermissionsManager.checkPermission(p, "manuallyguidedrockets.fire")) {
                        if (Submethods.checkRockets(p)) {
                            Guiding.guideProjectile(p, this);
                            Submethods.initialActions(p);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws IOException {

        Player p = e.getPlayer();
        p.setResourcePack("https://download.mc-packs.net/pack/e1103b292c625ecb7c5b0ed4f0d9613bab890f7e.zip");
        if (FilesUtil.rocketmodeFileYML.contains(p.getUniqueId().toString())) {
            Submethods.endActions(p, this);
        }
    }


}
