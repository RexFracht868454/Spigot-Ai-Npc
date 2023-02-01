package rexfracht868454.ainpc.command;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import rexfracht868454.ainpc.utils.ConvoTrait;

public class SummonNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 3) {
                player.sendMessage(ChatColor.RED + command.getUsage());
                return true;
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
                player.sendMessage("Use one of them: zombie, skeleton, villager, creeper");
            }
        }
        return true;
    }
}
