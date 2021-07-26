package dev.naurzera.arenas.listeners;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.events.PlayerDeathArenaEvent;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener
    implements Listener
{
    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        for (Arena arena : Main.getInstance().getArenas().getAll())
        {
            if (arena.containsPlayer(e.getEntity()))
            {
                e.getDrops().clear();
                Bukkit.getPluginManager().callEvent(new PlayerDeathArenaEvent(e.getEntity(), e.getEntity().getKiller(), arena));
            }
        }
    }
}
