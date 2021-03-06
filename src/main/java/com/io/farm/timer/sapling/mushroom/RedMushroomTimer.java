package com.io.farm.timer.sapling.mushroom;

import com.io.farm.implement.SaplingStateImp;
import com.io.farm.state.SaplingState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RedMushroomTimer extends BukkitRunnable {

    public RedMushroomTimer(Block block, Player p) {
        if (block.getBlockData() instanceof Sapling) {
            if (block.getType().equals(Material.RED_MUSHROOM)) {
                SaplingState state = new SaplingStateImp();
                if (state.getStage(block.getBlockData()) == 1) {
                    block2 = block;
                    p2 = p;
                }
            }
        }
    }

    Block block2;

    Player p2;

    int num = 2;

    int minute = 59;

    int second = 59;

    ArmorStand armorStand;

    @Override
    public void run() {
        SaplingState state = new SaplingStateImp();
        if (second == 0) {
            if (minute == 0) {
                if (num == 0) {
                    state.setMaxStage(block2.getBlockData());
                    World world = block2.getLocation().getWorld();
                    world.spawnParticle(Particle.VILLAGER_HAPPY, block2.getLocation(), 1);
                    armorStand.remove();
                    cancel();
                }
                num = num - 1;
                minute = 60;
            }
            minute = minute - 1;
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
                    armorStand.setCustomName(num + "??????" + minute + "???" + "" + second + "???");
                    armorStand.setCustomNameVisible(true);
                    armorStand.setGravity(false);
                    armorStand.addPassenger(p2);
                }
            } else {
                armorStand = world.spawn(loc, ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setCustomName(num + "??????" + minute + "???" + "" + second + "???");
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
