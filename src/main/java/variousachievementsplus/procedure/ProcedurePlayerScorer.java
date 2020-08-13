package variousachievementsplus.procedure;

import variousachievementsplus.VariousAchievementsVariables;

import variousachievementsplus.ElementsVariousAchievements;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.entity.Entity;

@ElementsVariousAchievements.ModElement.Tag
public class ProcedurePlayerScorer extends ElementsVariousAchievements.ModElement {
	public ProcedurePlayerScorer(ElementsVariousAchievements instance) {
		super(instance, 26);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PlayerScorer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		VariousAchievementsVariables.scorePlayers = (double) ((VariousAchievementsVariables.scorePlayers) + 1);
		System.out.println(((entity) + "" + (" join. Now online: ") + "" + ((VariousAchievementsVariables.scorePlayers))));
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
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
