package de.MoritzMCC.challengApi.challenges;

import de.MoritzMCC.challengApi.AbstractChallenge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class NoJumpChallenge extends AbstractChallenge {

    public NoJumpChallenge() {
        super("no jump challenge", Material.FEATHER);
    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        super.onMove(event);
        if (!isActivated())return;
        if (event.getFrom().getY() + 1 <= event.getTo().getY() ) {
            failedChallenge(event.getPlayer());
        }
    }
}
