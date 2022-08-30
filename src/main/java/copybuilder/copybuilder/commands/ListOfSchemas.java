package copybuilder.copybuilder.commands;

import copybuilder.copybuilder.files.BuildingsSchemas;
import copybuilder.copybuilder.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListOfSchemas implements CommandExecutor
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

        if (command.getName().equalsIgnoreCase("cb_schemas"))
        {
            String schemas = "Available schemas: ";
            for(String schema : BuildingsSchemas.get().getKeys(false))
            {
                schemas+= schema + ", ";
            }
            player.sendMessage(schemas);
        }
        return true;
    }
}
