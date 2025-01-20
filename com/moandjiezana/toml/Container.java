package com.moandjiezana.toml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

abstract class Container {
   abstract boolean accepts(String var1);

   abstract void put(String var1, Object var2);

   abstract Object get(String var1);

   abstract boolean isImplicit();

   private Container() {
   }

   // $FF: synthetic method
   Container(Object x0) {
      this();
   }

   static class TableArray extends Container {
      private final List<Container.Table> values = new ArrayList();

      TableArray() {
         super(null);
         this.values.add(new Container.Table());
      }

      boolean accepts(String key) {
         return this.getCurrent().accepts(key);
      }

      void put(String key, Object value) {
         this.values.add((Container.Table)value);
      }

      Object get(String key) {
         throw new UnsupportedOperationException();
      }

      boolean isImplicit() {
         return false;
      }

      List<Map<String, Object>> getValues() {
         ArrayList<Map<String, Object>> unwrappedValues = new ArrayList();
         Iterator var2 = this.values.iterator();

         while(var2.hasNext()) {
            Container.Table table = (Container.Table)var2.next();
            unwrappedValues.add(table.consume());
         }

         return unwrappedValues;
      }

      Container.Table getCurrent() {
         return (Container.Table)this.values.get(this.values.size() - 1);
      }

      public String toString() {
         return this.values.toString();
      }
   }

   static class Table extends Container {
      private final Map<String, Object> values;
      final String name;
      final boolean implicit;

      Table() {
         this((String)null, false);
      }

      public Table(String name) {
         this(name, false);
      }

      public Table(String tableName, boolean implicit) {
         super(null);
         this.values = new HashMap();
         this.name = tableName;
         this.implicit = implicit;
      }

      boolean accepts(String key) {
         return !this.values.containsKey(key) || this.values.get(key) instanceof Container.TableArray;
      }

      void put(String key, Object value) {
         this.values.put(key, value);
      }

      Object get(String key) {
         return this.values.get(key);
      }

      boolean isImplicit() {
         return this.implicit;
      }

      Map<String, Object> consume() {
         Iterator var1 = this.values.entrySet().iterator();

         while(var1.hasNext()) {
            Entry<String, Object> entry = (Entry)var1.next();
            if (entry.getValue() instanceof Container.Table) {
               entry.setValue(((Container.Table)entry.getValue()).consume());
            } else if (entry.getValue() instanceof Container.TableArray) {
               entry.setValue(((Container.TableArray)entry.getValue()).getValues());
            }
         }

         return this.values;
      }

      public String toString() {
         return this.values.toString();
      }
   }
}
