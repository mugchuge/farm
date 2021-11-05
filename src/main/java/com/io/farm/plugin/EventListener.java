package com.io.farm.plugin;

import com.io.farm.crop.Crop;
import com.io.farm.implement.CropStateImp;
import com.io.farm.state.CropState;
import com.io.farm.timer.Timer;
import com.io.farm.timer.ageable.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener extends JavaPlugin implements Listener {

    @EventHandler
    public void onGrowth(BlockGrowEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onStructureGrowth(StructureGrowEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFade(BlockFadeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        BlockData data = b.getBlockData();
        switch (b.getType()) {
            case WHEAT: {
                getServer().getScheduler().runTaskTimer(this, new WheatTimer(b, p), 0L, 20L);
            }
            case POTATOES: {
                getServer().getScheduler().runTaskTimer(this, new PotatoTimer(b, p), 0L, 20L);
            }
            case CARROTS: {
                getServer().getScheduler().runTaskTimer(this, new CarrotTimer(b, p), 0L, 20L);
            }
            case BEETROOT: {
                getServer().getScheduler().runTaskTimer(this, new BeetTimer(b, p), 0L, 20L);
            }
            case NETHER_WART: {
                getServer().getScheduler().runTaskTimer(this, new NetherWartTimer(b, p), 0L, 20L);
            }
            case SWEET_BERRY_BUSH: {
                getServer().getScheduler().runTaskTimer(this, new BerryTimer(b, p), 0L, 20L);
            }
            case FARMLAND: {
                Farmland farmland = (Farmland) data;
                farmland.setMoisture(farmland.getMaximumMoisture());
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        if (b.getType().equals(Material.FARMLAND)) {
            e.setCancelled(true);
            Location loc = b.getLocation();
            loc.getBlock().setType(Material.AIR);
            World world = loc.getWorld();
            world.playEffect(loc, Effect.STEP_SOUND, b.getBlockData().getMaterial());
            world.dropItemNaturally(loc, new ItemStack(Material.FARMLAND));
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Crop crop = new Crop(Material.WHEAT_SEEDS, Material.POTATOES, Material.CARROTS, Material.BEETROOT_SEEDS, Material.NETHER_WART, Material.SWEET_BERRIES,
        Material.MELON_SEEDS, Material.PUMPKIN_SEEDS,
        Material.OAK_SAPLING, Material.ACACIA_SAPLING, Material.JUNGLE_SAPLING, Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING, Material.SPRUCE_SAPLING);
        ItemStack item = e.getItem();
        Block block = e.getClickedBlock();
        assert item != null;
        assert block != null;
        Action action = e.getAction();
        Player p = e.getPlayer();
        CropState cropState = new CropStateImp();
        if (action == Action.RIGHT_CLICK_BLOCK) {
            if (crop.isCrop(item.getType())) {
                if (!item.getItemMeta().hasEnchant(Enchantment.DURABILITY) && !item.getItemMeta().hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                    e.setCancelled(true);
                }
            } else {
                if (crop.isCrop(block.getType())) {
                    if (block.getType().equals(Material.SWEET_BERRY_BUSH)) {
                        if (cropState.getState(block.getBlockData()) >= 2) {
                            BerryTimer berryTimer = new BerryTimer(block, p);
                            berryTimer.initializationTimer();
                        }
                    }
                    if (cropState.getState(block.getBlockData()) < cropState.getMaxState(block.getBlockData())) {
                        if (p.isSneaking()) {
                            Timer timer = new Timer();
                            timer.setTimer(block, p);
                        }
                    }
                } if (item.getType().equals(Material.BONE_MEAL)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
