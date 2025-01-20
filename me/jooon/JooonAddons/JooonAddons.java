package me.jooon.JooonAddons;

import java.io.File;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonKt;
import me.jooon.JooonAddons.commands.JooonAddonsCommand;
import me.jooon.JooonAddons.config.Config;
import me.jooon.JooonAddons.config.PersistentSave;
import me.jooon.JooonAddons.features.autoExperiments.AutoChromanotron;
import me.jooon.JooonAddons.features.autoExperiments.AutoSequencer;
import me.jooon.JooonAddons.features.betterlootshare.ESP;
import me.jooon.JooonAddons.features.dungeons.AutoP3GhostBlocks;
import me.jooon.JooonAddons.features.dungeons.BetterStonk;
import me.jooon.JooonAddons.features.dungeons.BonzoMask;
import me.jooon.JooonAddons.features.dungeons.HelmetSwapper;
import me.jooon.JooonAddons.features.dungeons.Jerry;
import me.jooon.JooonAddons.features.dungeons.RemoveBlindness;
import me.jooon.JooonAddons.features.dungeons.StarredMobESP;
import me.jooon.JooonAddons.features.dungeons.TerminalWaypoints;
import me.jooon.JooonAddons.features.funnyFishing.BarnFishingTimer;
import me.jooon.JooonAddons.features.funnyFishing.FishingData;
import me.jooon.JooonAddons.features.funnyFishing.FishingTracker;
import me.jooon.JooonAddons.features.funnyFishing.FunnyFishing;
import me.jooon.JooonAddons.features.other.ArmorSwapper;
import me.jooon.JooonAddons.features.other.AutoDojo;
import me.jooon.JooonAddons.features.other.AutoMelody;
import me.jooon.JooonAddons.features.other.Ping;
import me.jooon.JooonAddons.gui.GuiManager;
import me.jooon.JooonAddons.render.DisplayNotification;
import me.jooon.JooonAddons.utils.SBInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Mod(
   modid = "joonaddons",
   name = "Jooon Addons",
   version = "3.0",
   dependencies = "before:*",
   useMetadata = true,
   clientSideOnly = true,
   acceptedMinecraftVersions = "[1.8.9]"
)
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\fH\u0007¨\u0006\u000e"},
   d2 = {"Lme/jooon/JooonAddons/JooonAddons;", "", "()V", "onInit", "", "event", "Lnet/minecraftforge/fml/common/event/FMLInitializationEvent;", "onTick", "Lnet/minecraftforge/fml/common/gameevent/TickEvent$ClientTickEvent;", "postInit", "Lnet/minecraftforge/fml/common/event/FMLPostInitializationEvent;", "preInit", "Lnet/minecraftforge/fml/common/event/FMLPreInitializationEvent;", "Companion", "JooonAddons"}
)
@SourceDebugExtension({"SMAP\nJooonAddons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JooonAddons.kt\nme/jooon/JooonAddons/JooonAddons\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,191:1\n1855#2,2:192\n*S KotlinDebug\n*F\n+ 1 JooonAddons.kt\nme/jooon/JooonAddons/JooonAddons\n*L\n155#1:192,2\n*E\n"})
public final class JooonAddons {
   @NotNull
   public static final JooonAddons.Companion Companion = new JooonAddons.Companion((DefaultConstructorMarker)null);
   @NotNull
   private static final Minecraft mc;
   @Nullable
   private static GuiScreen currentGui;
   @NotNull
   private static final Lazy<File> configDirectory$delegate;
   @NotNull
   private static KeyBinding[] keyBindings;
   public static Config config;
   @NotNull
   public static final String MODID = "joonaddons";
   @NotNull
   public static final String MOD_NAME = "Jooon Addons";
   @NotNull
   public static final String VERSION = "3.0";
   public static ModMetadata metadata;
   @NotNull
   public static final String prefix = "§7[§aJooonAddons§7]§8";
   private static boolean devMode;
   @NotNull
   private static final Lazy<File> modDir$delegate;
   @NotNull
   private static final CoroutineScope IO;
   @JvmField
   @Nullable
   public static File jarFile;
   public static GuiManager guiManager;
   @NotNull
   private static final Json json;

   @EventHandler
   public final void preInit(@NotNull FMLPreInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      System.out.println(Loader.instance().getConfigDir());
      JooonAddons.Companion var10000 = Companion;
      ModMetadata var10001 = event.getModMetadata();
      Intrinsics.checkNotNullExpressionValue(var10001, "event.modMetadata");
      var10000.setMetadata(var10001);

      try {
         Companion.setConfig(Config.INSTANCE);
         Config.INSTANCE.initialize();
      } catch (NoClassDefFoundError var4) {
         System.out.println("Warning: Class me/jooon/JooonAddons/config/Config not found. Skipping config initialization.");
      } catch (ClassNotFoundException var5) {
         System.out.println("Warning: Class gg/essential/vigilance/Vigilant not found. Skipping Vigilant configuration.");
      } catch (Exception var6) {
         System.out.println("An unexpected error occurred while initializing config: " + var6.getMessage());
      }

      try {
         var10000 = Companion;
         jarFile = event.getSourceFile();
         Companion.setGuiManager(GuiManager.INSTANCE);
      } catch (Exception var3) {
         System.out.println("Error during initialization: " + var3.getMessage());
      }

   }

   @EventHandler
   public final void onInit(@NotNull FMLInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      ClientCommandHandler.instance.func_71560_a((ICommand)(new JooonAddonsCommand()));
      Object[] var2 = new Object[]{this, Companion.getGuiManager(), SBInfo.INSTANCE, ESP.INSTANCE, AutoMelody.INSTANCE, Ping.INSTANCE, Jerry.INSTANCE, AutoDojo.INSTANCE, DisplayNotification.INSTANCE, ArmorSwapper.INSTANCE, AutoChromanotron.INSTANCE, AutoSequencer.INSTANCE, BarnFishingTimer.INSTANCE, FishingData.INSTANCE, FishingTracker.INSTANCE, FunnyFishing.INSTANCE, AutoP3GhostBlocks.INSTANCE, BetterStonk.INSTANCE, HelmetSwapper.INSTANCE, RemoveBlindness.INSTANCE, StarredMobESP.INSTANCE, TerminalWaypoints.INSTANCE, BonzoMask.INSTANCE};
      Iterable $this$forEach$iv = (Iterable)CollectionsKt.listOf(var2);
      EventBus var10000 = MinecraftForge.EVENT_BUS;
      Intrinsics.checkNotNullExpressionValue(var10000, "EVENT_BUS");
      EventBus var3 = var10000;
      int $i$f$forEach = false;
      Iterator var5 = $this$forEach$iv.iterator();

      while(var5.hasNext()) {
         Object element$iv = var5.next();
         int var8 = false;
         var3.register(element$iv);
      }

      Runtime.getRuntime().addShutdownHook(new Thread(JooonAddons::onInit$lambda$0));
      keyBindings[1] = new KeyBinding("Auto Fish", 38, "JooonAddons");
      KeyBinding[] var10 = keyBindings;
      int var11 = 0;

      for(int var12 = var10.length; var11 < var12; ++var11) {
         KeyBinding keyBinding = var10[var11];
         if (keyBinding != null) {
            ClientRegistry.registerKeyBinding(keyBinding);
         }
      }

   }

   @EventHandler
   public final void postInit(@NotNull FMLPostInitializationEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      PersistentSave.Companion.loadData();
      Config.INSTANCE.loadData();
   }

   @SubscribeEvent
   public final void onTick(@NotNull ClientTickEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      if (keyBindings[1] != null) {
         KeyBinding var10000 = keyBindings[1];
         Intrinsics.checkNotNull(var10000);
         if (var10000.func_151468_f()) {
            FunnyFishing.INSTANCE.toggleFishing();
         }
      }

      if (event.phase == Phase.START && currentGui != null) {
         mc.func_147108_a(currentGui);
         JooonAddons.Companion var2 = Companion;
         currentGui = null;
      }
   }

   private static final void onInit$lambda$0() {
      Config.INSTANCE.markDirty();
      Config.INSTANCE.writeData();
   }

   @NotNull
   public static final GuiManager getGuiManager() {
      return Companion.getGuiManager();
   }

   public static final void setGuiManager(@NotNull GuiManager <set-?>) {
      Companion.setGuiManager(var0);
   }

   static {
      Minecraft var10000 = Minecraft.func_71410_x();
      Intrinsics.checkNotNullExpressionValue(var10000, "getMinecraft()");
      mc = var10000;
      configDirectory$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      keyBindings = new KeyBinding[6];
      modDir$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      IO = (CoroutineScope)(new CoroutineScope() {
         @NotNull
         private final CoroutineContext coroutineContext = Dispatchers.getIO().plus((CoroutineContext)SupervisorKt.SupervisorJob$default((Job)null, 1, (Object)null)).plus((CoroutineContext)(new CoroutineName("JooonAddons IO")));

         @NotNull
         public CoroutineContext getCoroutineContext() {
            return this.coroutineContext;
         }
      });
      json = JsonKt.Json$default((Json)null, (Function1)null.INSTANCE, 1, (Object)null);
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R$\u0010#\u001a\u00020$8\u0006@\u0006X\u0087.¢\u0006\u0014\n\u0000\u0012\u0004\b%\u0010\u0002\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0014\u0010*\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0011\u0010+\u001a\u00020,¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R$\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010100X\u0086\u000e¢\u0006\u0010\n\u0002\u00106\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0011\u00107\u001a\u000208¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001b\u0010A\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bC\u0010\u0016\u001a\u0004\bB\u0010\u0014R\u000e\u0010D\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006E"},
      d2 = {"Lme/jooon/JooonAddons/JooonAddons$Companion;", "", "()V", "IO", "Lkotlinx/coroutines/CoroutineScope;", "getIO", "()Lkotlinx/coroutines/CoroutineScope;", "MODID", "", "MOD_NAME", "VERSION", "config", "Lme/jooon/JooonAddons/config/Config;", "getConfig", "()Lme/jooon/JooonAddons/config/Config;", "setConfig", "(Lme/jooon/JooonAddons/config/Config;)V", "configDirectory", "Ljava/io/File;", "getConfigDirectory", "()Ljava/io/File;", "configDirectory$delegate", "Lkotlin/Lazy;", "currentGui", "Lnet/minecraft/client/gui/GuiScreen;", "getCurrentGui", "()Lnet/minecraft/client/gui/GuiScreen;", "setCurrentGui", "(Lnet/minecraft/client/gui/GuiScreen;)V", "devMode", "", "getDevMode", "()Z", "setDevMode", "(Z)V", "guiManager", "Lme/jooon/JooonAddons/gui/GuiManager;", "getGuiManager$annotations", "getGuiManager", "()Lme/jooon/JooonAddons/gui/GuiManager;", "setGuiManager", "(Lme/jooon/JooonAddons/gui/GuiManager;)V", "jarFile", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "keyBindings", "", "Lnet/minecraft/client/settings/KeyBinding;", "getKeyBindings", "()[Lnet/minecraft/client/settings/KeyBinding;", "setKeyBindings", "([Lnet/minecraft/client/settings/KeyBinding;)V", "[Lnet/minecraft/client/settings/KeyBinding;", "mc", "Lnet/minecraft/client/Minecraft;", "getMc", "()Lnet/minecraft/client/Minecraft;", "metadata", "Lnet/minecraftforge/fml/common/ModMetadata;", "getMetadata", "()Lnet/minecraftforge/fml/common/ModMetadata;", "setMetadata", "(Lnet/minecraftforge/fml/common/ModMetadata;)V", "modDir", "getModDir", "modDir$delegate", "prefix", "JooonAddons"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Minecraft getMc() {
         return JooonAddons.mc;
      }

      @Nullable
      public final GuiScreen getCurrentGui() {
         return JooonAddons.currentGui;
      }

      public final void setCurrentGui(@Nullable GuiScreen <set-?>) {
         JooonAddons.currentGui = var1;
      }

      @NotNull
      public final File getConfigDirectory() {
         Lazy var1 = JooonAddons.configDirectory$delegate;
         return (File)var1.getValue();
      }

      @NotNull
      public final KeyBinding[] getKeyBindings() {
         return JooonAddons.keyBindings;
      }

      public final void setKeyBindings(@NotNull KeyBinding[] <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         JooonAddons.keyBindings = var1;
      }

      @NotNull
      public final Config getConfig() {
         Config var10000 = JooonAddons.config;
         if (var10000 != null) {
            return var10000;
         } else {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            return null;
         }
      }

      public final void setConfig(@NotNull Config <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         JooonAddons.config = var1;
      }

      @NotNull
      public final ModMetadata getMetadata() {
         ModMetadata var10000 = JooonAddons.metadata;
         if (var10000 != null) {
            return var10000;
         } else {
            Intrinsics.throwUninitializedPropertyAccessException("metadata");
            return null;
         }
      }

      public final void setMetadata(@NotNull ModMetadata <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         JooonAddons.metadata = var1;
      }

      public final boolean getDevMode() {
         return JooonAddons.devMode;
      }

      public final void setDevMode(boolean <set-?>) {
         JooonAddons.devMode = var1;
      }

      @NotNull
      public final File getModDir() {
         Lazy var1 = JooonAddons.modDir$delegate;
         return (File)var1.getValue();
      }

      @NotNull
      public final CoroutineScope getIO() {
         return JooonAddons.IO;
      }

      @NotNull
      public final GuiManager getGuiManager() {
         GuiManager var10000 = JooonAddons.guiManager;
         if (var10000 != null) {
            return var10000;
         } else {
            Intrinsics.throwUninitializedPropertyAccessException("guiManager");
            return null;
         }
      }

      public final void setGuiManager(@NotNull GuiManager <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         JooonAddons.guiManager = var1;
      }

      /** @deprecated */
      // $FF: synthetic method
      @JvmStatic
      public static void getGuiManager$annotations() {
      }

      @NotNull
      public final Json getJson() {
         return JooonAddons.json;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
