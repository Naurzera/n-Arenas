package dev.naurzera.arenas.listeners;

import dev.naurzera.arenas.events.PlayerJoinArenaEvent;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerJoinArenaListener
    implements Listener
{

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void playerJoinArena(PlayerJoinArenaEvent e)
    {
        Arena arena = e.getArena();
        for (Player receiver : e.getArena().getPlayers())
        {
            arena.sendMessage(receiver, arena.formatMessage(arena.OTHER_ENTERING, e.getPlayer().getName(), arena.getName()));
        }
        arena.sendMessage(e.getPlayer(), arena.formatMessage(arena.ENTERING, e.getPlayer().getName(), arena.getName()));
        arena.addPlayer(e.getPlayer());
    }
}
