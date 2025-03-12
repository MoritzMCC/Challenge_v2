package de.MoritzMCC.challengApi.challenges;

import de.MoritzMCC.challengApi.AbstractChallenge;
import de.MoritzMCC.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class NoSneakChallenge extends AbstractChallenge {
    public NoSneakChallenge() {
        super("No Sneak Challenge",  Material.DIAMOND_BOOTS);
    }

    @Override
    public void onSneak(PlayerToggleSneakEvent event) {
        if (!isActivated())return;
        super.onSneak(event);
        Player player = event.getPlayer();
        failedChallenge(player);
    }
}
