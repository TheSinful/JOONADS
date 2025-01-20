package com.moandjiezana.toml;

import java.util.Collection;
import java.util.Iterator;

class TableArrayValueWriter extends ArrayValueWriter {
   static final ValueWriter TABLE_ARRAY_VALUE_WRITER = new TableArrayValueWriter();

   public boolean canWrite(Object value) {
      return isArrayish(value) && !isArrayOfPrimitive(value);
   }

   public void write(Object from, WriterContext context) {
      Collection<?> values = this.normalize(from);
      WriterContext subContext = context.pushTableFromArray();
      Iterator var5 = values.iterator();

      while(var5.hasNext()) {
         Object value = var5.next();
         ValueWriters.WRITERS.findWriterFor(value).write(value, subContext);
      }

   }

   private TableArrayValueWriter() {
   }

   public String toString() {
      return "table-array";
   }
}
