package com.moandjiezana.toml;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class ObjectValueWriter implements ValueWriter {
   static final ValueWriter OBJECT_VALUE_WRITER = new ObjectValueWriter();

   public boolean canWrite(Object value) {
      return true;
   }

   public void write(Object value, WriterContext context) {
      Map<String, Object> to = new LinkedHashMap();
      Set<Field> fields = getFields(value.getClass());
      Iterator var5 = fields.iterator();

      while(var5.hasNext()) {
         Field field = (Field)var5.next();
         to.put(field.getName(), getFieldValue(field, value));
      }

      MapValueWriter.MAP_VALUE_WRITER.write(to, context);
   }

   public boolean isPrimitiveType() {
      return false;
   }

   private static Set<Field> getFields(Class<?> cls) {
      LinkedHashSet fields;
      for(fields = new LinkedHashSet(Arrays.asList(cls.getDeclaredFields())); cls != Object.class; cls = cls.getSuperclass()) {
         fields.addAll(Arrays.asList(cls.getDeclaredFields()));
      }

      removeConstantsAndSyntheticFields(fields);
      return fields;
   }

   private static void removeConstantsAndSyntheticFields(Set<Field> fields) {
      Iterator iterator = fields.iterator();

      while(true) {
         Field field;
         do {
            if (!iterator.hasNext()) {
               return;
            }

            field = (Field)iterator.next();
         } while((!Modifier.isFinal(field.getModifiers()) || !Modifier.isStatic(field.getModifiers())) && !field.isSynthetic() && !Modifier.isTransient(field.getModifiers()));

         iterator.remove();
      }
   }

   private static Object getFieldValue(Field field, Object o) {
      boolean isAccessible = field.isAccessible();
      field.setAccessible(true);
      Object value = null;

      try {
         value = field.get(o);
      } catch (IllegalAccessException var5) {
      }

      field.setAccessible(isAccessible);
      return value;
   }

   private ObjectValueWriter() {
   }
}
