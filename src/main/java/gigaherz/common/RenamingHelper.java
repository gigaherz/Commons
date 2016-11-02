package gigaherz.common;

import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Map;

public class RenamingHelper
{
    private final Map<ResourceLocation, Item> upgradeItemNames = Maps.newHashMap();
    private final Map<ResourceLocation, Block> upgradeBlockNames = Maps.newHashMap();
    private final Map<String, Class<? extends TileEntity>> nameToClassMap = ReflectionHelper.getPrivateValue(TileEntity.class, null, "field_145855_i", "nameToClassMap");

    public void addAlternativeName(Class<? extends TileEntity> clazz, String altName)
    {
        nameToClassMap.put(altName, clazz);
    }

    public void addAlternativeName(Item item, ResourceLocation altName)
    {
        upgradeItemNames.put(altName, item);
    }

    public void addAlternativeName(Block block, ResourceLocation altName)
    {
        upgradeBlockNames.put(altName, block);
        Item item = Item.getItemFromBlock(block);
        if (item != null)
            addAlternativeName(item, altName);
    }

    public void process(FMLMissingMappingsEvent ev)
    {
        for (FMLMissingMappingsEvent.MissingMapping missing : ev.get())
        {
            if (missing.type == GameRegistry.Type.ITEM
                    && upgradeItemNames.containsKey(missing.resourceLocation))
            {
                missing.remap(upgradeItemNames.get(missing.resourceLocation));
            }

            if (missing.type == GameRegistry.Type.BLOCK
                    && upgradeBlockNames.containsKey(missing.resourceLocation))
            {
                missing.remap(upgradeBlockNames.get(missing.resourceLocation));
            }
        }
    }

}
