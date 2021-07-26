package dev.naurzera.arenas.command.executors;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.events.PlayerJoinArenaEvent;
import dev.naurzera.arenas.events.PlayerLeaveArenaEvent;
import dev.naurzera.arenas.objects.Arena;
import dev.naurzera.arenas.utils.InventoryHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TeleportToArena
{
    public static void teleportTo(Player player, Arena arena)
    {
        if (!InventoryHelper.isInvCleared(player))
        {
            List<String> message = arena.formatMessage(arena.INVENTORY_NOT_CLEARED, player.getName(), arena.getName());
            Main.getInstance().getMessages().sendMessage(player, message);
            return;
        }
        try
        {
            for (Arena arena1 : Main.getInstance().getArenas().getAll())
            {
                if (arena1.containsPlayer(player))
                {
                    List<String> message = arena.formatMessage(arena.ERROR_YOU_ARE_IN_ANOTHER_ARENA, player.getName(), arena.getName());
                    Main.getInstance().getMessages().sendMessage(player, message);
                    return;
                }
            }
            player.teleport(arena.getLocation());
            player.getInventory().setContents(arena.getContents());
            player.getInventory().setArmorContents(arena.getArmor());
            Bukkit.getPluginManager().callEvent(new PlayerJoinArenaEvent(player, arena));
        } catch (Exception exception)
        {
            player.sendMessage("§c Ocorreu um erro, contate um staff.");
            exception.printStackTrace();
        }
    }
    public static boolean teleportFrom(Player player, Arena arena)
    {
        try
        {
            player.teleport(Main.getInstance().getSpawn());
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.getActivePotionEffects().clear();
            Bukkit.getPluginManager().callEvent(new PlayerLeaveArenaEvent(player, arena));
            return true;
        } catch (Exception exception){return false;}
    }
}
