package copybuilder.copybuilder.files;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingsSchemas
{
    private static File file;
    private static FileConfiguration customFile;

    public static void setup()
    {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("CopyBuilder").getDataFolder(), "buildings_schemas.yml");
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException ex)
            {

            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get()
    {
        return customFile;
    }

    public static void addSchema(String schema_name, Location make_around, List<Block> blocks)
    {
        List<String> SchemaBlocks = makeSchemaBlocksList(make_around, blocks);

        customFile.addDefault(schema_name, SchemaBlocks);
        customFile.set(schema_name, SchemaBlocks);

        save();
        reload();
    }

    public static void save()
    {
        try
        {
            customFile.save(file);
        }
        catch(IOException ex)
        {
            save();
        }
    }

    public static void reload()
    {
        customFile = YamlConfiguration.loadConfiguration(file);
    }


    public static List<String> makeSchemaBlocksList(Location make_around, List<Block> blocks)
    {
        List<String> SchemaBlocks = new ArrayList<>();

        for(Block block : blocks)
        {
            int x = block.getX() - make_around.getBlockX();
            int y = block.getY() - make_around.getBlockY();
            int z = block.getZ() - make_around.getBlockZ();

            String material = block.getType().toString();
            String cords = x + " " + y + " " + z;

            String schemablock = cords + "/" + material;
            SchemaBlocks.add(schemablock);
        }
        return SchemaBlocks;
    }

    public static void buildSchema(String schema, Player player)
    {
        if(!customFile.contains(schema))
        {
            player.sendMessage("This schema isnt exists!");
        }
        else
        {
            Location pl = player.getLocation();
            List<String> blocks = (List<String>)  customFile.getList(schema);
            for(String block : blocks)
            {
                Material material = Material.getMaterial(block.split("/")[1]);
                int x = Integer.parseInt(block.split("/")[0].split(" ")[0]);
                int y = Integer.parseInt(block.split("/")[0].split(" ")[1]);
                int z = Integer.parseInt(block.split("/")[0].split(" ")[2]);

                player.getWorld().getBlockAt(pl.getBlockX()+x, pl.getBlockY()+y, pl.getBlockZ()+z).setType(material);
            }
        }
    }
}
