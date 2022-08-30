package copybuilder.copybuilder.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemManager
{
    public static ItemStack SchemaCreator;

    public static void init_items()
    {
        createSchemaCreator();
    }

    // electric Lamp ------------------------------------------------------------------------------------------------------------------------- electric lamp
    private static void createSchemaCreator()
    {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Schema Creator");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        SchemaCreator = item;
    }

    public static void MakeSchemaName(String name)
    {
        ItemStack item = SchemaCreator;
        ItemMeta meta = item.getItemMeta();
        List <String> lore = new ArrayList<>();
        lore.add(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        SchemaCreator = item;
    }
}
