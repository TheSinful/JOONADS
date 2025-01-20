package me.jooon.Seecreter.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Checkbox;
import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.config.annotations.KeyBind;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class SeecreterConfig extends Config {
   @Switch(
      name = "Seecreter"
   )
   public static boolean secretAuraEnabled = false;
   @Switch(
      name = "Outside of Dungeons"
   )
   public static boolean secretAuraNotDungeon = false;
   @Slider(
      name = "Range",
      min = 2.1F,
      max = 6.2F
   )
   public static float secretAuraRange = 2.1F;
   @Slider(
      name = "Skull Range",
      min = 2.1F,
      max = 4.2F
   )
   public static float secretAuraSkullRange = 2.1F;
   @Switch(
      name = "Swing"
   )
   public static boolean secretAuraSwingHand = true;
   @Switch(
      name = "Auto Close Chests"
   )
   public static boolean secretAuraAutoClose = true;
   @KeyBind(
      name = "Toggle Seecreter Keybind"
   )
   public static OneKeyBind secretAuraToggleKeyBind = new OneKeyBind();
   @KeyBind(
      name = "Clear clicked blocks"
   )
   public static OneKeyBind secretAuraClearKeyBind = new OneKeyBind();
   @Dropdown(
      name = "Swap to set slot on",
      options = {"None", "Skulls", "All"}
   )
   public static int secretAuraSwapOn = 0;
   @Switch(
      name = "Swap Back to held item"
   )
   public static boolean secretAuraSwapBack = true;
   @Slider(
      name = "Slot",
      min = 1.0F,
      max = 9.0F
   )
   public static int secretAuraSlot = 1;
   @Checkbox(
      name = "Blaze",
      subcategory = "Rooms"
   )
   public static boolean roomBlaze = false;
   @Checkbox(
      name = "Boulder",
      subcategory = "Rooms"
   )
   public static boolean roomBoulder = true;
   @Checkbox(
      name = "Creeper Beams",
      subcategory = "Rooms"
   )
   public static boolean roomCreeperBeams = true;
   @Checkbox(
      name = "Ice Fill",
      subcategory = "Rooms"
   )
   public static boolean roomIceFill = false;
   @Checkbox(
      name = "Ice Path",
      subcategory = "Rooms"
   )
   public static boolean roomIcePath = false;
   @Checkbox(
      name = "Teleport Maze",
      subcategory = "Rooms"
   )
   public static boolean roomTeleportMaze = false;
   @Checkbox(
      name = "Three Weirdos",
      subcategory = "Rooms"
   )
   public static boolean roomThreeWeirdos = false;
   @Checkbox(
      name = "Tic Tac Toe",
      subcategory = "Rooms"
   )
   public static boolean roomTicTacToe = true;
   @Checkbox(
      name = "Water Board",
      subcategory = "Rooms"
   )
   public static boolean roomWaterBoard = false;

   public SeecreterConfig() {
      super(new Mod("Seecreter", ModType.SKYBLOCK), "config.json");

      try {
         System.out.println("Initializing SeecreterConfig...");
         this.initialize();
         System.out.println("SeecreterConfig initialized successfully.");
      } catch (NoClassDefFoundError var2) {
         System.err.println("OneConfig Profiles class is missing: " + var2.getMessage());
         System.err.println("Disabling Profiles-dependent functionality.");
      } catch (Exception var3) {
         System.err.println("Failed to initialize SeecreterConfig: " + var3.getMessage());
         var3.printStackTrace();
      }

   }
}
