package me.jooon.JooonAddons.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.TimersKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.text.Charsets;
import kotlinx.serialization.json.Json;
import me.jooon.JooonAddons.JooonAddons;
import me.jooon.JooonAddons.utils.Utils;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH&J\b\u0010\u001f\u001a\u00020\u0016H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"},
   d2 = {"Lme/jooon/JooonAddons/config/PersistentSave;", "", "saveFile", "Ljava/io/File;", "(Ljava/io/File;)V", "dirty", "", "getDirty", "()Z", "setDirty", "(Z)V", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "mc", "Lnet/minecraft/client/Minecraft;", "getMc", "()Lnet/minecraft/client/Minecraft;", "getSaveFile", "()Ljava/io/File;", "init", "", "read", "reader", "Ljava/io/Reader;", "readSave", "setDefault", "writer", "Ljava/io/Writer;", "write", "writeSave", "Companion", "JooonAddons"}
)
public abstract class PersistentSave {
   @NotNull
   public static final PersistentSave.Companion Companion = new PersistentSave.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final File saveFile;
   private boolean dirty;
   @NotNull
   private final Minecraft mc;
   @NotNull
   private final Json json;
   @NotNull
   private static final HashSet<PersistentSave> SAVES = new HashSet();

   public PersistentSave(@NotNull File saveFile) {
      Intrinsics.checkNotNullParameter(saveFile, "saveFile");
      super();
      this.saveFile = saveFile;
      this.mc = JooonAddons.Companion.getMc();
      this.json = JooonAddons.Companion.getJson();
      this.init();
   }

   @NotNull
   protected final File getSaveFile() {
      return this.saveFile;
   }

   public final boolean getDirty() {
      return this.dirty;
   }

   public final void setDirty(boolean <set-?>) {
      this.dirty = var1;
   }

   @NotNull
   protected final Minecraft getMc() {
      return this.mc;
   }

   @NotNull
   protected final Json getJson() {
      return this.json;
   }

   public abstract void read(@NotNull Reader var1);

   public abstract void write(@NotNull Writer var1);

   public abstract void setDefault(@NotNull Writer var1);

   private final void readSave() {
      try {
         Utils.INSTANCE.ensureFile(this.saveFile);
         File var1 = this.saveFile;
         Charset var26 = Charsets.UTF_8;
         short var29 = 8192;
         Reader var33 = (Reader)(new InputStreamReader((InputStream)(new FileInputStream(var1)), var26));
         Closeable var24 = (Closeable)(var33 instanceof BufferedReader ? (BufferedReader)var33 : new BufferedReader(var33, var29));
         Throwable var27 = null;

         try {
            BufferedReader it = (BufferedReader)var24;
            int var35 = false;
            this.read((Reader)it);
            Unit var34 = Unit.INSTANCE;
         } catch (Throwable var21) {
            var27 = var21;
            throw var21;
         } finally {
            CloseableKt.closeFinally(var24, var27);
         }
      } catch (Exception var23) {
         var23.printStackTrace();

         try {
            File var2 = this.saveFile;
            Charset var3 = Charsets.UTF_8;
            short var4 = 8192;
            Writer var5 = (Writer)(new OutputStreamWriter((OutputStream)(new FileOutputStream(var2)), var3));
            Closeable var25 = (Closeable)(var5 instanceof BufferedWriter ? (BufferedWriter)var5 : new BufferedWriter(var5, var4));
            Throwable var28 = null;

            try {
               BufferedWriter it = (BufferedWriter)var25;
               int var36 = false;
               this.setDefault((Writer)it);
               Unit var32 = Unit.INSTANCE;
            } catch (Throwable var18) {
               var28 = var18;
               throw var18;
            } finally {
               CloseableKt.closeFinally(var25, var28);
            }
         } catch (Exception var20) {
            var20.printStackTrace();
         }
      }

   }

   private final void writeSave() {
      try {
         Utils.INSTANCE.ensureFile(this.saveFile);
         File var1 = this.saveFile;
         Charset var2 = Charsets.UTF_8;
         Closeable var11 = (Closeable)(new OutputStreamWriter((OutputStream)(new FileOutputStream(var1)), var2));
         Throwable var12 = null;

         try {
            OutputStreamWriter writer = (OutputStreamWriter)var11;
            int var4 = false;
            this.write((Writer)writer);
            Unit var13 = Unit.INSTANCE;
         } catch (Throwable var8) {
            var12 = var8;
            throw var8;
         } finally {
            CloseableKt.closeFinally(var11, var12);
         }

         this.dirty = false;
      } catch (Exception var10) {
         var10.printStackTrace();
      }

   }

   private final void init() {
      SAVES.add(this);
   }

   private static final void _init_$lambda$4() {
      Iterator var0 = SAVES.iterator();

      while(var0.hasNext()) {
         PersistentSave save = (PersistentSave)var0.next();
         if (save.dirty) {
            save.writeSave();
         }
      }

   }

   // $FF: synthetic method
   public static final void access$writeSave(PersistentSave $this) {
      $this.writeSave();
   }

   static {
      String var0 = "JooonAddons-PersistentSave-Write";
      long var4 = 30000L;
      boolean var1 = false;
      long var2 = 0L;
      Timer var6 = TimersKt.timer(var0, var1);
      var6.scheduleAtFixedRate((TimerTask)(new PersistentSave$special$$inlined$fixedRateTimer$default$1()), var2, var4);
      Runtime.getRuntime().addShutdownHook(new Thread(PersistentSave::_init_$lambda$4, "JooonAddons-PersistentSave-Shutdown"));
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u000b\u001a\u00020\n\"\n\b\u0000\u0010\f\u0018\u0001*\u00020\u0005H\u0086\bJ\u0016\u0010\u000b\u001a\u00020\n2\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000eR!\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"},
      d2 = {"Lme/jooon/JooonAddons/config/PersistentSave$Companion;", "", "()V", "SAVES", "Ljava/util/HashSet;", "Lme/jooon/JooonAddons/config/PersistentSave;", "Lkotlin/collections/HashSet;", "getSAVES", "()Ljava/util/HashSet;", "loadData", "", "markDirty", "T", "clazz", "Lkotlin/reflect/KClass;", "JooonAddons"}
   )
   @SourceDebugExtension({"SMAP\nPersistentSave.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PersistentSave.kt\nme/jooon/JooonAddons/config/PersistentSave$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,110:1\n1#2:111\n1855#3,2:112\n*S KotlinDebug\n*F\n+ 1 PersistentSave.kt\nme/jooon/JooonAddons/config/PersistentSave$Companion\n*L\n94#1:112,2\n*E\n"})
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final HashSet<PersistentSave> getSAVES() {
         return PersistentSave.SAVES;
      }

      public final void markDirty(@NotNull KClass<? extends PersistentSave> clazz) {
         Intrinsics.checkNotNullParameter(clazz, "clazz");
         Iterable var3 = (Iterable)this.getSAVES();
         Iterator var4 = var3.iterator();

         Object var10000;
         while(true) {
            if (var4.hasNext()) {
               Object var5 = var4.next();
               PersistentSave it = (PersistentSave)var5;
               int var7 = false;
               if (!Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(it.getClass()), clazz)) {
                  continue;
               }

               var10000 = var5;
               break;
            }

            var10000 = null;
            break;
         }

         PersistentSave var8 = (PersistentSave)var10000;
         if (var8 == null) {
            throw new IllegalAccessException("PersistentSave not found");
         } else {
            PersistentSave save = var8;
            save.setDirty(true);
         }
      }

      // $FF: synthetic method
      public final <T extends PersistentSave> void markDirty() {
         int $i$f$markDirty = false;
         Intrinsics.reifiedOperationMarker(4, "T");
         this.markDirty(Reflection.getOrCreateKotlinClass(PersistentSave.class));
      }

      public final void loadData() {
         Iterable $this$forEach$iv = (Iterable)this.getSAVES();
         int $i$f$forEach = false;
         Iterator var3 = $this$forEach$iv.iterator();

         while(var3.hasNext()) {
            Object element$iv = var3.next();
            PersistentSave it = (PersistentSave)element$iv;
            int var6 = false;
            it.readSave();
         }

      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
