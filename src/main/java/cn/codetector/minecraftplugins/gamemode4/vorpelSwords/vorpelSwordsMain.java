package cn.codetector.minecraftplugins.gamemode4.vorpelSwords;


import org.bukkit.plugin.java.JavaPlugin;

public class vorpelSwordsMain extends JavaPlugin{
    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getPluginManager().registerEvents(new EventListener(),this);
    }
}
