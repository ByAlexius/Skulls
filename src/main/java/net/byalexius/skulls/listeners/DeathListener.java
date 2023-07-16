package net.byalexius.skulls.listeners;

import net.byalexius.skulls.Skulls;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class DeathListener implements Listener {

    FileConfiguration config = Skulls.getInstance().getCfg();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (!config.getBoolean("drop.player")) {
            return;
        }

        Player v = e.getEntity().getPlayer();

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);

        if (meta == null)
            return;

        meta.setOwningPlayer(v);
        skull.setItemMeta(meta);

        v.getWorld().dropItem(v.getLocation(), skull);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (!config.getBoolean("drop.entity")) {
            return;
        }

        Entity entity = e.getEntity();

        if (e.getEntity().getKiller() == null || e.getEntity().getKiller().getType() != EntityType.PLAYER) {
            return;
        }

        checkMaterial(entity);
    }

    // Checks if the Entity has a Skull/Head Material
    private void checkMaterial(Entity e) {
        EntityType type = e.getType();

        switch (type) {
            case ZOMBIE -> giveSkullToPlayer(e, Material.ZOMBIE_HEAD);
            case CREEPER -> giveSkullToPlayer(e, Material.CREEPER_HEAD);
            case ENDER_DRAGON -> giveSkullToPlayer(e, Material.DRAGON_HEAD);
            case PIGLIN -> giveSkullToPlayer(e, Material.PIGLIN_HEAD);
            case SKELETON -> giveSkullToPlayer(e, Material.SKELETON_SKULL);
        }
    }

    // Gives the Player the Skull/Head of the Entity
    private void giveSkullToPlayer(Entity e, Material m) {
        ItemStack skull = new ItemStack(m, 1);
        e.getWorld().dropItem(e.getLocation(), skull);
    }

}
