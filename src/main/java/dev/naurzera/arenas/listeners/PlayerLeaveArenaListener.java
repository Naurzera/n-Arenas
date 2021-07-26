package dev.naurzera.arenas.listeners;

import dev.naurzera.arenas.events.PlayerJoinArenaEvent;
import dev.naurzera.arenas.events.PlayerLeaveArenaEvent;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerLeaveArenaListener
    implements Listener
{

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = false)
    public void playerLeaveArena(PlayerLeaveArenaEvent e)
    {
        Arena arena = e.getArena();
        arena.sendMessage(e.getPlayer(), arena.formatMessage(arena.LEAVING, e.getPlayer().getName(), arena.getName()));
        arena.removePlayer(e.getPlayer());
        for (Player receiver : e.getArena().getPlayers())
        {
            arena.sendMessage(receiver, arena.formatMessage(arena.OTHER_LEAVING, e.getPlayer().getName(), arena.getName()));
        }
    }
}
