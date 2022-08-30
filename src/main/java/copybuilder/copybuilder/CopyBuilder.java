package copybuilder.copybuilder;

import copybuilder.copybuilder.commands.*;
import copybuilder.copybuilder.files.BuildingsSchemas;
import copybuilder.copybuilder.handlers.CreateSchemaWithItem;
import copybuilder.copybuilder.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CopyBuilder extends JavaPlugin {

    @Override
    public void onEnable()
    {
        // files---------------------------------------------------------------------------------------------------------
        BuildingsSchemas.setup();
        BuildingsSchemas.get().options().copyDefaults(true);
        BuildingsSchemas.save();

        // commands------------------------------------------------------------------------------------------------------
        getCommand("cb_make_field").setExecutor(new CreateBuildingField());
        getCommand("cb_schema_creator").setExecutor(new GiveSchemaCreator());
        getCommand("cb_schemas").setExecutor(new ListOfSchemas());
        getCommand("cb_build").setExecutor(new BuildSchema());
        getCommand("cb_delete_schema").setExecutor(new DeleteSchema());

        // handlers------------------------------------------------------------------------------------------------------
        getServer().getPluginManager().registerEvents(new CreateSchemaWithItem(this),this);

        // items---------------------------------------------------------------------------------------------------------
        ItemManager.init_items();
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}
