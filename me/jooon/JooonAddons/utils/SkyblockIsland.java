package me.jooon.JooonAddons.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.descriptors.PrimitiveKind.STRING;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001aB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001b"},
   d2 = {"Lme/jooon/JooonAddons/utils/SkyblockIsland;", "", "formattedName", "", "mode", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getFormattedName", "()Ljava/lang/String;", "getMode", "PrivateIsland", "SpiderDen", "CrimsonIsle", "TheEnd", "GoldMine", "DeepCaverns", "DwarvenMines", "CrystalHollows", "FarmingIsland", "ThePark", "Dungeon", "DungeonHub", "Hub", "DarkAuction", "JerryWorkshop", "Instanced", "Unknown", "ModeSerializer", "JooonAddons"}
)
public enum SkyblockIsland {
   @NotNull
   private final String formattedName;
   @NotNull
   private final String mode;
   PrivateIsland("Private Island", "dynamic"),
   SpiderDen("Spider's Den", "combat_1"),
   CrimsonIsle("Crimson Isle", "crimson_isle"),
   TheEnd("The End", "combat_3"),
   GoldMine("Gold Mine", "mining_1"),
   DeepCaverns("Deep Caverns", "mining_2"),
   DwarvenMines("Dwarven Mines", "mining_3"),
   CrystalHollows("Crystal Hollows", "crystal_hollows"),
   FarmingIsland("The Farming Islands", "farming_1"),
   ThePark("The Park", "foraging_1"),
   Dungeon("Dungeon", "dungeon"),
   DungeonHub("Dungeon Hub", "dungeon_hub"),
   Hub("Hub", "hub"),
   DarkAuction("Dark Auction", "dark_auction"),
   JerryWorkshop("Jerry's Workshop", "winter"),
   Instanced("Instanced", "instanced"),
   Unknown("(Unknown)", "");

   private SkyblockIsland(String formattedName, String mode) {
      this.formattedName = formattedName;
      this.mode = mode;
   }

   @NotNull
   public final String getFormattedName() {
      return this.formattedName;
   }

   @NotNull
   public final String getMode() {
      return this.mode;
   }

   // $FF: synthetic method
   private static final SkyblockIsland[] $values() {
      SkyblockIsland[] var0 = new SkyblockIsland[]{PrivateIsland, SpiderDen, CrimsonIsle, TheEnd, GoldMine, DeepCaverns, DwarvenMines, CrystalHollows, FarmingIsland, ThePark, Dungeon, DungeonHub, Hub, DarkAuction, JerryWorkshop, Instanced, Unknown};
      return var0;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"},
      d2 = {"Lme/jooon/JooonAddons/utils/SkyblockIsland$ModeSerializer;", "Lkotlinx/serialization/KSerializer;", "Lme/jooon/JooonAddons/utils/SkyblockIsland;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "JooonAddons"}
   )
   @SourceDebugExtension({"SMAP\nSBInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SBInfo.kt\nme/jooon/JooonAddons/utils/SkyblockIsland$ModeSerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,222:1\n1#2:223\n1282#3,2:224\n*S KotlinDebug\n*F\n+ 1 SBInfo.kt\nme/jooon/JooonAddons/utils/SkyblockIsland$ModeSerializer\n*L\n209#1:224,2\n*E\n"})
   public static final class ModeSerializer implements KSerializer<SkyblockIsland> {
      @NotNull
      public static final SkyblockIsland.ModeSerializer INSTANCE = new SkyblockIsland.ModeSerializer();
      @NotNull
      private static final SerialDescriptor descriptor;

      private ModeSerializer() {
      }

      @NotNull
      public SerialDescriptor getDescriptor() {
         return descriptor;
      }

      @NotNull
      public SkyblockIsland deserialize(@NotNull Decoder decoder) {
         Intrinsics.checkNotNullParameter(decoder, "decoder");
         String s = decoder.decodeString();
         int var3 = false;
         Object[] $this$firstOrNull$iv = SkyblockIsland.values();
         int $i$f$firstOrNull = false;
         int var6 = 0;
         int var7 = $this$firstOrNull$iv.length;

         SkyblockIsland var10000;
         while(true) {
            if (var6 >= var7) {
               var10000 = null;
               break;
            }

            Object element$iv = $this$firstOrNull$iv[var6];
            int var10 = false;
            if (Intrinsics.areEqual(element$iv.getMode(), s)) {
               var10000 = element$iv;
               break;
            }

            ++var6;
         }

         if (var10000 == null) {
            var10000 = SkyblockIsland.Unknown;
         }

         return var10000;
      }

      public void serialize(@NotNull Encoder encoder, @NotNull SkyblockIsland value) {
         Intrinsics.checkNotNullParameter(encoder, "encoder");
         Intrinsics.checkNotNullParameter(value, "value");
         encoder.encodeString(value.getMode());
      }

      static {
         descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("SkyblockIsland", (PrimitiveKind)STRING.INSTANCE);
      }
   }
}
