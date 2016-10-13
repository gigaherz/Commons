package gigaherz.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemRegisteredFood extends ItemFood
{
    public ItemRegisteredFood(String name, int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        setRegistryName(name);
        setUnlocalizedName(getRegistryName().getResourceDomain() + "." + name);
        setCreativeTab(CreativeTabs.FOOD);
    }
}
