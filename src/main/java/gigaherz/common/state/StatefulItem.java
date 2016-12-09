package gigaherz.common.state;

public interface StatefulItem
{
    IItemStateManager getStateManager();

    IItemState getDefaultState();
}
