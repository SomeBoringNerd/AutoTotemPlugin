package io.github.someboringnerd.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ColorHelp implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        sender.sendMessage("§l§n[CPVPUTILITY COLOR CODE]");
        sender.sendMessage("§o§nuse those character at the begining of your message to change it's color");
        sender.sendMessage("> : §aput your text in green");
        sender.sendMessage("% : §5put your text in purple");
        sender.sendMessage("? : §1put your text in blue");
        sender.sendMessage("$ : §4put your text in red");
        sender.sendMessage("& : §eput your text in yellow");
        sender.sendMessage("# : §6put your text in orange");
        sender.sendMessage("! : §dput your text in pink");
        sender.sendMessage("@ : §6put your text in gold");
        sender.sendMessage("§oPlugin made by SomeBoringNerd for TaxMachine");
        return true;
    }
}
