package com.io.farm.state;

import org.bukkit.block.data.BlockData;

public interface SaplingState {

    void setMaxStage(BlockData data);

    int getStage(BlockData data);
}
