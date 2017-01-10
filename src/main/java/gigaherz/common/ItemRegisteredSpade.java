package gigaherz.common;

import net.minecraft.item.ItemSpade;

public class ItemRegisteredSpade extends ItemSpade
{
    public ItemRegisteredSpade(String name, ToolMaterial material)
    {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(getRegistryName().getResourceDomain() + "." + name);
    }
}
