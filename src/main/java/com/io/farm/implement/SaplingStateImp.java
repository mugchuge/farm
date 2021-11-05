package com.io.farm.implement;

import com.io.farm.state.SaplingState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;

public class SaplingStateImp implements SaplingState {

    @Override
    public void setMaxState(BlockData data) {
        if (data instanceof Sapling) {
            Sapling sapling = (Sapling) data;
            sapling.setStage(sapling.getMaximumStage());
        }
    }
}
