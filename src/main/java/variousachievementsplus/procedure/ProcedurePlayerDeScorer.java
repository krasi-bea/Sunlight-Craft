package variousachievementsplus.procedure;

import variousachievementsplus.VariousachievementsplusModVariables;

import variousachievementsplus.ElementsVariousachievementsplusMod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.entity.Entity;

import java.util.Map;

@ElementsVariousachievementsplusMod.ModElement.Tag
public class ProcedurePlayerDeScorer extends ElementsVariousachievementsplusMod.ModElement {
	public ProcedurePlayerDeScorer(ElementsVariousachievementsplusMod instance) {
		super(instance, 27);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PlayerDeScorer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		VariousachievementsplusModVariables.scorePlayers = (double) ((VariousachievementsplusModVariables.scorePlayers) - 1);
		if (((VariousachievementsplusModVariables.scorePlayers) < 0)) {
			VariousachievementsplusModVariables.scorePlayers = (double) 1;
		}
		System.out.println(((entity) + "" + (" leave world. Now online: ") + "" + ((VariousachievementsplusModVariables.scorePlayers))));
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
