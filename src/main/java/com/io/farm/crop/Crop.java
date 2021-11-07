package com.io.farm.crop;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public interface Crop {

    Set<Material> crops = new HashSet<>();

    boolean isCrop(Material material);

    Set<Material> getCrops();
}
