package dev.naurzera.arenas.events;

import dev.naurzera.arenas.objects.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDeathArenaEvent
    extends Event
{
    private static final HandlerList handlers = new HandlerList();

    Player player;
    Player killer;
    Arena arena;
    public PlayerDeathArenaEvent(Player player, Player killer, Arena arena)
    {
        this.player = player;
        this.killer = killer;
        this.arena = arena;
    }

    public Player getPlayer()
    {
        return player;
    }
    public Player getKiller(){return killer;}
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
