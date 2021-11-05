package com.io.farm.plugin;

import com.io.farm.command.FarmCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class FarmPlugin extends JavaPlugin {

    private void logMessage(String msg) {
        getLogger().info(msg);
    }

    private void setupCommand() {
        Objects.requireNonNull(getCommand("farm")).setExecutor(new FarmCommand());
    }

    private void setupEvent() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    //randomTickSpeed 를 0로 설정하셔야 합니다!
    @Override
    public void onEnable() {
        setupCommand();
        setupEvent();
        logMessage("플러그인 활성화");
    }

    @Override
    public void onDisable() {
        logMessage("플러그인 비활성화");
    }
}
