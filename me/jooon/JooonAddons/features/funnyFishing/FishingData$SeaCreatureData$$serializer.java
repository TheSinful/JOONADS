package me.jooon.JooonAddons.features.funnyFishing;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.GeneratedSerializer.DefaultImpls;
import org.jetbrains.annotations.NotNull;

/** @deprecated */
@Deprecated(
   message = "This synthesized declaration should not be used directly",
   replaceWith = @ReplaceWith(
   expression = "",
   imports = {}
),
   level = DeprecationLevel.HIDDEN
)
@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÖ\u0001¢\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u00058VXÖ\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"},
   d2 = {"me/jooon/JooonAddons/features/funnyFishing/FishingData.SeaCreatureData.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lme/jooon/JooonAddons/features/funnyFishing/FishingData$SeaCreatureData;", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "JooonAddons"}
)
public final class FishingData$SeaCreatureData$$serializer implements GeneratedSerializer<FishingData.SeaCreatureData> {
   @NotNull
   public static final FishingData$SeaCreatureData$$serializer INSTANCE = new FishingData$SeaCreatureData$$serializer();
   // $FF: synthetic field
   private static final PluginGeneratedSerialDescriptor descriptor;

   private FishingData$SeaCreatureData$$serializer() {
   }

   @NotNull
   public KSerializer<?>[] typeParametersSerializers() {
      return DefaultImpls.typeParametersSerializers(this);
   }

   @NotNull
   public SerialDescriptor getDescriptor() {
      return (SerialDescriptor)descriptor;
   }

   @NotNull
   public KSerializer<?>[] childSerializers() {
      KSerializer[] var1 = new KSerializer[]{IntSerializer.INSTANCE, DoubleSerializer.INSTANCE};
      return var1;
   }

   @NotNull
   public FishingData.SeaCreatureData deserialize(@NotNull Decoder decoder) {
      Intrinsics.checkNotNullParameter(decoder, "decoder");
      SerialDescriptor var2 = this.getDescriptor();
      boolean var3 = true;
      int var5 = 0;
      int var6 = 0;
      double var7 = 0.0D;
      CompositeDecoder var9 = decoder.beginStructure(var2);
      if (var9.decodeSequentially()) {
         var6 = var9.decodeIntElement(var2, 0);
         var5 |= 1;
         var7 = var9.decodeDoubleElement(var2, 1);
         var5 |= 2;
      } else {
         while(var3) {
            int var4 = var9.decodeElementIndex(var2);
            switch(var4) {
            case -1:
               var3 = false;
               break;
            case 0:
               var6 = var9.decodeIntElement(var2, 0);
               var5 |= 1;
               break;
            case 1:
               var7 = var9.decodeDoubleElement(var2, 1);
               var5 |= 2;
               break;
            default:
               throw new UnknownFieldException(var4);
            }
         }
      }

      var9.endStructure(var2);
      return new FishingData.SeaCreatureData(var5, var6, var7, (SerializationConstructorMarker)null);
   }

   public void serialize(@NotNull Encoder encoder, @NotNull FishingData.SeaCreatureData value) {
      Intrinsics.checkNotNullParameter(encoder, "encoder");
      Intrinsics.checkNotNullParameter(value, "value");
      SerialDescriptor var3 = this.getDescriptor();
      CompositeEncoder var4 = encoder.beginStructure(var3);
      FishingData.SeaCreatureData.write$Self(value, var4, var3);
      var4.endStructure(var3);
   }

   static {
      PluginGeneratedSerialDescriptor var0 = new PluginGeneratedSerialDescriptor("me.jooon.JooonAddons.features.funnyFishing.FishingData.SeaCreatureData", (GeneratedSerializer)INSTANCE, 2);
      var0.addElement("catches", false);
      var0.addElement("timeSince", false);
      descriptor = var0;
   }
}
