package com.io.farm.timer.ageable.stem;

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

    int minute = 59;

    int stateNum = 2;

    ArmorStand armorStand;

    @Override
    public void run() {
        CropState state = new CropStateImp();
        if (minute == 0) {
            if (num <= 8) {
                if (num == 0) {
                    Location loc = block2.getLocation();
                    loc.getBlock().setType(Material.MELON);
                    World world = loc.getWorld();
                    world.spawnParticle(Particle.VILLAGER_HAPPY, loc, 3);
                } else {
                    BlockData data = block2.getBlockData();
                    state.setState(data, stateNum);
                    if (state.getState(data) == state.getMaxState(data)) {
                        Location loc = block2.getLocation();
                        World world = loc.getWorld();
                        world.spawnParticle(Particle.VILLAGER_ANGRY, loc, 3);
                    } else {
                        stateNum++;
                    }
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
