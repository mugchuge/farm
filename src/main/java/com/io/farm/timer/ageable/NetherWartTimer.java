package com.io.farm.timer.ageable;

import com.io.farm.implement.CropStateImp;
import com.io.farm.state.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NetherWartTimer extends BukkitRunnable {

    public NetherWartTimer(Block block, Player p) {
        if (block.getBlockData() instanceof Ageable) {
            if (block.getType().equals(Material.NETHER_WART)) {
                CropState state = new CropStateImp();
                if (state.getState(block.getBlockData()) == 1) {
                    block2 = block;
                    p2 = p;
                }
            }
        }
    }

    Block block2;

    Player p2;

    int num = 29;

    int minute = 59;

    int stateNum = 2;

    ArmorStand armorStand;

    @Override
    public void run() {
        CropState state = new CropStateImp();
        if (minute == 0) {
            if ((num == 15) || (num == 0)) {
                BlockData data = block2.getBlockData();
                state.setState(data, stateNum);
                if (state.getState(data) == state.getMaxState(data)) {
                    Location loc = block2.getLocation();
                    World world = loc.getWorld();
                    world.spawnParticle(Particle.VILLAGER_HAPPY, loc, 3);
                    cancel();
                    armorStand.remove();
                } else {
                    stateNum++;
                }
            }
            num = num - 1;
            minute = 60;
        }
        minute = minute - 1;
    }

    public void addTimer(Location loc) {
        World world = loc.getWorld();
        for (Entity entity : world.getEntities()) {
            if (entity instanceof ArmorStand) {
                ArmorStand armorStand2 = (ArmorStand) entity;
                if (!armorStand2.getLocation().equals(block2.getLocation())) {
                    armorStand = world.spawn(loc, ArmorStand.class);
                    armorStand.setVisible(false);
                    armorStand.setCustomName(num + "분" + "" + minute + "초");
                    armorStand.setCustomNameVisible(true);
                    armorStand.setGravity(false);
                    armorStand.setPassenger(p2);
                }
            } else {
                armorStand = world.spawn(loc, ArmorStand.class);
                armorStand.setVisible(false);
                armorStand.setCustomName(num + "분" + "" + minute + "초");
                armorStand.setCustomNameVisible(true);
                armorStand.setGravity(false);
                armorStand.setPassenger(p2);
            }
        }
    }
}
