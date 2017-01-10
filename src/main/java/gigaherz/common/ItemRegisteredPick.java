package gigaherz.common;

import net.minecraft.item.ItemPickaxe;

public class ItemRegisteredPick extends ItemPickaxe
{
    public ItemRegisteredPick(String name, ToolMaterial material)
    {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(getRegistryName().getResourceDomain() + "." + name);
    }
}
