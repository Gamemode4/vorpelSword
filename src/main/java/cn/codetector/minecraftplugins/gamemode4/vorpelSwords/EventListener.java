package cn.codetector.minecraftplugins.gamemode4.vorpelSwords;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onEntityDeath(EntityDeathEvent event) {
        EntityDamageEvent.DamageCause cause = event.getEntity().getLastDamageCause().getCause();
        if (event.getEntity().getKiller() instanceof Player){
            Player p = (Player)event.getEntity().getKiller();
            ItemStack hand = p.getItemInHand();
            if (hand != null && hand.getEnchantments().containsKey(Enchantment.LOOT_BONUS_MOBS) && hand.getEnchantments().get(Enchantment.LOOT_BONUS_MOBS).equals(3) && hand.getItemMeta().getDisplayName()!=null && hand.getItemMeta().getDisplayName().replaceAll(" ","").equalsIgnoreCase("vorpelsword")) {
                EntityType t = event.getEntity().getType();
                if (cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK && event.getEntity().getKiller() != null) {
                    Location loc = event.getEntity().getLocation();

                    if (t == EntityType.ZOMBIE) {
                        dropSkull(loc, 2);
                    } else if (t == EntityType.SKELETON) {
                        dropSkull(loc, 0);
                    } else if (t == EntityType.CREEPER) {
                        dropSkull(loc, 4);
                    } else if(t == EntityType.PLAYER){
                        dropSkull(loc,3);
                    }
                }
            }
        }

    }

    private void dropSkull(Location loc, int type) {
        loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.SKULL_ITEM, 1, (short) type));
    }
}
