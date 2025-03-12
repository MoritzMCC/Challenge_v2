package de.MoritzMCC.challengApi.challenges;

import de.MoritzMCC.challengApi.AbstractChallenge;
import de.MoritzMCC.challenge.Main;
import de.MoritzMCC.utils.TeleportUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;


public class RandomTeleportChallenge extends AbstractChallenge implements Runnable {
    Random rand;
    public RandomTeleportChallenge() {
        super("Random Teleport", Material.ENDER_PEARL);
        rand = new Random();
    }

    private long lastTeleportTime;
    private int teleportDelay;

    @Override
    public void run() {
        if(Main.getInstance().getTimer().getTime() >=  lastTeleportTime + teleportDelay) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                TeleportUtil.teleportInRadius(player, 300);
            });
            
            lastTeleportTime = Main.getInstance().getTimer().getTime();
            teleportDelay = rand.nextInt(30 , 120);
        }
    }
}
