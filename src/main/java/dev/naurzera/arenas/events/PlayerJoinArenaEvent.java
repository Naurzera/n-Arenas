package dev.naurzera.arenas.events;

import dev.naurzera.arenas.objects.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJoinArenaEvent
    extends Event
{
    private static final HandlerList handlers = new HandlerList();

    Player player;
    Arena arena;
    public PlayerJoinArenaEvent(Player player, Arena arena)
    {
        this.player = player;
        this.arena = arena;
    }

    public Player getPlayer()
    {
        return player;
    }
    public Arena getArena()
    {
        return arena;
    }

    @Override
    public HandlerList getHandlers()
    {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
