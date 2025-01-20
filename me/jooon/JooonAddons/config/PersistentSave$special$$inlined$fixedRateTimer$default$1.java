package me.jooon.JooonAddons.config;

import java.util.Iterator;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"},
   d2 = {"kotlin/concurrent/TimersKt$timerTask$1", "Ljava/util/TimerTask;", "run", "", "kotlin-stdlib"}
)
@SourceDebugExtension({"SMAP\nTimer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timer.kt\nkotlin/concurrent/TimersKt$timerTask$1\n+ 2 PersistentSave.kt\nme/jooon/JooonAddons/config/PersistentSave\n*L\n1#1,148:1\n99#2,4:149\n*E\n"})
public final class PersistentSave$special$$inlined$fixedRateTimer$default$1 extends TimerTask {
   public void run() {
      TimerTask $this$_init__u24lambda_u243 = (TimerTask)this;
      int var2 = false;
      Iterator var3 = PersistentSave.access$getSAVES$cp().iterator();

      while(var3.hasNext()) {
         PersistentSave save = (PersistentSave)var3.next();
         if (save.getDirty()) {
            Intrinsics.checkNotNullExpressionValue(save, "save");
            PersistentSave.access$writeSave(save);
         }
      }

   }
}
