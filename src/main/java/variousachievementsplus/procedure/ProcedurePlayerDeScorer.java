package variousachievementsplus.procedure;

import variousachievementsplus.VariousAchievementsVariables;

import variousachievementsplus.ElementsVariousAchievements;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.entity.Entity;

@ElementsVariousAchievements.ModElement.Tag
public class ProcedurePlayerDeScorer extends ElementsVariousAchievements.ModElement {
	public ProcedurePlayerDeScorer(ElementsVariousAchievements instance) {
		super(instance, 27);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PlayerDeScorer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		VariousAchievementsVariables.scorePlayers = (double) ((VariousAchievementsVariables.scorePlayers) - 1);
		if (((VariousAchievementsVariables.scorePlayers) < 0)) {
			VariousAchievementsVariables.scorePlayers = (double) 1;
		}
		System.out.println(((entity) + "" + (" leave world. Now online: ") + "" + ((VariousAchievementsVariables.scorePlayers))));
	}

	@SubscribeEvent
	public void onPlayerLoggedOut(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent event) {
		Entity entity = event.player;
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", (int) entity.posX);
		dependencies.put("y", (int) entity.posY);
		dependencies.put("z", (int) entity.posZ);
		dependencies.put("world", entity.world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
