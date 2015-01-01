package com.lemonpup.easyxp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;

public class EasyXP extends JavaPlugin implements CommandExecutor, Listener {

    @Override
    public void onEnable() {
        getLogger().info("EasyXP enabled.");
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("exphelp").setExecutor(this);
        getCommand("expversion").setExecutor(this);
    }
    @Override
    public void onDisable() {
        getLogger().info("EasyXP disabled.");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("exphelp")) {
            sender.sendMessage(ChatColor.AQUA + "To use EasyXP's bottling capability, make sure you have at least 1 experience level and have equipped an empty bottle. Then, while holding the bottle, right click. Your bottle will be filled with experience.");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("expversion")) {
            sender.sendMessage(ChatColor.AQUA + "EasyXP v.0.0.1\n=============\nDeveloped by lemonpup (lemonpup@ghostrealms.net)\nSponsored by Ghost Realms (ghostrealms.net)");
        }
        return false;
    }
    @SuppressWarnings("unused")
    @EventHandler(priority=EventPriority.HIGH)
    public void swap(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        int xp = p.getExpToLevel();
        if (LemonCore.hasEnoughXP(p) && LemonCore.isHoldingEmptyBottle(p)) {
            p.getInventory().remove(p.getItemInHand());
            p.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 1));
            xp -= 1;
            p.sendMessage(ChatColor.GREEN + "[EasyXP]: Success!");
            getLogger().info("[EasyXP]: Given player" + p.getName() + "1 Experience Bottle.");
        } else if (!LemonCore.hasEnoughXP(p) && LemonCore.isHoldingEmptyBottle(p))
            p.sendMessage(ChatColor.RED + "[EasyXP]: You don't have enough Experience Points!");
    }
}