package fun.lewisdev.inventoryfull.listeners;

import fun.lewisdev.inventoryfull.EventProcessor;
import fun.lewisdev.inventoryfull.InventoryFullPlusPlugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PickupItemListener implements Listener {

    private final EventProcessor eventProcessor;

    public PickupItemListener(InventoryFullPlusPlugin plugin) {
        this.eventProcessor = plugin.getEventProcessor();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPickupItem(EntityPickupItemEvent event) {
        if (event.getEntityType() != EntityType.PLAYER)
            return;

        if (event.getRemaining() == 0)
            return;

        eventProcessor.process((Player) event.getEntity(), List.of(new ItemStack(event.getItem().getItemStack().getType(), 8192)));
    }

}
