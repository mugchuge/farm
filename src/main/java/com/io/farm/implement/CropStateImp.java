package com.io.farm.implement;

import com.io.farm.state.CropState;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

public class CropStateImp implements CropState {

    @Override
    public void setState(BlockData data, int num) {
        if (data instanceof Ageable) {
            Ageable ageable = (Ageable) data;
            ageable.setAge(num);
        }
    }

    @Override
    public int getState(BlockData data) {
        if (data instanceof Ageable) {
            Ageable ageable = (Ageable) data;
            return ageable.getAge();
        } else {
            return 0;
        }
    }

    @Override
    public int getMaxState(BlockData data) {
        if (data instanceof Ageable) {
            Ageable ageable = (Ageable) data;
            return ageable.getMaximumAge();
        } else {
            return 0;
        }
    }
}
