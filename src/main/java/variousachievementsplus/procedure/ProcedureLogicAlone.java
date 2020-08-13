package variousachievementsplus.procedure;

import variousachievementsplus.VariousAchievementsVariables;

import variousachievementsplus.ElementsVariousAchievements;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Iterator;

@ElementsVariousAchievements.ModElement.Tag
public class ProcedureLogicAlone extends ElementsVariousAchievements.ModElement {
	public ProcedureLogicAlone(ElementsVariousAchievements instance) {
		super(instance, 18);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure LogicAlone!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((VariousAchievementsVariables.scorePlayers) == 1)) {
			entity.getEntityData().setDouble("aloneMessages", ((entity.getEntityData().getDouble("aloneMessages")) + 1));
			System.out.println(
					(("Alone Messages of ") + "" + (entity) + "" + (" now is ") + "" + ((entity.getEntityData().getDouble("aloneMessages")))));
		}
		if (((entity.getEntityData().getDouble("aloneMessages")) >= 100)) {
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager()
						.getAdvancement(new ResourceLocation("variousachievementsplus:alone"));
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
	public void onChat(ServerChatEvent event) {
		EntityPlayer entity = event.getPlayer();
		int i = (int) entity.posX;
		int j = (int) entity.posY;
		int k = (int) entity.posZ;
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("text", event.getMessage());
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
