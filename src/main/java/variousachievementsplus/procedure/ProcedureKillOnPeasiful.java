package variousachievementsplus.procedure;

import variousachievementsplus.ElementsVariousachievementsplusMod;

import net.minecraft.world.World;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;

@ElementsVariousachievementsplusMod.ModElement.Tag
public class ProcedureKillOnPeasiful extends ElementsVariousachievementsplusMod.ModElement {
	public ProcedureKillOnPeasiful(ElementsVariousachievementsplusMod instance) {
		super(instance, 4);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KillOnPeasiful!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure KillOnPeasiful!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if ((world.getDifficulty() == EnumDifficulty.PEACEFUL)) {
			if (entity instanceof EntityPlayerMP) {
				Advancement _adv = ((MinecraftServer) ((EntityPlayerMP) entity).mcServer).getAdvancementManager()
						.getAdvancement(new ResourceLocation("variousachievementsplus:wtfyoudoing"));
				AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
			entity.getEntityData().setString("vapAchievement", ((((entity.getEntityData().getString("vapAchievement")).substring((int) 0, (int) 0)))
					+ "" + (1) + "" + (((entity.getEntityData().getString("vapAchievement")).substring((int) 2, (int) 13)))));
		}
	}
}
