package com.io.farm.timer;

import com.io.farm.timer.ageable.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Timer {

    //묘목 추가 예정
    
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
        }
    }
}
