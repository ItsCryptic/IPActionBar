package com.yovez.ipactionbar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class IPActionBar extends JavaPlugin {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		new BukkitRunnable() {

			@Override
			public void run() {
				if (Bukkit.getOnlinePlayers().size() > 0) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						try {
							sendMessage(p);
						} catch (ClassNotFoundException e) {
						}
					}
				}
			}

		}.runTaskTimerAsynchronously(this, 0L, 20L);
	}

	private void sendMessage(Player p) throws ClassNotFoundException {
		if (Class.forName("net.md_5.bungee.api.ChatMessageType") != null
				&& Class.forName("net.md_5.bungee.api.chat.TextComponent") != null)
			p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
					new TextComponent(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message"))));
		else
			ActionBarUtil.sendActionBarMessage(p,
					ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));
	}

}
