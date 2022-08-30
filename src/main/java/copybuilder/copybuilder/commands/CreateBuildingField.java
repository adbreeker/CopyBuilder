package copybuilder.copybuilder.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateBuildingField implements CommandExecutor
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

        if (command.getName().equalsIgnoreCase("cb_make_field"))
        {
            Location pl = player.getLocation();
            int radius;
            try
            {
                if(Arrays.stream(args).count() == 0)
                {
                    player.sendMessage("No radius value");
                    return true;
                }
                else
                {
                    radius = Integer.parseInt(args[0]);
                }
            }
            catch (NumberFormatException ex)
            {
                player.sendMessage("Wrong radius value");
                return true;
            }

            if(Arrays.stream(args).count() > 1)
            {
                if(args[1].equals("clear"))
                {
                    for(int x = ((radius+5) * -1); x<(radius+5); x++)
                    {
                        for(int y = -10; (pl.getBlockY()+y)<=319; y++)
                        {
                            for(int z = ((radius+5) * -1); z<(radius+5); z++)
                            {
                                Block block_to_change = pl.getWorld().getBlockAt(pl.getBlockX()+x, pl.getBlockY()+y, pl.getBlockZ()+z);
                                if((x < (radius*-1) || x > radius) || (z < (radius*-1) || z > radius) || y!= -1)
                                {
                                    block_to_change.setType(Material.AIR);
                                }
                                else
                                {
                                    if(x == 0 && z == 0 && y == -1)
                                    {
                                        block_to_change.setType(Material.DIAMOND_BLOCK);
                                    }
                                    else
                                    {
                                        block_to_change.setType(Material.BEDROCK);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                for(int x = ((radius+5) * -1); x<(radius+5); x++)
                {
                    for(int y = -5; y<=-1; y++)
                    {
                        for(int z = ((radius+5) * -1); z<(radius+5); z++)
                        {
                            Block block_to_change = pl.getWorld().getBlockAt(pl.getBlockX()+x, pl.getBlockY()+y, pl.getBlockZ()+z);
                            if((x < (radius*-1) || x > radius) || (z < (radius*-1) || z > radius) || y!= -1)
                            {
                                block_to_change.setType(Material.AIR);
                            }
                            else
                            {
                                if(x == 0 && z == 0 && y == -1)
                                {
                                    block_to_change.setType(Material.DIAMOND_BLOCK);
                                }
                                else
                                {
                                    block_to_change.setType(Material.BEDROCK);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkField(Block block)
    {
        if(block.getType()!= Material.DIAMOND_BLOCK)
        {
            return false;
        }

        List<Block> adjacentBlocks = new ArrayList<>();
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX()+1,block.getY(), block.getZ()+1));
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX()+1,block.getY(), block.getZ()));
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX()+1,block.getY(), block.getZ()-1));
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX(),block.getY(), block.getZ()+1));
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX(),block.getY(), block.getZ()-1));
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX()-1,block.getY(), block.getZ()+1));
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX()-1,block.getY(), block.getZ()));
        adjacentBlocks.add(block.getWorld().getBlockAt(block.getX()-1,block.getY(), block.getZ()-1));

        for(Block checkBlock : adjacentBlocks)
        {
            if(checkBlock.getType()!=Material.BEDROCK)
            {
                return false;
            }
        }
        return true;
    }

    public static int[] getXandZ(Block center)
    {
        int addX = 1;
        int addZ = 1;
        int x,z;
        while(center.getWorld().getBlockAt(center.getX()+addX, center.getY(), center.getZ()).getType() == Material.BEDROCK)
        {
            addX++;
        }
        x = addX - 1;
        while(center.getWorld().getBlockAt(center.getX(), center.getY(), center.getZ()+addZ).getType() == Material.BEDROCK)
        {
            addZ++;
        }
        z = addZ - 1;

        int[] XandZ = {x, z};
        return XandZ;
    }

    public static List<Block> getBlocksOnField(Block center)
    {
        List<Block> blocks = new ArrayList<>();
        int radiusX = getXandZ(center)[0];
        int radiusZ = getXandZ(center)[1];

        for(int x = (radiusX * -1); x<=radiusX; x++)
        {
            for(int y = 1; (center.getY()+y) <= 319; y++)
            {
                for(int z = (radiusZ * -1); z<=radiusZ; z++)
                {
                    Block block_to_change = center.getWorld().getBlockAt(center.getX()+x, center.getY()+y, center.getZ()+z);
                    if(block_to_change.getType() != Material.AIR)
                    {
                        blocks.add(block_to_change);
                    }
                }
            }
        }
        return blocks;
    }

    public static Location getPlayerPossibleLocation(Block center)
    {
        for(int y=1; (center.getY()+y) <= 318; y++)
        {
            Block first = center.getWorld().getBlockAt(center.getX(), center.getY()+y, center.getZ());
            Block second = center.getWorld().getBlockAt(center.getX(), center.getY()+y+1, center.getZ());
            if(first.getType() == Material.AIR && second.getType() == Material.AIR)
            {
                return first.getLocation();
            }
        }
        Location loc = center.getLocation();
        loc.setY(320);
        return loc;
    }
}
