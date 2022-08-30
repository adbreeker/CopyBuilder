package copybuilder.copybuilder.commands;

import copybuilder.copybuilder.files.BuildingsSchemas;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class BuildSchema implements CommandExecutor
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

        if (command.getName().equalsIgnoreCase("cb_build"))
        {
            if(Arrays.stream(args).count() == 0)
            {
                sender.sendMessage("No schema name");
                return true;
            }
            else
            {
                BuildingsSchemas.buildSchema(args[0], player);
            }
        }
        return true;
    }
}
