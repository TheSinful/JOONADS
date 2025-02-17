package com.moandjiezana.toml;

class IndentationPolicy {
   private final int tableIndent;
   private final int keyValueIndent;
   private final int arrayDelimiterPadding;

   IndentationPolicy(int keyIndentation, int tableIndentation, int arrayDelimiterPadding) {
      this.keyValueIndent = keyIndentation;
      this.tableIndent = tableIndentation;
      this.arrayDelimiterPadding = arrayDelimiterPadding;
   }

   int getTableIndent() {
      return this.tableIndent;
   }

   int getKeyValueIndent() {
      return this.keyValueIndent;
   }

   int getArrayDelimiterPadding() {
      return this.arrayDelimiterPadding;
   }
}
