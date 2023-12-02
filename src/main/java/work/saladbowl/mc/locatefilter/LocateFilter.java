package work.saladbowl.mc.locatefilter;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

public class LocateFilter extends JavaPlugin implements Listener {
    public static LocateFilter plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        // イベントリスナーを登録
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("LocateFilter plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LocateFilter plugin has been disabled!");
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) throws Exception {
        String ipAddress = e.getPlayer().getAddress().getAddress().getHostAddress();

        Bukkit.getLogger().info("Player " + e.getPlayer().getName() + " has IP address: " + ipAddress);

        if (!IPData.getCountry(ipAddress)) e.getPlayer().kickPlayer(getConfig().getString("kickMessage"));
    }
}
