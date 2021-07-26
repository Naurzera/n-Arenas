package dev.naurzera.arenas.utils;

import dev.naurzera.arenas.objects.Arena;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Base64Translator
{
    String toString(Arena arena) throws IllegalStateException
    {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(arena);

            dataOutput.close();

            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Error whilst translate arena to base64, please contact the developer", e);
        }
    }

    Arena fromString(String data) throws IOException
    {
        try
        {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            Arena arena = (Arena) dataInput.readObject();

            dataInput.close();

            return arena;
        }
        catch (ClassNotFoundException | IOException e)
        {
            throw new IOException("Error whilst loading arena, please contact the developer", e);
        }
    }





    String locationToString(Location location) throws IllegalStateException
    {
        try
        {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeObject(location);

            dataOutput.close();

            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e)
        {
            throw new IllegalStateException("Error whilst translate arena to base64, please contact the developer", e);
        }
    }

    Location locationFromString(String data) throws IOException
    {
        try
        {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

            Location location = (Location) dataInput.readObject();

            dataInput.close();

            return location;
        }
        catch (ClassNotFoundException | IOException e)
        {
            throw new IOException("Error whilst loading arena, please contact the developer", e);
        }
    }
}
