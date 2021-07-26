package dev.naurzera.arenas.command;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.manager.ArenaManager;
import dev.naurzera.arenas.command.executors.TeleportToArena;
import dev.naurzera.arenas.objects.Arena;
import dev.naurzera.arenas.objects.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ArenaCommand
    extends BukkitCommand
{
    public ArenaCommand(String command)
    {
        super(command);
    }

    String staffPermission = Main.getInstance().getConfig().getString("permissao-staff");
    ArenaManager manager = new ArenaManager();
    @Override
    public boolean execute(CommandSender sender, String arg, String[] args)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Messages messages = Main.getInstance().getMessages();
                if (args.length<1)
                {
                    if (sender.hasPermission(staffPermission))
                    {
                        messages.sendMessage(sender, messages.HELP_STAFF);
                    }
                    else
                    {
                        messages.sendMessage(sender, messages.HELP);
                    }
                    return;
                }
                else {
                    String argument = args[0];
                    if (sender.hasPermission(staffPermission))
                    {
                        boolean isCreate = is(argument, "criar") && args.length > 1;
                        boolean isDel = is(argument, "del") && args.length > 1;
                        boolean isSetKit = is(argument, "setkit") && args.length > 1;
                        boolean isSetIn = is(argument, "setentrada") && args.length > 1;
                        boolean isSetOut = is(argument, "setsaida");
                        boolean isReload = is(argument, "reload");
                        if (isCreate) {
                            if (!manager.createArena(sender, args[1]))
                                messages.sendMessage(sender, messages.HELP_STAFF);
                            return;
                        }
                        else if (isDel) {
                            if (!manager.removeArena(sender, args[1]))
                                messages.sendMessage(sender, messages.HELP_STAFF);
                            return;
                        }
                        else if (isSetKit) {
                            if (!manager.setKit(sender, args[1]))
                                messages.sendMessage(sender, messages.HELP_STAFF);
                            return;
                        }
                        else if (isSetIn) {
                            if (!manager.setArenaLocation(sender, args[1]))
                                messages.sendMessage(sender, messages.HELP_STAFF);
                            return;
                        }
                        else if (isSetOut) {
                            if (!manager.setSpawn(sender))
                                messages.sendMessage(sender, messages.HELP_STAFF);
                            return;
                        }
                        else if (isReload) {
                            Main.getInstance().reloadConfig();
                            sender.sendMessage(" Configuração recarregada!");
                        }
                    }
                    if (!(sender instanceof Player))
                    {
                        sender.sendMessage("§cComando exclusivo para jogadores!");
                        return;
                    }
                    Player player = (Player) sender;
                    boolean encontrou = false;
                    for (Arena arena : Main.getInstance().getArenas().getAll())
                    {
                        if (args[0].equalsIgnoreCase(arena.getName()))
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
                            encontrou = true;
                        }
                    }
                    if (!encontrou)
                    {
                        if (sender.hasPermission(staffPermission))
                        {
                            messages.sendMessage(sender, messages.HELP_STAFF);
                        }
                        else
                        {
                            messages.sendMessage(sender, messages.HELP);
                        }
                    }
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
        return false;
    }

    private boolean is(String argument1, String argument2)
    {
        return argument1.equalsIgnoreCase(argument2);
    }

}
