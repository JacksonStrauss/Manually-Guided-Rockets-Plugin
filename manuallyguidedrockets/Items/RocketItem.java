package com.francais.manuallyguidedrockets.Items;

import com.francais.manuallyguidedrockets.Files.FilesUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class RocketItem extends ItemStack {

    private ItemMeta meta;
    private ArrayList<String> itemLore;

    public RocketItem() {
        super(Material.PAPER);
        meta = super.getItemMeta();
        itemLore = new ArrayList<String>();

        meta.setCustomModelData(FilesUtil.configFileYML.getInt("rocket"));
        meta.setDisplayName(FilesUtil.langFileYML.getString("items.rocketItem.displayName"));

        itemLore.add(FilesUtil.langFileYML.getString("items.rocketItem.lore.line1"));

        meta.setLore(itemLore);
        this.setItemMeta(meta);
    }

    public static boolean isRocketItem(ItemStack compareItem) {
        String heldItemModelData = compareItem.getItemMeta().getDisplayName();
        if (compareItem.getType().equals(Material.PAPER) && heldItemModelData.equals(FilesUtil.langFileYML.getString("items.rocketItem.displayName")) && compareItem.getItemMeta().hasCustomModelData() && compareItem.getItemMeta().getCustomModelData() == FilesUtil.configFileYML.getInt("rocket")) {
            return true;
        }
        return false;
    }


}
