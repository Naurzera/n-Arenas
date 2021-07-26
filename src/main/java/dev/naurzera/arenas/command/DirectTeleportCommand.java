package dev.naurzera.arenas.command;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.command.executors.TeleportToArena;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.List;

public class DirectTeleportCommand
    extends BukkitCommand
{
    public DirectTeleportCommand(String arena)
    {
        super(arena);
        this.arena = Main.getInstance().getArenas().get(arena);
    }
    Arena arena;
    @Override
    public boolean execute(CommandSender sender, String arg, String[] args) {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (!(sender instanceof Player))
                {
                    sender.sendMessage("§cComando exclusivo para jogadores!");
                    return;
                }
                Player player = (Player) sender;
                try
                {
                    if (Main.getInstance().getArenas().contains(arena))
                    {
                        if (arena.isOpen())
                        {
                            if (!arena.getPlayers().contains(player))
                            {
                                TeleportToArena.teleportTo(player, arena);
                            }
                            else
                            {
                                if (!TeleportToArena.teleportFrom(player, arena))
                                {
                                    arena.sendMessage(sender, arena.ERROR_EXIT_NOT_SET);
                                }
                            }
                        }
                        else
                        {
                            List<String> message = arena.formatMessage(arena.ERROR_ARENA_NOT_EXISTS, sender.getName(), arena.getName());
                            arena.sendMessage(sender, message);
                        }
                    }
                    else
                    {
                        List<String> message = arena.formatMessage(arena.ERROR_ARENA_NOT_EXISTS, sender.getName(), arena.getName());
                        arena.sendMessage(sender, message);
                    }
                } catch (NullPointerException e)
                {
                    List<String> message = arena.formatMessage(arena.ERROR_ARENA_NOT_EXISTS, sender.getName(), arena.getName());
                    arena.sendMessage(sender, message);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
        return false;
    }
}
