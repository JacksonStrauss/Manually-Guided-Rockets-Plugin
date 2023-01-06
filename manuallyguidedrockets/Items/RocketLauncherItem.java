package com.francais.manuallyguidedrockets.Items;

import com.francais.manuallyguidedrockets.Files.FilesUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RocketLauncherItem extends ItemStack {

    private ItemMeta meta;
    private ArrayList<String> itemLore;

    public RocketLauncherItem() {
        super(Material.PAPER);
        meta = super.getItemMeta();
        itemLore = new ArrayList<String>();

        meta.setCustomModelData(FilesUtil.configFileYML.getInt("rocketLauncher"));
        meta.setDisplayName(FilesUtil.langFileYML.getString("items.rocketLauncherItem.displayName"));

        itemLore.add(FilesUtil.langFileYML.getString("items.rocketLauncherItem.lore.line1"));
        itemLore.add(FilesUtil.langFileYML.getString("items.rocketLauncherItem.lore.line2"));
        itemLore.add(FilesUtil.langFileYML.getString("items.rocketLauncherItem.lore.line3"));
        itemLore.add(FilesUtil.langFileYML.getString("items.rocketLauncherItem.lore.line4"));
        itemLore.add(FilesUtil.langFileYML.getString("items.rocketLauncherItem.lore.line5"));

        meta.setLore(itemLore);
        this.setItemMeta(meta);
    }

    public static boolean isRocketLauncherItem(ItemStack compareItem) {
        String heldItemModelData = compareItem.getItemMeta().getDisplayName();
        if (compareItem.getType().equals(Material.PAPER) && heldItemModelData.equals(FilesUtil.langFileYML.getString("items.rocketLauncherItem.displayName")) && compareItem.getItemMeta().hasCustomModelData() && compareItem.getItemMeta().getCustomModelData() == FilesUtil.configFileYML.getInt("rocketLauncher")) {
            return true;
        }
        return false;
    }

}
