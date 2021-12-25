package io.github.someboringnerd.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener
{
    @EventHandler
    public void ChatEvent(AsyncPlayerChatEvent event)
    {
        String tmp = event.getMessage();

        // ça supporte pas les switchs ptn
        if(tmp.startsWith(">"))
        {
            tmp = "§a" + tmp;
        }else if(tmp.startsWith("%"))
        {
            tmp = "§5" + tmp;
        }else if(tmp.startsWith("?"))
        {
            tmp = "§1" + tmp;
        }else if(tmp.startsWith("$"))
        {
            tmp = "§4" + tmp;
        }else if(tmp.startsWith("#"))
        {
            tmp = "§6" + tmp;
        }else if(tmp.startsWith("!"))
        {
            tmp = "§d" + tmp;
        }else if(tmp.startsWith("@"))
        {
            tmp = "§6" + tmp;
        }else if(tmp.startsWith("&"))
        {
            tmp = "§e" + tmp;
        }

        event.setMessage(tmp);
    }
}
