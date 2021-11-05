package com.io.farm.state;

import org.bukkit.block.data.BlockData;

public interface CropState {

    void setState(BlockData data, int num);

    int getState(BlockData data);

    int getMaxState(BlockData data);
}
