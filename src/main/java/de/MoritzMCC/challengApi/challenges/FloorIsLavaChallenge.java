package de.MoritzMCC.challengApi.challenges;

import de.MoritzMCC.challengApi.AbstractChallenge;
import de.MoritzMCC.challenge.Main;
import de.MoritzMCC.timer.Timer;
import de.MoritzMCC.utils.placeBlocks.PlaceStructure;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class FloorIsLavaChallenge extends AbstractChallenge implements Runnable {
    public FloorIsLavaChallenge() {
        super("The Floor is lava", Material.LAVA);
    }

    Map<Integer,Location>locations = new HashMap<>();

    @Override
    public void onMove(PlayerMoveEvent event) {
        super.onMove(event);
        if (!isActivated())return;
        Player player = event.getPlayer();
        Location location = event.getFrom();
        location.setY(location.getY() - 1);
        locations.put(Main.getInstance().getTimer().getTime() + 1, location);

    }
    @Override
    public void run() {
        if (isActivated()) {
            Timer timer = Main.getInstance().getTimer();
            if(timer.isRunning()){
                for (int time: locations.keySet()) {
                    if(time <= timer.getTime()){
                        Location location = locations.get(time);
                        PlaceStructure.placeBlock(location, Material.LAVA);
                        locations.remove(time);
                    }
                }
            }
        }
    }
}
