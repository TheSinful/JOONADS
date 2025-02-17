package com.moandjiezana.toml;

import java.util.concurrent.atomic.AtomicInteger;

class TomlParser {
   static Results run(String tomlString) {
      Results results = new Results();
      if (tomlString.isEmpty()) {
         return results;
      } else {
         AtomicInteger index = new AtomicInteger();
         boolean inComment = false;
         AtomicInteger line = new AtomicInteger(1);
         Identifier identifier = null;
         Object value = null;

         for(int i = index.get(); i < tomlString.length(); i = index.incrementAndGet()) {
            char c = tomlString.charAt(i);
            if (results.errors.hasErrors()) {
               break;
            }

            if (c == '#' && !inComment) {
               inComment = true;
            } else if (!Character.isWhitespace(c) && !inComment && identifier == null) {
               Identifier id = IdentifierConverter.IDENTIFIER_CONVERTER.convert(tomlString, index, new Context((Identifier)null, line, results.errors));
               if (id != Identifier.INVALID) {
                  if (id.isKey()) {
                     identifier = id;
                  } else if (id.isTable()) {
                     results.startTables(id, line);
                  } else if (id.isTableArray()) {
                     results.startTableArray(id, line);
                  }
               }
            } else if (c == '\n') {
               inComment = false;
               identifier = null;
               value = null;
               line.incrementAndGet();
            } else if (!inComment && identifier != null && identifier.isKey() && value == null && !Character.isWhitespace(c)) {
               value = ValueReaders.VALUE_READERS.convert(tomlString, index, new Context(identifier, line, results.errors));
               if (value instanceof Results.Errors) {
                  results.errors.add((Results.Errors)value);
               } else {
                  results.addValue(identifier.getName(), value, line);
               }
            } else if (value != null && !inComment && !Character.isWhitespace(c)) {
               results.errors.invalidTextAfterIdentifier(identifier, c, line.get());
            }
         }

         return results;
      }
   }

   private TomlParser() {
   }
}
