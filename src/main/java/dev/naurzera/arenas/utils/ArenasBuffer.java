package dev.naurzera.arenas.utils;

import dev.naurzera.arenas.Main;
import dev.naurzera.arenas.objects.Arena;
import org.bukkit.Location;

import java.io.IOException;
import java.util.Set;

public class ArenasBuffer
{
    Config config;
    Base64Translator translator = new Base64Translator();
    public ArenasBuffer(Config config)
    {
        this.config = config;
        if (!(config.existeConfig()))config.saveDefaultConfig();
        config.reloadConfig();
    }
    public void loadArenas() throws IOException
    {
        try
        {
            Set<String> arenasNames = config.getConfig()
                    .getConfigurationSection("arenas").getKeys(false);
            for (String section : arenasNames) {
                String name = section.toLowerCase();
                try
                {
                    Arena arena = translator.fromString(config.getString("arenas." + name));
                    Main.getInstance().getArenas().add(arena);
                }catch (Exception e)
                {
                    System.out.println("Erro tentando carregar a arena "+name+":");
                    e.printStackTrace();
                }
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Erro: Nenhuma arena encontrada na config de arenas.");
        }
    }
    public Arena getArena(String arena1) throws IOException
    {
        try
        {
            return translator.fromString(config.getString("arenas." + arena1));
        }
        catch (NullPointerException e)
        {
            System.out.println("Erro: Nenhuma arena encontrada na config de arenas.");
        }
        return null;
    }
    public void saveArena(Arena arena1)
    {
        Arena arena = new Arena(arena1);
        String arenaString = translator.toString(arena);
        String name = arena.getName().toLowerCase();
        config.set("arenas."+name, arenaString);
        config.saveConfig();
    }
    public void removeArena(Arena arena)
    {
        String name = arena.getName().toLowerCase();
        config.set("arenas."+name, null);
        config.saveConfig();
    }
    public void saveArenas()
    {
        for (Arena arena1 : Main.getInstance().getArenas().getAll())
        {
            Arena arena = new Arena(arena1);
            String arenaString = translator.toString(arena);
            String name = arena.getName().toLowerCase();
            config.set("arenas." + name, arenaString);
            config.saveConfig();
        }
    }

    public void setSpawn(Location location)
    {
        String arenaString = translator.locationToString(location);
        config.set("saida", arenaString);
        config.saveConfig();
    }
    public Location getSpawn()
    {
        try
        {
            return translator.locationFromString(config.getString("saida"));
        }
        catch (IOException exception)
        {
            return null;
        }
    }
}
