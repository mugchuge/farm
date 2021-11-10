package com.io.farm.implement;

import com.io.farm.state.SaplingState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Sapling;

public class SaplingStateImp implements SaplingState {

    @Override
    public void setMaxStage(BlockData data) {
        if (data instanceof Sapling) {
            Sapling sapling = (Sapling) data;
            sapling.setStage(sapling.getMaximumStage());
        }
    }

    @Override
    public int getStage(BlockData data) {
        if (data instanceof Sapling) {
            Sapling sapling = (Sapling) data;
            return sapling.getStage();
        } else {
            return 0;
        }
    }
}
