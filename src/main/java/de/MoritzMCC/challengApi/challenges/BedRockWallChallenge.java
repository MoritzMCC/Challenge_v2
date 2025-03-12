package de.MoritzMCC.challengApi.challenges;

import de.MoritzMCC.challengApi.AbstractChallenge;
import de.MoritzMCC.challenge.Main;
import de.MoritzMCC.timer.Timer;
import org.bukkit.Location;
import org.bukkit.Material;
import de.MoritzMCC.utils.combatUtils.SoupHealingUtils;
import de.MoritzMCC.utils.placeBlocks.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BedRockWallChallenge extends AbstractChallenge implements Runnable {

Map<Integer, Location> locations = new HashMap<>();

    public BedRockWallChallenge() {
        super("Bedrock wall challenge", Material.BEDROCK);

    }

    @Override
    public void onMove(PlayerMoveEvent event) {
        super.onMove(event);
        if (!isActivated())return;
        Location to = event.getTo();
       locations.put(Main.getInstance().getTimer().getTime() + 1, to);



    }

    @Override
    public void run() {
        if (isActivated()) {
            Timer timer = Main.getInstance().getTimer();
            if(timer.isRunning()){
                for (int time: locations.keySet()) {
                    if(time <= timer.getTime()){
                        Location location = locations.get(time);
                        PlaceStructure.placeColumn(location,Material.BEDROCK);
                        locations.remove(time);
                    }
                }
            }
        }
    }
}
