package gigaherz.common;

import net.minecraft.item.ItemAxe;

public class ItemRegisteredAxe extends ItemAxe
{
    public ItemRegisteredAxe(String name, ToolMaterial material, float damage, float speed)
    {
        super(material, damage, speed);
        setRegistryName(name);
        setUnlocalizedName(getRegistryName().getResourceDomain() + "." + name);
    }
}
