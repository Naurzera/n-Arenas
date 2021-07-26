package dev.naurzera.arenas.listeners;

import dev.naurzera.arenas.events.PlayerDeathArenaEvent;
import dev.naurzera.arenas.events.PlayerJoinArenaEvent;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerDeathArenaListener
    implements Listener
{

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void playerDeathArena(PlayerDeathArenaEvent e)
    {
        Arena arena = e.getArena();
        arena.removePlayer(e.getPlayer());
        for (Player receiver : e.getArena().getPlayers())
        {
            arena.sendMessage(receiver, arena.formatMessage(arena.OTHER_DEATH, e.getPlayer().getName(), arena.getName()));
        }
        arena.sendMessage(e.getPlayer(), arena.formatMessage(arena.DEATH, e.getKiller().getName(), arena.getName()));
    }
}
