package com.google.gson;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public final class JsonObject extends JsonElement {
   private final LinkedTreeMap<String, JsonElement> members = new LinkedTreeMap();

   JsonObject deepCopy() {
      JsonObject result = new JsonObject();
      Iterator var2 = this.members.entrySet().iterator();

      while(var2.hasNext()) {
         Entry<String, JsonElement> entry = (Entry)var2.next();
         result.add((String)entry.getKey(), ((JsonElement)entry.getValue()).deepCopy());
      }

      return result;
   }

   public void add(String property, JsonElement value) {
      if (value == null) {
         value = JsonNull.INSTANCE;
      }

      this.members.put(property, value);
   }

   public JsonElement remove(String property) {
      return (JsonElement)this.members.remove(property);
   }

   public void addProperty(String property, String value) {
      this.add(property, this.createJsonElement(value));
   }

   public void addProperty(String property, Number value) {
      this.add(property, this.createJsonElement(value));
   }

   public void addProperty(String property, Boolean value) {
      this.add(property, this.createJsonElement(value));
   }

   public void addProperty(String property, Character value) {
      this.add(property, this.createJsonElement(value));
   }

   private JsonElement createJsonElement(Object value) {
      return (JsonElement)(value == null ? JsonNull.INSTANCE : new JsonPrimitive(value));
   }

   public Set<Entry<String, JsonElement>> entrySet() {
      return this.members.entrySet();
   }

   public Set<String> keySet() {
      return this.members.keySet();
   }

   public int size() {
      return this.members.size();
   }

   public boolean has(String memberName) {
      return this.members.containsKey(memberName);
   }

   public JsonElement get(String memberName) {
      return (JsonElement)this.members.get(memberName);
   }

   public JsonPrimitive getAsJsonPrimitive(String memberName) {
      return (JsonPrimitive)this.members.get(memberName);
   }

   public JsonArray getAsJsonArray(String memberName) {
      return (JsonArray)this.members.get(memberName);
   }

   public JsonObject getAsJsonObject(String memberName) {
      return (JsonObject)this.members.get(memberName);
   }

   public boolean equals(Object o) {
      return o == this || o instanceof JsonObject && ((JsonObject)o).members.equals(this.members);
   }

   public int hashCode() {
      return this.members.hashCode();
   }
}
