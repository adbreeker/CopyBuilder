package copybuilder.copybuilder.handlers;

import copybuilder.copybuilder.CopyBuilder;
import copybuilder.copybuilder.commands.CreateBuildingField;
import copybuilder.copybuilder.files.BuildingsSchemas;
import copybuilder.copybuilder.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;

public class CreateSchemaWithItem implements Listener
{
    CopyBuilder plugin;
    public CreateSchemaWithItem(CopyBuilder plugin) {this.plugin = plugin;}

    @EventHandler
    public void onRightClickWithSchemaCreator(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(event.getHand() == EquipmentSlot.HAND)
            {
                if(CreateBuildingField.checkField(event.getClickedBlock()))
                {
                    if(event.getItem().asOne().equals(ItemManager.SchemaCreator.asOne()))
                    {
                        String schemaName = event.getItem().getItemMeta().getLore().get(0);
                        event.getPlayer().sendMessage("Creating a schema");
                        event.getItem().setAmount(0);
                        BuildingsSchemas.addSchema(schemaName, CreateBuildingField.getPlayerPossibleLocation(event.getClickedBlock()), CreateBuildingField.getBlocksOnField(event.getClickedBlock()));
                    }
                }
            }
        }
    }

}
