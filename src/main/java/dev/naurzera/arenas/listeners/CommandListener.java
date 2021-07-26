package dev.naurzera.arenas.listeners;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandListener
    implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL)
    public void onCommand(PlayerCommandPreprocessEvent e)
    {
        String command = e.getMessage().split(" ")[0].replaceAll("/","");
        for (Arena arena : Main.getInstance().getArenas().getAll())
        {
            if (arena.containsPlayer(e.getPlayer()))
            {
                boolean cancel = true;
                if (command.equalsIgnoreCase(arena.getName())) cancel = false;
                if (command.equalsIgnoreCase("arena")) cancel = false;
                if (command.equalsIgnoreCase("arenas")) cancel = false;
                if (cancel)
                {
                    List<String> message = arena.formatMessage(arena.ERROR_BLOCKED_COMMAND, e.getPlayer().getName(), arena.getName());
                    Main.getInstance().getMessages().sendMessage(e.getPlayer(), message);
                    e.setCancelled(true);
                }
                break;
            }
        }
    }
}
