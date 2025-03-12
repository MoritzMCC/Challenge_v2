package de.MoritzMCC.inventory;

import de.MoritzMCC.challengApi.AbstractChallenge;
import de.MoritzMCC.challengApi.ChallengeManager;
import de.MoritzMCC.utils.InventoryBuilder;
import de.MoritzMCC.utils.ItemBuilder;
import de.MoritzMCC.utils.combatUtils.DamageNerf;
import de.MoritzMCC.utils.combatUtils.NoHitDelayUtil;
import de.MoritzMCC.utils.combatUtils.SoupHealingUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler implements Listener {

    @EventHandler
    public void onInentoryInteract(InventoryInteractEvent event) {
        Inventory inventory = event.getInventory();
        ItemStack item = event.getWhoClicked().getItemOnCursor();
        Player player = (Player) event.getWhoClicked();
        if(item == null)return;
        if(inventory.getHolder() != null)return;
        if(inventory.equals(Inventorys.homeInventory)){
            homeInventoryHandler(item, player);
            return;
        }
        if(inventory.equals(Inventorys.combatSettingsInventory)){combatSettingsInventoryHandler(item, player ,inventory); return;}

        if (inventory.equals(Inventorys.damageInventory())){damageInventoryHandler(item,player, inventory);}

        for(AbstractChallenge challenge: ChallengeManager.getChallengesAsList()){
            if(challenge.getDisplayItem().equals(item)){
                challenge.setActivated(!challenge.isActivated());
                Bukkit.broadcastMessage(ChatColor.DARK_GREEN + challenge.getChallengeName() + " has been " + (challenge.isActivated() ? "activated" : " §c deactivated"));

            }
        }

    }

    public void homeInventoryHandler(ItemStack clickedItem, Player player) {
        if(clickedItem.equals( new ItemBuilder(Material.IRON_SWORD).withName(ChatColor.RED + "Combat settings").build())) {
            player.openInventory(Inventorys.combatSettingsInventory);
            return;
        }
        if (clickedItem.equals(new ItemBuilder(Material.BOOK).withName(ChatColor.GOLD + "Challenges").build())){
            player.openInventory(Inventorys.challengeInventory(0));
        }
    }
    public void combatSettingsInventoryHandler(ItemStack clickedItem, Player player,Inventory inventory) {
        if (clickedItem.equals( new ItemBuilder(Material.CLOCK).withName("Hit Delay " + NoHitDelayUtil.isEnabledAsString()).build())) {
            NoHitDelayUtil.toggleAktivated();
            player.openInventory(inventory);
            return;
        }
        if(clickedItem.equals(new ItemBuilder(Material.MUSHROOM_STEW).withName("Soup healing " + SoupHealingUtils.isEnabledAsString()).build())){
            SoupHealingUtils.toggleActive();
            return;
        }
        if (clickedItem.equals(new ItemBuilder(Material.WOODEN_AXE).withName("Damage nerf " + DamageNerf.isEnabledAsString()).build())){
            DamageNerf.toogleNerf();
            return;
        }
    }

    public void damageInventoryHandler(ItemStack clickedItem, Player player, Inventory inventory) {

    }


}
