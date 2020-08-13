package variousachievementsplus.procedure;

import variousachievementsplus.ElementsVariousAchievements;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSender;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Iterator;

@ElementsVariousAchievements.ModElement.Tag
public class ProcedureLogicNowImGod extends ElementsVariousAchievements.ModElement {
	public ProcedureLogicNowImGod(ElementsVariousAchievements instance) {
		super(instance, 19);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure LogicNowImGod!");
			return;
		}
		if (dependencies.get("command") == null) {
			System.err.println("Failed to load dependency command for procedure LogicNowImGod!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		String command = (String) dependencies.get("command");
		if (((!((entity instanceof EntityPlayer) ? ((EntityPlayer) entity).capabilities.isCreativeMode : false))
				&& (((command)).equals("gamemode")))) {
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager()
						.getAdvancement(new ResourceLocation("variousachievementsplus:nowigod"));
				AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onCommand(CommandEvent event) {
		ICommandSender sender = event.getSender();
		Entity entity = sender.getCommandSenderEntity();
		if (entity != null) {
			int i = (int) sender.getPosition().getX();
			int j = (int) sender.getPosition().getY();
			int k = (int) sender.getPosition().getZ();
			String command = event.getCommand().getName();
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("command", command);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
