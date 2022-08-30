package copybuilder.copybuilder.commands;

import copybuilder.copybuilder.files.BuildingsSchemas;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DeleteSchema implements CommandExecutor
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

        if (command.getName().equalsIgnoreCase("cb_delete_schema"))
        {
            if(Arrays.stream(args).count() == 0)
            {
                player.sendMessage("No schema name");
                return true;
            }
            else
            {
                if(Arrays.stream(args).count() == 1)
                {
                    player.sendMessage("After schema name you have to write \"CONFIRM\" to confirm deleting schema");
                }
                if(Arrays.stream(args).count() > 1)
                {
                    if(args[1].equals("CONFIRM"))
                    {
                        BuildingsSchemas.deleteSchema(args[0], player);
                    }
                    else
                    {
                        player.sendMessage("After schema name you have to write \"CONFIRM\" to confirm deleting schema");
                    }
                }
            }
        }
        return true;
    }
}
