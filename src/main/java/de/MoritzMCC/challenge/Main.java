package de.MoritzMCC.challenge;

import de.MoritzMCC.challengApi.AbstractChallenge;
import de.MoritzMCC.challengApi.ChallengeManager;
import de.MoritzMCC.challengApi.challenges.NoSneakChallenge;
import de.MoritzMCC.command.ChallengeCommand;
import de.MoritzMCC.command.TimerCommand;
import de.MoritzMCC.inventory.InventoryHandler;
import de.MoritzMCC.listener.TimerPausedListener;
import de.MoritzMCC.timer.Timer;
import de.MoritzMCC.utils.Config;
import de.MoritzMCC.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private Config timerConfig;
    private Timer timer;

    @Override
    public void onLoad() {
        instance = this;
        timerConfig = new Config();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        timer = new Timer();
        enableCommands();
        enableListeners();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        timer.save();
        timerConfig.save();
    }

    public static Main getInstance() {
        return instance;
    }

    private void enableCommands(){
        getCommand("challenge").setExecutor(new ChallengeCommand());
        getCommand("timer").setExecutor(new TimerCommand());
    }

    private void enableListeners(){
        getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
        getServer().getPluginManager().registerEvents(new TimerPausedListener(), this);
    }



    public Config getTimerConfig() {
        return timerConfig;
    }

    public Timer getTimer() {
        return timer;
    }
}
