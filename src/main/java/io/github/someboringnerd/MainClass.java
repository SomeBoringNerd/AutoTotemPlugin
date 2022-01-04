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
    public static Map<Player, Boolean> PlayerUseSoftMode = new HashMap<Player, Boolean>();

    //@TODO : implémenter un mode "soft" et un mode "hard"
    //        le mode soft ne mettra un item en offhand uniquement si la offhand est vide
    //        le mode hard tentera de placer un item en offhand quoi qu'il arrive si l'item en main n'est pas un totem
    
    
    public Boolean determineUserMode(Player player)
    {
    	if(PlayerUseSoftMode.get(player))
    	{
    	     return (player.getInventory().getItemInOffHand().getType() == null);
    	}else{
    	     return (player.getInventory().getItemInOffHand().getType() != Material.TOTEM_OF_UNDYING);
    	}
    }
    
    
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
                    
                    if(determineUserMode(player) && PlayerUseTotem.get(player))
                    {
                        // regarde chaque slot de l'inventaire
                        for (ItemStack item : player.getInventory().getContents())
                        {
                            // si l'item n'est pas nul
                            if (item != null) {
                                // et que c'est un totem
                                if(item.getType() == Material.TOTEM_OF_UNDYING)
                                {
                                    ItemStack tempitem = player.getInventory().getItemInOffHand();
                                
                                    // on met une copie de l'item dans la offhand
                                    player.getInventory().setItemInOffHand(item);
                                    // inverse l'item de la offhand par le premier totem placé en offhand
                                    item = tempitem;
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
