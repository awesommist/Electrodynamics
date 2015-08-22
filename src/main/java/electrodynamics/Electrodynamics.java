package electrodynamics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import dynamics.config.BlockInstances;
import dynamics.config.ItemInstances;
import dynamics.config.game.ModStartupHelper;
import dynamics.config.properties.ConfigProcessing;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Electrodynamics.MODID, name = Electrodynamics.NAME, version = Electrodynamics.VERSION, dependencies = Electrodynamics.DEPENDENCIES)
public class Electrodynamics {

    public static final String MODID = "elecdyn";
    public static final String NAME = "Electrodynamics";
    public static final String VERSION = "$VERSION$";
    public static final String PROXY_SERVER = "electrodynamics.common.ServerProxy";
    public static final String PROXY_CLIENT = "electrodynamics.client.ClientProxy";
    public static final String DEPENDENCIES = "required-after:dynamiclib@[$LIB-VERSION$,$NEXT-LIB-VERSION$)";

    @Mod.Instance(MODID)
    public static Electrodynamics instance;

    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER)
    public static IElectrodynamicsProxy proxy;

    public static class Blocks implements BlockInstances {}

    public static class Items implements ItemInstances {}

    public static CreativeTabs tabElectrodynamics = new CreativeTabs("tabElectrodynamics") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(net.minecraft.init.Blocks.quartz_block);
        }
    };

    public static int renderIdFull;

    public static int renderIdFlat;

    private final ModStartupHelper startupHelper = new ModStartupHelper(MODID) {
        @Override
        protected void populateConfig(Configuration config) {
            ConfigProcessing.processAnnotations(MODID, config, Config.class);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        startupHelper.registerBlocksHolder(Blocks.class);
        startupHelper.registerItemsHolder(Items.class);
        startupHelper.preInit(event.getSuggestedConfigurationFile());

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @EventHandler
    public void handleRenames(FMLMissingMappingsEvent event) {
        startupHelper.handleRenames(event);
    }
}