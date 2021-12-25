package io.github.someboringnerd;

import io.github.someboringnerd.commands.AutoTotemToggle;
import io.github.someboringnerd.commands.ColorHelp;
import io.github.someboringnerd.event.ChatListener;
import io.github.someboringnerd.event.JoinQuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class MainClass extends JavaPlugin {

    public static Map<Player, Boolean> PlayerUseTotem = new HashMap<Player, Boolean>();

    @Override
    public void onEnable()
    {
        // register l'event du chat pour les couleurs
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new JoinQuitEvent(), this);

        System.out.println("---------");
        System.out.println("CPVPutility by SomeBoringNerd");
        System.out.println("Thank for using my plugin, check my other works on here :");
        System.out.println("https://github.com/SomeBoringNerd");
        System.out.println("---------");

        // register les commandes
        getCommand("colorhelp").setExecutor(new ColorHelp());
        getCommand("autototemtoggle").setExecutor(new AutoTotemToggle());

        // Autototem
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run() {
                // pour chaque joueur en ligne
                for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                    // si l'item en main n'est pas un totem
                    if(player.getInventory().getItemInOffHand().getType() != Material.TOTEM_OF_UNDYING && PlayerUseTotem.get(player))
                    {
                        // regarde chaque slot de l'inventaire
                        for (ItemStack item : player.getInventory().getContents())
                        {
                            // si l'item n'est pas nul
                            if (item != null) {
                                // et que c'est un totem
                                if(item.getType() == Material.TOTEM_OF_UNDYING && player.getInventory().getItemInOffHand().getType() != Material.TOTEM_OF_UNDYING)
                                {
                                    // on met une copie de l'item dans la offhand
                                    player.getInventory().setItemInOffHand(item);
                                    // on met l'item a -1
                                    item.setAmount(-1);
                                    // force update l'inventaire
                                    player.updateInventory();
                                }
                            }
                        }
                    }
                }
            }
        }, 5L, 1L);
    }
}
