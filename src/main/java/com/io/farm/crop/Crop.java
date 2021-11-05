package com.io.farm.crop;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Crop {

    Set<Material> crops = new HashSet<>();

    public Crop(Material... materials) {
        crops.addAll(Arrays.asList(materials));
    }

    public boolean isCrop(Material material) {
        return crops.contains(material);
    }

    public Set<Material> getCrops() {
        return crops;
    }
}
