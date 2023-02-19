package rexfracht868454.ainpc.command;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import rexfracht868454.ainpc.Main;
import rexfracht868454.ainpc.utils.ConvoTrait;

import java.util.ArrayList;

public class SummonNPCCommand implements CommandExecutor {

    private Main plugin;

    public SummonNPCCommand(Main plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1) {
                String action = args[1];
                if (action.equalsIgnoreCase("help")) {
                    player.sendMessage("");
                } else if (action.equalsIgnoreCase("list")) {
                    player.sendMessage(ChatColor.GREEN + "List of all ..." + ChatColor.YELLOW + "\n-> zombie,\n-> skeleton,\n-> villager,\n-> creeper");
                }
            }

            String type = args[0];
            String name = args[1];
            String role = args[2];

            if (type.equalsIgnoreCase("villager")) {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.VILLAGER, name);
                npc.addTrait(new ConvoTrait(role));
                npc.spawn(player.getLocation());
                player.sendMessage(ChatColor.GREEN + "NPC created a " + ChatColor.YELLOW +  type  + ChatColor.GREEN + " npc with the name " + ChatColor.YELLOW + name + ChatColor.GREEN + " and role " + ChatColor.YELLOW + role);
                player.sendMessage( ChatColor.GREEN + "They will now have conversations with you as a " + ChatColor.YELLOW + role);

            }else if (type.equalsIgnoreCase("zombie")) {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.ZOMBIE, name);
                npc.addTrait(new ConvoTrait(role));
                npc.spawn(player.getLocation());
                player.sendMessage(ChatColor.GREEN + "NPC created a " + ChatColor.YELLOW +  type  + ChatColor.GREEN + " npc with the name " + ChatColor.YELLOW + name + ChatColor.GREEN + " and role " + ChatColor.YELLOW + role);
                player.sendMessage( ChatColor.GREEN + "They will now have conversations with you as a " + ChatColor.YELLOW + role);

            } else if (type.equalsIgnoreCase("skeleton")) {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.SKELETON, name);
                npc.addTrait(new ConvoTrait(role));
                npc.spawn(player.getLocation());
                player.sendMessage(ChatColor.GREEN + "NPC created a " + ChatColor.YELLOW +  type  + ChatColor.GREEN + " npc with the name " + ChatColor.YELLOW + name + ChatColor.GREEN + " and role " + ChatColor.YELLOW + role);
                player.sendMessage( ChatColor.GREEN + "They will now have conversations with you as a " + ChatColor.YELLOW + role);

            } else if (type.equalsIgnoreCase("creeper")) {
                NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.CREEPER, name);
                npc.addTrait(new ConvoTrait(role));
                npc.spawn(player.getLocation());
                player.sendMessage(ChatColor.GREEN + "NPC created a " + ChatColor.YELLOW +  type  + ChatColor.GREEN + " npc with the name " + ChatColor.YELLOW + name + ChatColor.GREEN + " and role " + ChatColor.YELLOW + role);
                player.sendMessage( ChatColor.GREEN + "They will now have conversations with you as a " + ChatColor.YELLOW + role);

            }  else {
                player.sendMessage(ChatColor.RED + "");
                player.sendMessage(ChatColor.RED + command.getUsage());
                player.sendMessage("Use one of them: zombie, skeleton, villager, creeper");
            }
        }
        return true;
    }
}
