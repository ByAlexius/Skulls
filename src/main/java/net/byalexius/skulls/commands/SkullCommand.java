package net.byalexius.skulls.commands;

import net.byalexius.skulls.Skulls;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a Player to execute this command!");
            return false;
        }

        if(!Skulls.getInstance().getCfg().getBoolean("drop.command")) {
            sender.sendMessage(ChatColor.RED + "The Skull Command is disabled!");
            return false;
        }

        if (!sender.hasPermission("skulls.skull") && sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You do not have enough permissions to execute this command!");
            return false;
        }

        if (args[0] == null || args[0].trim().equals("")) {
            sender.sendMessage(ChatColor.RED + "Please specify a Player!");
            return false;
        }

        Player p = Bukkit.getPlayer(args[0].trim());

        if (p == null) {
            sender.sendMessage(ChatColor.RED + "Please specify a valid Player!");
            return false;
        }

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);

        meta.setOwningPlayer(p);
        skull.setItemMeta(meta);

        ((Player) sender).getInventory().addItem(skull);
        ((Player) sender).updateInventory();

        return true;
    }
}
