package com.io.farm.timer.ageable.stem;

import com.io.farm.implement.CropStateImp;
import com.io.farm.state.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MelonTimer extends BukkitRunnable {

    public MelonTimer(Block block, Player p) {
        if (block.getType().equals(Material.MELON_STEM)) {
            CropState state = new CropStateImp();
            if (state.getState(block.getBlockData()) == 1) {
                block2 = block;
                p2 = p;
            }
        }
    }

    Block block2;

    Player p2;

    int num = 9;

    int second = 59;

    int stateNum = 2;

    ArmorStand armorStand;

    @Override
    public void run() {
        CropState state = new CropStateImp();
        if (second == 0) {
            if (num <= 8) {
                if (num == 0) {
                    Location loc = block2.getLocation();
                    loc.getBlock().setType(Material.MELON);
                    World world = loc.getWorld();
                    world.spawnParticle(Particle.VILLAGER_HAPPY, loc, 1);
                } else {
                    BlockData data = block2.getBlockData();
                    state.setState(data, stateNum);
                    if (state.getState(data) == state.getMaxState(data)) {
                        Location loc = block2.getLocation();
                        World world = loc.getWorld();
                        world.spawnParticle(Particle.VILLAGER_ANGRY, loc, 1);
                    } else {
                        stateNum++;
                    }
                }
            }
            num = num - 1;
            second = 60;
        }
        second = second - 1;
    }

    public void addTimer(Location loc) {
        World world = loc.getWorld();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof ArmorStand) {
                ArmorStand armorStand2 = (ArmorStand) entity;
                if (!armorStand2.getLocation().equals(block2.getLocation())) {
                    armorStand = world.spawn(loc, ArmorStand.class);
                    armorStand.setVisible(false);
                    armorStand.setCustomName(num + "???" + "" + second + "???");
                    armorStand.setCustomNameVisible(true);
                    armorStand.setGravity(false);
                    armorStand.addPassenger(p2);
                }
            } else {
                armorStand = world.spawn(loc, ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setCustomName(num + "???" + "" + second + "???");
                armorStand.setCustomNameVisible(true);
                armorStand.setGravity(false);
                armorStand.addPassenger(p2);
            }
        }
    }

    public void removeTimer() {
        if (armorStand.getLocation().equals(block2.getLocation())) armorStand.remove();
        cancel();
    }
}
