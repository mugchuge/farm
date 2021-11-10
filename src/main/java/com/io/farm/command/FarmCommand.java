package com.io.farm.command;

import com.io.farm.crop.Crop;
import com.io.farm.implement.CropImp;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class FarmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage("farm command");
        } else {
            switch (args[0]) {
                case "register": {
                    register(p, p.getItemInHand());
                    break;
                }
                case "info": {
                    Crop crop = new CropImp(Material.WHEAT_SEEDS, Material.POTATOES, Material.CARROTS, Material.BEETROOT_SEEDS, Material.NETHER_WART,
                            Material.MELON_SEEDS, Material.PUMPKIN_SEEDS,
                            Material.OAK_SAPLING, Material.ACACIA_SAPLING, Material.JUNGLE_SAPLING, Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING, Material.SPRUCE_SAPLING,
                            Material.BROWN_MUSHROOM, Material.RED_MUSHROOM);
                    p.sendMessage(String.valueOf(crop.getCrops()));
                }
            }
        }
        return false;
    }

    private void register(Player sender, ItemStack itemStack) {
        Crop crop = new CropImp(Material.WHEAT_SEEDS, Material.POTATOES, Material.CARROTS, Material.BEETROOT_SEEDS, Material.NETHER_WART,
                Material.MELON_SEEDS, Material.PUMPKIN_SEEDS,
                Material.OAK_SAPLING, Material.ACACIA_SAPLING, Material.JUNGLE_SAPLING, Material.BIRCH_SAPLING, Material.DARK_OAK_SAPLING, Material.SPRUCE_SAPLING);
        if (crop.isCrop(itemStack.getType())) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta.hasEnchant(Enchantment.DURABILITY)) {
                sender.sendMessage("이미 등록되어 있는 씨앗이나 묘목입니다.");
            } else {
                itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                sender.sendMessage("씨앗을 등록시켰습니다.");
            }
        } else {
            sender.sendMessage("작물을 들고 다시 명령어를 실행해주세요.");
        }
    }
}
