package com.io.farm.implement;

import com.io.farm.crop.Crop;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Set;

public class CropImp implements Crop {

    public CropImp(Material... materials) {
        crops.addAll(Arrays.asList(materials));
    }

    @Override
    public boolean isCrop(Material material) {
        return crops.contains(material);
    }

    @Override
    public Set<Material> getCrops() {
        return crops;
    }
}
