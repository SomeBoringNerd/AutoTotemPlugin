package io.github.someboringnerd.commands;

import io.github.someboringnerd.MainClass;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class AutoTotemToggle implements CommandExecutor
{
    /*

    l'utilité de ce code est de désactiver l'autototem si un joueur n'en veut pas

    */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // on récupère le joueur qui execute la commande
        Player player = (Player) sender;

        MainClass.PlayerUseTotem.replace(player, !MainClass.PlayerUseTotem.get(player));
	
	if(args.lenght > 0){        
		if(args[0] == "soft" || args[0] == "hard"){
			MainClass.PlayerUseSoftMode.replace(player, (args[0] == "soft"));
		}
        }
        sender.sendMessage("§4[CPVPUTILITY]§2 : your Auto-Totem was set to §1" + (MainClass.PlayerUseTotem.get(player) ? "on" : "off") + " and in " + (MainClass.PlayerUseSoftMode.get(player) ? "soft" : "hard") + " mode");
        return true;
    }
}
