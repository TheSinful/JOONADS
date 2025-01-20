package com.moandjiezana.toml;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MapValueWriter implements ValueWriter {
   static final ValueWriter MAP_VALUE_WRITER = new MapValueWriter();
   private static final Pattern REQUIRED_QUOTING_PATTERN = Pattern.compile("^.*[^A-Za-z\\d_-].*$");

   public boolean canWrite(Object value) {
      return value instanceof Map;
   }

   public void write(Object value, WriterContext context) {
      Map<?, ?> from = (Map)value;
      if (hasPrimitiveValues(from, context)) {
         context.writeKey();
      }

      Iterator var4 = from.entrySet().iterator();

      Object fromValue;
      while(var4.hasNext()) {
         Entry<?, ?> entry = (Entry)var4.next();
         fromValue = entry.getKey();
         Object fromValue = entry.getValue();
         if (fromValue != null) {
            ValueWriter valueWriter = ValueWriters.WRITERS.findWriterFor(fromValue);
            if (valueWriter.isPrimitiveType()) {
               context.indent();
               context.write(quoteKey(fromValue)).write(" = ");
               valueWriter.write(fromValue, context);
               context.write('\n');
            } else if (valueWriter == PrimitiveArrayValueWriter.PRIMITIVE_ARRAY_VALUE_WRITER) {
               context.setArrayKey(fromValue.toString());
               context.write(quoteKey(fromValue)).write(" = ");
               valueWriter.write(fromValue, context);
               context.write('\n');
            }
         }
      }

      var4 = from.keySet().iterator();

      while(true) {
         Object key;
         ValueWriter valueWriter;
         do {
            do {
               if (!var4.hasNext()) {
                  return;
               }

               key = var4.next();
               fromValue = from.get(key);
            } while(fromValue == null);

            valueWriter = ValueWriters.WRITERS.findWriterFor(fromValue);
         } while(valueWriter != this && valueWriter != ObjectValueWriter.OBJECT_VALUE_WRITER && valueWriter != TableArrayValueWriter.TABLE_ARRAY_VALUE_WRITER);

         valueWriter.write(fromValue, context.pushTable(quoteKey(key)));
      }
   }

   public boolean isPrimitiveType() {
      return false;
   }

   private static String quoteKey(Object key) {
      String stringKey = key.toString();
      Matcher matcher = REQUIRED_QUOTING_PATTERN.matcher(stringKey);
      if (matcher.matches()) {
         stringKey = "\"" + stringKey + "\"";
      }

      return stringKey;
   }

   private static boolean hasPrimitiveValues(Map<?, ?> values, WriterContext context) {
      Iterator var2 = values.keySet().iterator();

      ValueWriter valueWriter;
      do {
         Object fromValue;
         do {
            if (!var2.hasNext()) {
               return false;
            }

            Object key = var2.next();
            fromValue = values.get(key);
         } while(fromValue == null);

         valueWriter = ValueWriters.WRITERS.findWriterFor(fromValue);
      } while(!valueWriter.isPrimitiveType() && valueWriter != PrimitiveArrayValueWriter.PRIMITIVE_ARRAY_VALUE_WRITER);

      return true;
   }

   private MapValueWriter() {
   }
}
