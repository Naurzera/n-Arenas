package dev.naurzera.arenas.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryHelper
{
    public static boolean isInvCleared(Player player)
    {
        boolean vazio = true;
        for (ItemStack item : player.getInventory().getContents())
        {
            if (item==null) continue;
            if (item.getType()==null) continue;
            if (item.getType().equals(Material.AIR)) continue;
            vazio = false;
        }
        return vazio;
    }
}
