package ninjabrain.logisticbots.lib;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LibResources {
	
	public static final ResourceLocation LOGISTICS_ROBOT_TEXTURE = new ResourceLocation(LibMod.MODID, "textures/entity/logisticrobot.png");
	public static final ResourceLocation SIMPLE_CHEST_BG_TEXTURE = new ResourceLocation(LibMod.MODID, "textures/gui/simplechest.png");

}
