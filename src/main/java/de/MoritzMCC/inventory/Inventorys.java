package de.MoritzMCC.inventory;

import de.MoritzMCC.challengApi.AbstractChallenge;
import de.MoritzMCC.challengApi.ChallengeManager;
import de.MoritzMCC.utils.InventoryBuilder;
import de.MoritzMCC.utils.ItemBuilder;
import de.MoritzMCC.utils.combatUtils.DamageNerf;
import de.MoritzMCC.utils.combatUtils.NoHitDelayUtil;
import de.MoritzMCC.utils.combatUtils.SoupHealingUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Inventorys {

    public static Inventory homeInventory = new InventoryBuilder(5, "Home")
            .addItem(9, new ItemBuilder(Material.IRON_SWORD).
                    withName(ChatColor.RED + "Combat settings").
                    build())
            .addItem(11, new ItemBuilder(Material.BOOK)
                    .withName(ChatColor.GOLD + "Challenges")
                    .build())
            .addItem(17, new ItemBuilder(Material.REDSTONE)
                    .withName(ChatColor.GOLD + "Damage")
                    .build())
            .withGrayGlass()
            .canTakeItemsOut(false)
            .build();

    public static Inventory combatSettingsInventory = new InventoryBuilder(3 , "CombatSettings")
            .addItem(9, new ItemBuilder(Material.CLOCK)
                    .withName("Hit Delay " + NoHitDelayUtil.isEnabledAsString())
                    .build())
            .addItem(10, new ItemBuilder(Material.MUSHROOM_STEW)
                    .withName("Soup healing " + SoupHealingUtils.isEnabledAsString())
                    .build())
            .addItem(11, new ItemBuilder(Material.WOODEN_AXE)
                    .withName("Damage nerf " + DamageNerf.isEnabledAsString())
                    .build())
            .withGrayGlass()
            .canTakeItemsOut(false)
            .build();

    public static Inventory challengeInventory(int pageNumber) {
        List<AbstractChallenge> challenges = ChallengeManager.getChallengesAsRows(pageNumber);
         Inventory inventory = new InventoryBuilder(3, "Challenges").canTakeItemsOut(false).build();
         int i = 9;
         for (AbstractChallenge challenge : challenges) {
             inventory.setItem(i++, challenge.getDisplayItem());
         }
        for(int k = 0; k<inventory.getSize(); k++){
            if (inventory.getItem(i) == null){
                inventory.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).withName("").build());
            }
        }
         return inventory;
    }

    public static Inventory damageInventory(){
        Inventory inventory = new InventoryBuilder(3, "Damage")
                .addItem(new ItemBuilder(Material.IRON_BOOTS)
                        .withName("Fall Damage")
                        .build())
                .addItem(new ItemBuilder(Material.TNT)
                        .withName("Explosion Damage")
                        .build())
                .addItem(new ItemBuilder(Material.ZOMBIE_HEAD)
                        .withName("Entity Damage")
                        .build())
                .addItem(new ItemBuilder(Material.WATER)
                        .withName("Drowning damage")
                        .build())
                .addItem(new ItemBuilder(Material.LAVA)
                        .withName("Fire Damage")
                        .build())
                .canTakeItemsOut(false).build();
        return inventory;
    }


}
