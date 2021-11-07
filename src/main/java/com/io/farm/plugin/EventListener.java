package com.io.farm.plugin;

import com.io.farm.crop.Crop;
import com.io.farm.implement.CropImp;
import com.io.farm.implement.CropStateImp;
import com.io.farm.state.CropState;
import com.io.farm.timer.Timer;
import com.io.farm.timer.ageable.*;
import com.io.farm.timer.ageable.stem.MelonTimer;
import com.io.farm.timer.ageable.stem.PumpkinTimer;
import com.io.farm.timer.sapling.*;
import com.io.farm.timer.sapling.mushroom.BrownMushroomTimer;
import com.io.farm.timer.sapling.mushroom.RedMushroomTimer;
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

public class EventListener implements Listener {

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
        if (e.getBlock().getType().equals(Material.FARMLAND)) e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        BlockData data = b.getBlockData();
        FarmPlugin farmPlugin = new FarmPlugin();
        switch (b.getType()) {
            case WHEAT: {
                farmPlugin.setScheduler(new WheatTimer(b, p));
                break;
            }
            case POTATOES: {
                farmPlugin.setScheduler(new PotatoTimer(b, p));
                break;
            }
            case CARROTS: {
                farmPlugin.setScheduler(new CarrotTimer(b, p));
                break;
            }
            case BEETROOT: {
                farmPlugin.setScheduler(new BeetTimer(b, p));
                break;
            }
            case NETHER_WART: {
                farmPlugin.setScheduler(new NetherWartTimer(b, p));
                break;
            }
            case SWEET_BERRY_BUSH: {
                farmPlugin.setScheduler(new BerryTimer(b, p));
                break;
            }
            case PUMPKIN_STEM: {
                farmPlugin.setScheduler(new PumpkinTimer(b, p));
                break;
            }
            case MELON_STEM: {
                farmPlugin.setScheduler(new MelonTimer(b, p));
                break;
            }
            case OAK_SAPLING: {
                farmPlugin.setScheduler(new OakTimer(b, p));
                break;
            }
            case ACACIA_SAPLING: {
                farmPlugin.setScheduler(new AcaciaTimer(b, p));
                break;
            }
            case JUNGLE_SAPLING: {
                farmPlugin.setScheduler(new JungleTimer(b, p));
                break;
            }
            case BIRCH_SAPLING: {
                farmPlugin.setScheduler(new BirchTimer(b, p));
                break;
            }
            case DARK_OAK_SAPLING: {
                farmPlugin.setScheduler(new DarkOakTimer(b, p));
                break;
            }
            case SPRUCE_SAPLING: {
                farmPlugin.setScheduler(new SpruceTimer(b, p));
                break;
            }
            case RED_MUSHROOM: {
                farmPlugin.setScheduler(new RedMushroomTimer(b, p));
                break;
            }
            case BROWN_MUSHROOM: {
                farmPlugin.setScheduler(new BrownMushroomTimer(b, p));
                break;
            }
            case FARMLAND: {
                Farmland farmland = (Farmland) data;
                farmland.setMoisture(farmland.getMaximumMoisture());
                break;
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
        Crop crop = new CropImp(Material.WHEAT_SEEDS, Material.POTATOES, Material.CARROTS, Material.BEETROOT_SEEDS, Material.NETHER_WART, Material.SWEET_BERRIES,
        Material.MELON_SEEDS, Material.PUMPKIN_SEEDS,
        Material.OAK_SAPLING, Material.ACACIA_SAPLING, Material.JUNGLE_SAPLING, Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING, Material.SPRUCE_SAPLING,
        Material.BROWN_MUSHROOM, Material.RED_MUSHROOM);
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
