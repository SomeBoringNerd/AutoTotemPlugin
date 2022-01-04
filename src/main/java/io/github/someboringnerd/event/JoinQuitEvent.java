package io.github.someboringnerd.event;

import io.github.someboringnerd.MainClass;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitEvent implements Listener
{
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event)
    {
        MainClass.PlayerUseTotem.put(event.getPlayer(), true);
        MainClass.PlayerUseSoftMode.put(event.getPlayer(), false);
        
    }

    @EventHandler
    public void PlayerLeaveEvent(PlayerQuitEvent event)
    {
        MainClass.PlayerUseTotem.remove(event.getPlayer());
        MainClass.PlayerUseSoftMode.remove(event.getPlayer());
    }
}
