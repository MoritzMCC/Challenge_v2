package de.MoritzMCC.challengApi;

import de.MoritzMCC.challenge.Main;
import de.MoritzMCC.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractChallenge implements Listener {

    private String challengeName;
    private ItemStack displayItem;
    private Boolean isActivated = false;


    public AbstractChallenge(String challengeName, ItemStack displayItem) {
        this.challengeName = challengeName;
        this.displayItem = displayItem;
        ChallengeManager.addChallenge(this);
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    public AbstractChallenge(String challengeName, Material displayItemMaterial) {
        this.displayItem = new ItemBuilder(displayItemMaterial).
                withName(challengeName).
                build();

        this.challengeName = challengeName;
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {}

    @EventHandler
    public void onDamage(EntityDamageEvent event) {}

    @EventHandler
    public void onHitEntity(EntityDamageByEntityEvent event) {}

    @EventHandler
    public void onMove(PlayerMoveEvent event) {}

    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {}

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {}

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent event) {}

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event) {}

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event) {}

    @EventHandler
    public void onAdvancementDone(PlayerAdvancementDoneEvent event) {}

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {}
    @EventHandler
    public void onPickUpItem(EntityPickupItemEvent event) {}




    protected void failedChallenge(Player player) {
        Bukkit.getOnlinePlayers().forEach(p -> {
            p.setGameMode(GameMode.SPECTATOR);
        });
        Main.getInstance().getTimer().setRunning(false);
        Bukkit.broadcastMessage("You failed the challenge.");
        Bukkit.broadcastMessage( player.getName() +" is guilty of failing");
    }

    public String getChallengeName() {
        return challengeName;
    }
    public ItemStack getDisplayItem() {
        return displayItem;
    }
    public Boolean isActivated() {
        return isActivated;
    }
    public void setActivated(Boolean isAktivated) {
        this.isActivated = isAktivated;
    }
}
