package me.confuser.banmanager.commands;

import me.confuser.banmanager.BanManager;
import me.confuser.bukkitutil.Message;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class StopTheSpamCommand extends AutoCompleteNameTabCommand<BanManager> {

  public StopTheSpamCommand() {
    super("stopthespam");
  }

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if (strings.length != 1) {
      return false;
    }

    OfflinePlayer player = Bukkit.getOfflinePlayer(strings[0]);
    if (player == null || !player.hasPlayedBefore()) {
      if (plugin.getIgnoredIpAddressJoins().contains(strings[0])) {
        plugin.getIgnoredIpAddressJoins().remove(strings[0]);
        commandSender.sendMessage(Message.getString("stopthespam.removed"));
      } else {
        plugin.getIgnoredIpAddressJoins().add(strings[0]);
        commandSender.sendMessage(Message.getString("stopthespam.success"));
      }
      return true;
    } else {
      if (plugin.getIgnoredPlayerJoins().contains(player.getUniqueId())) {
        plugin.getIgnoredPlayerJoins().remove(player.getUniqueId());
        commandSender.sendMessage(Message.getString("stopthespam.removed"));
      } else {
        plugin.getIgnoredPlayerJoins().add(player.getUniqueId());
        commandSender.sendMessage(Message.getString("stopthespam.success"));
      }
      return true;
    }
  }
}
