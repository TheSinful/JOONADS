package me.jooon.Seecreter.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;

public class ScoreboardUtils {
   public static List<String> getSidebarScores(Scoreboard scoreboard) {
      List<String> found = new ArrayList();
      ScoreObjective sidebar = scoreboard.func_96539_a(1);
      if (sidebar != null) {
         List<Score> scores = new ArrayList(scoreboard.func_96528_e());
         scores.sort(Comparator.comparingInt(Score::func_96652_c));
         found = (List)scores.stream().filter((score) -> {
            return score.func_96645_d().func_96679_b().equals(sidebar.func_96679_b());
         }).map((score) -> {
            return getPrefixFromContainingTeam(scoreboard, score.func_96653_e()) + getSuffixFromContainingTeam(scoreboard, score.func_96653_e());
         }).collect(Collectors.toList());
      }

      return (List)found;
   }

   private static String getPrefixFromContainingTeam(Scoreboard scoreboard, String member) {
      String prefix = null;
      Iterator var3 = scoreboard.func_96525_g().iterator();

      while(var3.hasNext()) {
         ScorePlayerTeam team = (ScorePlayerTeam)var3.next();
         if (team.func_96670_d().contains(member)) {
            prefix = team.func_96668_e();
            break;
         }
      }

      return prefix == null ? "" : prefix;
   }

   private static String getSuffixFromContainingTeam(Scoreboard scoreboard, String member) {
      String suffix = null;
      Iterator var3 = scoreboard.func_96525_g().iterator();

      while(var3.hasNext()) {
         ScorePlayerTeam team = (ScorePlayerTeam)var3.next();
         if (team.func_96670_d().contains(member)) {
            suffix = team.func_96663_f();
            break;
         }
      }

      return suffix == null ? "" : suffix;
   }
}
