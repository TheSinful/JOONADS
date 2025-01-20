package me.jooon.Seecreter;

import me.jooon.Seecreter.commands.ScCommand;
import me.jooon.Seecreter.config.SeecreterConfig;
import me.jooon.Seecreter.features.AutoClose;
import me.jooon.Seecreter.features.SecretAura;
import me.jooon.Seecreter.utils.DungeonLocationUtils;
import me.jooon.Seecreter.utils.LocationUtils;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
   modid = "Seecreter",
   useMetadata = true
)
public class Seecreter {
   public static final String MOD_ID = "Seecreter";
   public static SeecreterConfig config;

   @EventHandler
   public void init(FMLInitializationEvent event) {
      config = new SeecreterConfig();
      MinecraftForge.EVENT_BUS.register(new LocationUtils());
      MinecraftForge.EVENT_BUS.register(new DungeonLocationUtils());
      MinecraftForge.EVENT_BUS.register(new SecretAura());
      MinecraftForge.EVENT_BUS.register(new AutoClose());
      ClientCommandHandler.instance.func_71560_a(new ScCommand());
   }
}
