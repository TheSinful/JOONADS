package me.jooon.Seecreter.utils;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class BlockUtils {
   public static MovingObjectPosition collisionRayTrace(BlockPos pos, AxisAlignedBB aabb, Vec3 start, Vec3 end) {
      start = start.func_178788_d(new Vec3(pos));
      end = end.func_178788_d(new Vec3(pos));
      Vec3 vec3 = start.func_72429_b(end, aabb.field_72340_a);
      Vec3 vec31 = start.func_72429_b(end, aabb.field_72336_d);
      Vec3 vec32 = start.func_72435_c(end, aabb.field_72338_b);
      Vec3 vec33 = start.func_72435_c(end, aabb.field_72337_e);
      Vec3 vec34 = start.func_72434_d(end, aabb.field_72339_c);
      Vec3 vec35 = start.func_72434_d(end, aabb.field_72334_f);
      if (isVecOutsideYZBounds(vec3, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72337_e, aabb.field_72334_f)) {
         vec3 = null;
      }

      if (isVecOutsideYZBounds(vec31, aabb.field_72338_b, aabb.field_72339_c, aabb.field_72337_e, aabb.field_72334_f)) {
         vec31 = null;
      }

      if (isVecOutsideXZBounds(vec32, aabb.field_72340_a, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72334_f)) {
         vec32 = null;
      }

      if (isVecOutsideXZBounds(vec33, aabb.field_72340_a, aabb.field_72339_c, aabb.field_72336_d, aabb.field_72334_f)) {
         vec33 = null;
      }

      if (isVecOutsideXYBounds(vec34, aabb.field_72340_a, aabb.field_72338_b, aabb.field_72336_d, aabb.field_72337_e)) {
         vec34 = null;
      }

      if (isVecOutsideXYBounds(vec35, aabb.field_72340_a, aabb.field_72338_b, aabb.field_72336_d, aabb.field_72337_e)) {
         vec35 = null;
      }

      Vec3 vec36 = null;
      if (vec3 != null) {
         vec36 = vec3;
      }

      if (vec31 != null && (vec36 == null || start.func_72436_e(vec31) < start.func_72436_e(vec36))) {
         vec36 = vec31;
      }

      if (vec32 != null && (vec36 == null || start.func_72436_e(vec32) < start.func_72436_e(vec36))) {
         vec36 = vec32;
      }

      if (vec33 != null && (vec36 == null || start.func_72436_e(vec33) < start.func_72436_e(vec36))) {
         vec36 = vec33;
      }

      if (vec34 != null && (vec36 == null || start.func_72436_e(vec34) < start.func_72436_e(vec36))) {
         vec36 = vec34;
      }

      if (vec35 != null && (vec36 == null || start.func_72436_e(vec35) < start.func_72436_e(vec36))) {
         vec36 = vec35;
      }

      if (vec36 == null) {
         return null;
      } else {
         EnumFacing enumfacing = null;
         if (vec36 == vec3) {
            enumfacing = EnumFacing.WEST;
         }

         if (vec36 == vec31) {
            enumfacing = EnumFacing.EAST;
         }

         if (vec36 == vec32) {
            enumfacing = EnumFacing.DOWN;
         }

         if (vec36 == vec33) {
            enumfacing = EnumFacing.UP;
         }

         if (vec36 == vec34) {
            enumfacing = EnumFacing.NORTH;
         }

         if (vec36 == vec35) {
            enumfacing = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(vec36, enumfacing, pos);
      }
   }

   private static boolean isVecOutsideYZBounds(Vec3 point, double minY, double minZ, double maxY, double maxZ) {
      return point == null || !(point.field_72448_b >= minY) || !(point.field_72448_b <= maxY) || !(point.field_72449_c >= minZ) || !(point.field_72449_c <= maxZ);
   }

   private static boolean isVecOutsideXZBounds(Vec3 point, double minX, double minZ, double maxX, double maxZ) {
      return point == null || !(point.field_72450_a >= minX) || !(point.field_72450_a <= maxX) || !(point.field_72449_c >= minZ) || !(point.field_72449_c <= maxZ);
   }

   private static boolean isVecOutsideXYBounds(Vec3 point, double minX, double minY, double maxX, double maxY) {
      return point == null || !(point.field_72450_a >= minX) || !(point.field_72450_a <= maxX) || !(point.field_72448_b >= minY) || !(point.field_72448_b <= maxY);
   }
}
