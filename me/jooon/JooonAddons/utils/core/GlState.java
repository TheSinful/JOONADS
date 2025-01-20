package me.jooon.JooonAddons.utils.core;

import java.nio.FloatBuffer;
import java.util.ArrayDeque;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010(\u001a\u00020)J\u0006\u0010*\u001a\u00020)R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\"\u0010\u001b\u001a\n \u001d*\u0004\u0018\u00010\u001c0\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001a\u0010%\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\b¨\u0006,"},
   d2 = {"Lme/jooon/JooonAddons/utils/core/GlState;", "", "()V", "alphaState", "", "getAlphaState", "()Z", "setAlphaState", "(Z)V", "blendAlphaDst", "", "getBlendAlphaDst", "()I", "setBlendAlphaDst", "(I)V", "blendAlphaSrc", "getBlendAlphaSrc", "setBlendAlphaSrc", "blendDst", "getBlendDst", "setBlendDst", "blendSrc", "getBlendSrc", "setBlendSrc", "blendState", "getBlendState", "setBlendState", "colorState", "Ljava/nio/FloatBuffer;", "kotlin.jvm.PlatformType", "getColorState", "()Ljava/nio/FloatBuffer;", "setColorState", "(Ljava/nio/FloatBuffer;)V", "depthState", "getDepthState", "setDepthState", "lightingState", "getLightingState", "setLightingState", "popState", "", "pushState", "Companion", "JooonAddons"}
)
public final class GlState {
   @NotNull
   public static final GlState.Companion Companion = new GlState.Companion((DefaultConstructorMarker)null);
   private boolean lightingState;
   private boolean blendState;
   private int blendSrc;
   private int blendDst;
   private int blendAlphaSrc = 1;
   private int blendAlphaDst;
   private boolean alphaState;
   private boolean depthState;
   private FloatBuffer colorState = GLAllocation.func_74524_c(64).asFloatBuffer();
   private static final boolean newBlend;
   @NotNull
   private static final ArrayDeque<GlState> stack = new ArrayDeque();

   public final boolean getLightingState() {
      return this.lightingState;
   }

   public final void setLightingState(boolean <set-?>) {
      this.lightingState = var1;
   }

   public final boolean getBlendState() {
      return this.blendState;
   }

   public final void setBlendState(boolean <set-?>) {
      this.blendState = var1;
   }

   public final int getBlendSrc() {
      return this.blendSrc;
   }

   public final void setBlendSrc(int <set-?>) {
      this.blendSrc = var1;
   }

   public final int getBlendDst() {
      return this.blendDst;
   }

   public final void setBlendDst(int <set-?>) {
      this.blendDst = var1;
   }

   public final int getBlendAlphaSrc() {
      return this.blendAlphaSrc;
   }

   public final void setBlendAlphaSrc(int <set-?>) {
      this.blendAlphaSrc = var1;
   }

   public final int getBlendAlphaDst() {
      return this.blendAlphaDst;
   }

   public final void setBlendAlphaDst(int <set-?>) {
      this.blendAlphaDst = var1;
   }

   public final boolean getAlphaState() {
      return this.alphaState;
   }

   public final void setAlphaState(boolean <set-?>) {
      this.alphaState = var1;
   }

   public final boolean getDepthState() {
      return this.depthState;
   }

   public final void setDepthState(boolean <set-?>) {
      this.depthState = var1;
   }

   public final FloatBuffer getColorState() {
      return this.colorState;
   }

   public final void setColorState(FloatBuffer <set-?>) {
      this.colorState = var1;
   }

   public final void pushState() {
      this.lightingState = GL11.glIsEnabled(2896);
      this.blendState = GL11.glIsEnabled(3042);
      this.blendSrc = GL11.glGetInteger(3041);
      this.blendDst = GL11.glGetInteger(3040);
      this.alphaState = GL11.glIsEnabled(3008);
      this.depthState = GL11.glIsEnabled(2929);
      if (newBlend) {
         this.blendSrc = GL11.glGetInteger(32969);
         this.blendDst = GL11.glGetInteger(32968);
         this.blendAlphaSrc = GL11.glGetInteger(32971);
         this.blendAlphaDst = GL11.glGetInteger(32970);
      }

      GL11.glGetFloat(2816, this.colorState);
   }

   public final void popState() {
      if (this.depthState) {
         GlStateManager.func_179126_j();
      } else {
         GlStateManager.func_179097_i();
      }

      if (this.blendState) {
         GlStateManager.func_179147_l();
      } else {
         GlStateManager.func_179084_k();
      }

      if (this.alphaState) {
         GlStateManager.func_179141_d();
      } else {
         GlStateManager.func_179118_c();
      }

      GlStateManager.func_179120_a(this.blendSrc, this.blendDst, this.blendAlphaSrc, this.blendAlphaDst);
      GlStateManager.func_179131_c(this.colorState.get(0), this.colorState.get(1), this.colorState.get(2), this.colorState.get(3));
   }

   static {
      ContextCapabilities context = GLContext.getCapabilities();
      newBlend = context.OpenGL14 || context.GL_EXT_blend_func_separate;
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"},
      d2 = {"Lme/jooon/JooonAddons/utils/core/GlState$Companion;", "", "()V", "newBlend", "", "getNewBlend", "()Z", "stack", "Ljava/util/ArrayDeque;", "Lme/jooon/JooonAddons/utils/core/GlState;", "getStack", "()Ljava/util/ArrayDeque;", "popState", "", "pushState", "JooonAddons"}
   )
   @SourceDebugExtension({"SMAP\nGlState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GlState.kt\nme/jooon/JooonAddons/utils/core/GlState$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,69:1\n1#2:70\n*E\n"})
   public static final class Companion {
      private Companion() {
      }

      public final boolean getNewBlend() {
         return GlState.newBlend;
      }

      @NotNull
      public final ArrayDeque<GlState> getStack() {
         return GlState.stack;
      }

      public final void pushState() {
         ArrayDeque var10000 = this.getStack();
         GlState var1 = new GlState();
         ArrayDeque var4 = var10000;
         int var3 = false;
         var1.pushState();
         var4.addLast(var1);
      }

      public final void popState() {
         ((GlState)this.getStack().removeLast()).popState();
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
