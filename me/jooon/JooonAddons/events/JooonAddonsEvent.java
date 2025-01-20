package me.jooon.JooonAddons.events;

import gg.essential.universal.UChat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Result.Companion;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nR\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"},
   d2 = {"Lme/jooon/JooonAddons/events/JooonAddonsEvent;", "Lnet/minecraftforge/fml/common/eventhandler/Event;", "()V", "eventName", "", "getEventName", "()Ljava/lang/String;", "eventName$delegate", "Lkotlin/Lazy;", "postAndCatch", "", "JooonAddons"}
)
public abstract class JooonAddonsEvent extends Event {
   @NotNull
   private final Lazy eventName$delegate = LazyKt.lazy((Function0)(new Function0<String>() {
      @Nullable
      public final String invoke() {
         return Reflection.getOrCreateKotlinClass(JooonAddonsEvent.this.getClass()).getSimpleName();
      }
   }));

   private final String getEventName() {
      Lazy var1 = this.eventName$delegate;
      return (String)var1.getValue();
   }

   public final boolean postAndCatch() {
      JooonAddonsEvent var1 = this;

      Object var2;
      Companion var10000;
      try {
         var10000 = Result.Companion;
         JooonAddonsEvent $this$postAndCatch_u24lambda_u240 = (JooonAddonsEvent)var1;
         int var3 = false;
         var2 = Result.constructor-impl(MinecraftForge.EVENT_BUS.post((Event)$this$postAndCatch_u24lambda_u240));
      } catch (Throwable var5) {
         var10000 = Result.Companion;
         var2 = Result.constructor-impl(ResultKt.createFailure(var5));
      }

      Throwable var9 = Result.exceptionOrNull-impl(var2);
      if (var9 != null) {
         var2 = var9;
         int var4 = false;
         ((Throwable)var2).printStackTrace();
         StringBuilder var10 = (new StringBuilder()).append("ERROR! caught and logged an ");
         String var10001 = Reflection.getOrCreateKotlinClass(var2.getClass()).getSimpleName();
         if (var10001 == null) {
            var10001 = "error";
         }

         UChat.chat(var10.append(var10001).append(" at ").append(this.getEventName()).append(". Please report this.").toString());
      }

      Object var6 = var2;
      Boolean var8 = this.isCanceled();
      return (Boolean)(Result.isFailure-impl(var6) ? var8 : var6);
   }
}
