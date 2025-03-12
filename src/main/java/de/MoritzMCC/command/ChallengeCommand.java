package de.MoritzMCC.command;

import de.MoritzMCC.inventory.Inventorys;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChallengeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

      if (!(commandSender instanceof Player)) {
          commandSender.sendMessage("You have to be a player to use this command!");
          return true;
      }
      Player player = (Player) commandSender;
      player.openInventory(Inventorys.homeInventory);

        return false;
    }
}
