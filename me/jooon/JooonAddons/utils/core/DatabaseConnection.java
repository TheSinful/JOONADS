package me.jooon.JooonAddons.utils.core;

import gg.essential.api.utils.Multithreading;
import gg.essential.universal.UChat;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import me.jooon.JooonAddons.utils.HttpUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"},
   d2 = {"Lme/jooon/JooonAddons/utils/core/DatabaseConnection;", "", "()V", "fetchData", "", "collection", "JooonAddons"}
)
public final class DatabaseConnection {
   @NotNull
   public static final DatabaseConnection INSTANCE = new DatabaseConnection();

   private DatabaseConnection() {
   }

   @NotNull
   public final String fetchData(@NotNull String collection) {
      Intrinsics.checkNotNullParameter(collection, "collection");
      Multithreading $this$fetchData_u24lambda_u240 = Multithreading.INSTANCE;
      int var3 = false;
      String data = "{\n        \"dataSource\": \"JooonAddons\",\n        \"database\": \"Users\",\n        \"collection\": \"" + collection + "\",\n        \"filter\": {},\n        \"sort\": { \"completedAt\": 1 }\n        }";
      String url = "https://eu-central-1.aws.data.mongodb-api.com/app/data-zqdkt/endpoint/data/v1/action/find";
      HttpUtils var10000 = HttpUtils.INSTANCE;
      Pair[] var6 = new Pair[]{TuplesKt.to("apiKey", "7nSTRxvFtfbn8EjepVoKabDkG4OHfcyapTz3kVPePrcS1KnOTmEhf8ol7d1Ri92t"), TuplesKt.to("Content-Type", "application/json")};
      String response = HttpUtils.sendPost$default(var10000, url, data, MapsKt.mapOf(var6), (String)null, 8, (Object)null);
      if (response != null) {
         UChat.chat(StringsKt.replace$default(response, "Â", "", false, 4, (Object)null));
         return StringsKt.replace$default(response, "Â", "", false, 4, (Object)null);
      } else {
         UChat.chat("Null?");
         return "Request failed with response code: " + response;
      }
   }
}
