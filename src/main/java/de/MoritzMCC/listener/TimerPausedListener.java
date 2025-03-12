package de.MoritzMCC.listener;

import de.MoritzMCC.challenge.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TimerPausedListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(Main.getInstance().getTimer().isRunning())event.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(Main.getInstance().getTimer().isRunning())event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(Main.getInstance().getTimer().isRunning())event.setCancelled(true);
    }
    @EventHandler
    public void onEntityDamgeEvent(EntityDamageEvent event) {
        if(Main.getInstance().getTimer().isRunning())event.setCancelled(true);
    }

}
