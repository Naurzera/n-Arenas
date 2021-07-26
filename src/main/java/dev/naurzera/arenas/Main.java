package dev.naurzera.arenas;

import dev.naurzera.arenas.command.ArenaCommand;
import dev.naurzera.arenas.command.ArenaListCommand;
import dev.naurzera.arenas.command.DirectTeleportCommand;
import dev.naurzera.arenas.command.executors.TeleportToArena;
import dev.naurzera.arenas.listeners.*;
import dev.naurzera.arenas.objects.Arena;
import dev.naurzera.arenas.objects.Messages;
import dev.naurzera.arenas.utils.ArenasBuffer;
import dev.naurzera.arenas.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main
    extends JavaPlugin
{
    String prefix = "[n-Arenas]";
    Arenas arenas = null;
    ArenasBuffer buffer = null;
    Messages messages = null;
    final Map<String, BukkitCommand> directCommands = new LinkedHashMap<String, BukkitCommand>();

    static Main instance = null;
    public static Main getInstance(){return instance;}

    @Override
    public void onEnable()
    {
        cslMsg(ChatColor.YELLOW, "----------------------------------------");
        long initialTime = System.currentTimeMillis();


        instance = this;
        cslMsg(ChatColor.YELLOW, prefix+" Carregando config...");
        saveDefaultConfig();
        reloadConfig();
        new Arena();
        messages = new Messages();
        arenas = new Arenas();
        buffer = new ArenasBuffer(new Config(this, "arenas.yml"));

        cslMsg(ChatColor.YELLOW, prefix+" Carregando arenas...");
        try
        {
            buffer.loadArenas();
            arenas.setSaida(buffer.getSpawn());
        }
        catch (Exception e)
        {
            cslMsg(ChatColor.RED, prefix+" Erro ao carregar arenas ou saída da config.");
            e.printStackTrace();
        }

        cslMsg(ChatColor.YELLOW, prefix+" Carregando comandos...");
        registerCommands();

        cslMsg(ChatColor.YELLOW, prefix+" Carregando eventos...");
        registerListeners();

        long tookTime = System.currentTimeMillis()-initialTime;
        cslMsg(ChatColor.YELLOW, prefix+" Plugin habilitado em "+tookTime+"ms!");
        cslMsg(ChatColor.YELLOW, "----------------------------------------");
    }

    @Override
    public void onDisable()
    {
        for (Arena arena : arenas.getAll())
        {
            for (Player player : arena.getPlayers())
            {
                TeleportToArena.teleportFrom(player, arena);
            }
        }
    }

    void cslMsg(ChatColor color, String mensagem){Bukkit.getConsoleSender().sendMessage(color+mensagem);}

    public ArenasBuffer getBuffer()
    {
        return this.buffer;
    }
    public Messages getMessages()
    {
        return this.messages;
    }
    public Arenas getArenas()
    {
        return this.arenas;
    }
    public Location getSpawn()
    {
        return this.arenas.getSaida();
    }
    public void setSpawn(Location location)
    {
        this.arenas.setSaida(location);
    }

    @Override
    public void reloadConfig()
    {
        super.reloadConfig();
    }

    private void registerCommands()
    {
        try
        {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register("arena", new ArenaCommand("arena"));
            commandMap.register("arenas", new ArenaListCommand());
            boolean directCommands = getConfig().getBoolean("criar-comandos");
            if (directCommands)
            {
                try
                {
                    for (Arena arena : this.arenas.getAll())
                    {
                        String name = arena.getName().toLowerCase();
                        commandMap.register(name, new DirectTeleportCommand(name));
                    }
                }catch (NullPointerException exception)
                {
                    System.out.println("Erro: Nenhuma arena encontrada (nenhum comando registrado)");
                }
            }
        }
        catch (Exception e)
        {
            setEnabled(false);
            throw new RuntimeException("Failed to register commands", e);
        }
    }
    public void registerCommand(Arena arena)
    {
        try
        {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            boolean directCommands = getConfig().getBoolean("criar-comandos");
            if (directCommands)
            {
                String command = arena.getName().toLowerCase();
                commandMap.register(command, new DirectTeleportCommand(command));
            }
        }
        catch (Exception e)
        {
            setEnabled(false);
            throw new RuntimeException("Failed to register commands", e);
        }
    }
    private void registerListeners()
    {
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathArenaListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinArenaListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveArenaListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLogoutListener(), this);
        Bukkit.getPluginManager().registerEvents(new CommandListener(), this);
    }
}
