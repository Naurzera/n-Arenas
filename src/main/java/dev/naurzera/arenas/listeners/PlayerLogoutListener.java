package dev.naurzera.arenas.listeners;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.command.executors.TeleportToArena;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLogoutListener
    implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDisconnect(PlayerQuitEvent event)
    {
        for (Arena arena : Main.getInstance().getArenas().getAll())
        {
            if (arena.containsPlayer(event.getPlayer()))
            {
                TeleportToArena.teleportFrom(event.getPlayer(), arena);
            }
        }
    }
}
