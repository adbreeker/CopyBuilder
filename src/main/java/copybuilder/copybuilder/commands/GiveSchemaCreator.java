package copybuilder.copybuilder.commands;

import copybuilder.copybuilder.items.ItemManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveSchemaCreator implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("cb_schema_creator"))
        {
            if(args.length == 0)
            {
                sender.sendMessage("No schema name value");
                return true;
            }
            ItemManager.MakeSchemaName(args[0]);
            player.getInventory().setItemInMainHand(ItemManager.SchemaCreator);

        }
        return true;
    }
}
