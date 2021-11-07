package com.io.farm.timer;

import com.io.farm.timer.ageable.*;
import com.io.farm.timer.ageable.stem.MelonTimer;
import com.io.farm.timer.ageable.stem.PumpkinTimer;
import com.io.farm.timer.sapling.*;
import com.io.farm.timer.sapling.mushroom.BrownMushroomTimer;
import com.io.farm.timer.sapling.mushroom.RedMushroomTimer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Timer {

    /*
    농작물 가격:
        밀 > 네더와트 > 수박 = 호박 > 사탕무 > 당근 = 감자 > 베리
     */

    public void setTimer(Block block, Player p) {
        switch (block.getType()) {
            case WHEAT: {
                WheatTimer wheatTimer = new WheatTimer(block, p);
                wheatTimer.addTimer(block.getLocation());
                break;
            }
            case POTATOES: {
                PotatoTimer potatoTimer = new PotatoTimer(block, p);
                potatoTimer.addTimer(block.getLocation());
                break;
            }
            case CARROTS: {
                CarrotTimer carrotTimer = new CarrotTimer(block, p);
                carrotTimer.addTimer(block.getLocation());
                break;
            }
            case BEETROOT: {
                BeetTimer beetTimer = new BeetTimer(block, p);
                beetTimer.addTimer(block.getLocation());
                break;
            }
            case NETHER_WART: {
                NetherWartTimer netherWartTimer = new NetherWartTimer(block, p);
                netherWartTimer.addTimer(block.getLocation());
                break;
            }
            case SWEET_BERRY_BUSH: {
                BerryTimer berryTimer = new BerryTimer(block, p);
                berryTimer.addTimer(block.getLocation());
                break;
            }
            case PUMPKIN_STEM: {
                PumpkinTimer pumpkinTimer = new PumpkinTimer(block, p);
                pumpkinTimer.addTimer(block.getLocation());
                break;
            }
            case MELON_STEM: {
                MelonTimer melonTimer = new MelonTimer(block, p);
                melonTimer.addTimer(block.getLocation());
                break;
            }
            case OAK_SAPLING: {
                OakTimer oakTimer = new OakTimer(block, p);
                oakTimer.addTimer(block.getLocation());
                break;
            }
            case ACACIA_SAPLING: {
                AcaciaTimer acaciaTimer = new AcaciaTimer(block, p);
                acaciaTimer.addTimer(block.getLocation());
                break;
            }
            case JUNGLE_SAPLING: {
                JungleTimer jungleTimer = new JungleTimer(block, p);
                jungleTimer.addTimer(block.getLocation());
                break;
            }
            case BIRCH_SAPLING: {
                BirchTimer birchTimer = new BirchTimer(block, p);
                birchTimer.addTimer(block.getLocation());
                break;
            }
            case DARK_OAK_SAPLING: {
                DarkOakTimer darkOakTimer = new DarkOakTimer(block, p);
                darkOakTimer.addTimer(block.getLocation());
                break;
            }
            case SPRUCE_SAPLING: {
                SpruceTimer spruceTimer = new SpruceTimer(block, p);
                spruceTimer.addTimer(block.getLocation());
                break;
            }
            case RED_MUSHROOM: {
                RedMushroomTimer redMushroomTimer = new RedMushroomTimer(block, p);
                redMushroomTimer.addTimer(block.getLocation());
                break;
            }
            case BROWN_MUSHROOM: {
                BrownMushroomTimer brownMushroomTimer = new BrownMushroomTimer(block, p);
                brownMushroomTimer.addTimer(block.getLocation());
                break;
            }
        }
    }
}
