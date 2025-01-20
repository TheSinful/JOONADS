package me.jooon.JooonAddons.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Category;
import gg.essential.vigilance.data.PropertyCollector;
import gg.essential.vigilance.data.SortingBehavior;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jooon.JooonAddons.JooonAddons;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 8, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000=\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b5\n\u0002\u0018\u0002\n\u0003\bÚ\u0001\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002À\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010¾\u0002\u001a\u00030¿\u0002H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#R\u001a\u0010'\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\bR\u001a\u0010*\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0006\"\u0004\b,\u0010\bR\u001a\u0010-\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0006\"\u0004\b5\u0010\bR\u001a\u00106\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00100\"\u0004\b8\u00102R\u001a\u00109\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00100\"\u0004\b;\u00102R\u001a\u0010<\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0006\"\u0004\b>\u0010\bR\u001a\u0010?\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010!\"\u0004\bA\u0010#R\u001a\u0010B\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u00100\"\u0004\bD\u00102R\u001a\u0010E\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010!\"\u0004\bG\u0010#R\u001a\u0010H\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010!\"\u0004\bJ\u0010#R\u001a\u0010K\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u0006\"\u0004\bM\u0010\bR\u001a\u0010N\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010\u0006\"\u0004\bP\u0010\bR\u001a\u0010Q\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010\u0006\"\u0004\bS\u0010\bR\u001a\u0010T\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010\u0006\"\u0004\bV\u0010\bR\u001a\u0010W\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010\u0006\"\u0004\bY\u0010\bR\u001a\u0010Z\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010!\"\u0004\b\\\u0010#R\u001a\u0010]\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010\u0006\"\u0004\b_\u0010\bR\u001a\u0010`\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010\u0006\"\u0004\bb\u0010\bR\u001a\u0010c\u001a\u00020dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\u001a\u0010i\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010\u0006\"\u0004\bk\u0010\bR\u001a\u0010l\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0006\"\u0004\bn\u0010\bR\u001a\u0010o\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010\u0006\"\u0004\bq\u0010\bR\u001a\u0010r\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010\u0006\"\u0004\bt\u0010\bR\u001a\u0010u\u001a\u00020dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u0010f\"\u0004\bw\u0010hR\u001a\u0010x\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010!\"\u0004\bz\u0010#R\u001a\u0010{\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u00100\"\u0004\b}\u00102R\u001b\u0010~\u001a\u00020dX\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010f\"\u0005\b\u0080\u0001\u0010hR\u001d\u0010\u0081\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0006\"\u0005\b\u0083\u0001\u0010\bR\u001d\u0010\u0084\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010\u0006\"\u0005\b\u0086\u0001\u0010\bR\u001d\u0010\u0087\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u0006\"\u0005\b\u0089\u0001\u0010\bR\u001d\u0010\u008a\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010\u0006\"\u0005\b\u008c\u0001\u0010\bR\u001d\u0010\u008d\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0006\"\u0005\b\u008f\u0001\u0010\bR\u001d\u0010\u0090\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010\u0006\"\u0005\b\u0092\u0001\u0010\bR\u001d\u0010\u0093\u0001\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u00100\"\u0005\b\u0095\u0001\u00102R\u001d\u0010\u0096\u0001\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u00100\"\u0005\b\u0098\u0001\u00102R\u001d\u0010\u0099\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010\u0006\"\u0005\b\u009b\u0001\u0010\bR\u001d\u0010\u009c\u0001\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u00100\"\u0005\b\u009e\u0001\u00102R\u001d\u0010\u009f\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010\u0006\"\u0005\b¡\u0001\u0010\bR\u001d\u0010¢\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b£\u0001\u0010\u0006\"\u0005\b¤\u0001\u0010\bR\u001d\u0010¥\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¦\u0001\u0010\u0006\"\u0005\b§\u0001\u0010\bR\u001d\u0010¨\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b©\u0001\u0010\u0006\"\u0005\bª\u0001\u0010\bR\u001d\u0010«\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¬\u0001\u0010\u0006\"\u0005\b\u00ad\u0001\u0010\bR\u001d\u0010®\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¯\u0001\u0010\u0006\"\u0005\b°\u0001\u0010\bR\u001d\u0010±\u0001\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b²\u0001\u00100\"\u0005\b³\u0001\u00102R\u001d\u0010´\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bµ\u0001\u0010\u0006\"\u0005\b¶\u0001\u0010\bR\u001d\u0010·\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¸\u0001\u0010\u0006\"\u0005\b¹\u0001\u0010\bR\u001d\u0010º\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b»\u0001\u0010\u0006\"\u0005\b¼\u0001\u0010\bR\u001d\u0010½\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¾\u0001\u0010\u0006\"\u0005\b¿\u0001\u0010\bR\u001d\u0010À\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÁ\u0001\u0010\u0006\"\u0005\bÂ\u0001\u0010\bR\u001d\u0010Ã\u0001\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÄ\u0001\u00100\"\u0005\bÅ\u0001\u00102R\u001d\u0010Æ\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÇ\u0001\u0010\u0006\"\u0005\bÈ\u0001\u0010\bR\u001d\u0010É\u0001\u001a\u00020dX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÊ\u0001\u0010f\"\u0005\bË\u0001\u0010hR\u001d\u0010Ì\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÍ\u0001\u0010\u0006\"\u0005\bÎ\u0001\u0010\bR\u001d\u0010Ï\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÐ\u0001\u0010\u0006\"\u0005\bÑ\u0001\u0010\bR\u001d\u0010Ò\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÓ\u0001\u0010\u0006\"\u0005\bÔ\u0001\u0010\bR\u001d\u0010Õ\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÖ\u0001\u0010!\"\u0005\b×\u0001\u0010#R\u001d\u0010Ø\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÙ\u0001\u0010!\"\u0005\bÚ\u0001\u0010#R\u001d\u0010Û\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bÜ\u0001\u0010\u0006\"\u0005\bÝ\u0001\u0010\bR\u001d\u0010Þ\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bß\u0001\u0010\u0006\"\u0005\bà\u0001\u0010\bR\u001d\u0010á\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bâ\u0001\u0010\u0006\"\u0005\bã\u0001\u0010\bR\u001d\u0010ä\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bå\u0001\u0010\u0006\"\u0005\bæ\u0001\u0010\bR\u001d\u0010ç\u0001\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bè\u0001\u00100\"\u0005\bé\u0001\u00102R\u001d\u0010ê\u0001\u001a\u00020dX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bë\u0001\u0010f\"\u0005\bì\u0001\u0010hR\u001d\u0010í\u0001\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bî\u0001\u0010!\"\u0005\bï\u0001\u0010#R\u001d\u0010ð\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bñ\u0001\u0010\u0006\"\u0005\bò\u0001\u0010\bR\u001d\u0010ó\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bô\u0001\u0010\u0006\"\u0005\bõ\u0001\u0010\bR\u001d\u0010ö\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b÷\u0001\u0010\u0006\"\u0005\bø\u0001\u0010\bR\u001d\u0010ù\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bú\u0001\u0010\u0006\"\u0005\bû\u0001\u0010\bR\u001d\u0010ü\u0001\u001a\u00020\rX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bý\u0001\u0010\u000f\"\u0005\bþ\u0001\u0010\u0011R\u001d\u0010ÿ\u0001\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0002\u0010\u0006\"\u0005\b\u0081\u0002\u0010\bR\u001d\u0010\u0082\u0002\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0002\u00100\"\u0005\b\u0084\u0002\u00102R\u001d\u0010\u0085\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0002\u0010\u0006\"\u0005\b\u0087\u0002\u0010\bR\u001d\u0010\u0088\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0089\u0002\u0010\u0006\"\u0005\b\u008a\u0002\u0010\bR\u001d\u0010\u008b\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0002\u0010\u0006\"\u0005\b\u008d\u0002\u0010\bR\u001d\u0010\u008e\u0002\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0002\u00100\"\u0005\b\u0090\u0002\u00102R\u001d\u0010\u0091\u0002\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0002\u00100\"\u0005\b\u0093\u0002\u00102R\u001d\u0010\u0094\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0095\u0002\u0010\u0006\"\u0005\b\u0096\u0002\u0010\bR\u001d\u0010\u0097\u0002\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0098\u0002\u00100\"\u0005\b\u0099\u0002\u00102R\u001d\u0010\u009a\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009b\u0002\u0010\u0006\"\u0005\b\u009c\u0002\u0010\bR\u001d\u0010\u009d\u0002\u001a\u00020dX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u009e\u0002\u0010f\"\u0005\b\u009f\u0002\u0010hR\u001d\u0010 \u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¡\u0002\u0010\u0006\"\u0005\b¢\u0002\u0010\bR\u001d\u0010£\u0002\u001a\u00020dX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¤\u0002\u0010f\"\u0005\b¥\u0002\u0010hR\u001d\u0010¦\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b§\u0002\u0010\u0006\"\u0005\b¨\u0002\u0010\bR\u001d\u0010©\u0002\u001a\u00020\u001fX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\bª\u0002\u0010!\"\u0005\b«\u0002\u0010#R\u001d\u0010¬\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u00ad\u0002\u0010\u0006\"\u0005\b®\u0002\u0010\bR\u001d\u0010¯\u0002\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b°\u0002\u00100\"\u0005\b±\u0002\u00102R\u001d\u0010²\u0002\u001a\u00020.X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b³\u0002\u00100\"\u0005\b´\u0002\u00102R\u001d\u0010µ\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¶\u0002\u0010\u0006\"\u0005\b·\u0002\u0010\bR\u001d\u0010¸\u0002\u001a\u00020dX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¹\u0002\u0010f\"\u0005\bº\u0002\u0010hR\u001d\u0010»\u0002\u001a\u00020\u0004X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¼\u0002\u0010\u0006\"\u0005\b½\u0002\u0010\b¨\u0006Á\u0002"},
   d2 = {"Lme/jooon/JooonAddons/config/Config;", "Lgg/essential/vigilance/Vigilant;", "()V", "autoBlazeDaggers", "", "getAutoBlazeDaggers", "()Z", "setAutoBlazeDaggers", "(Z)V", "autoBonzoMask", "getAutoBonzoMask", "setAutoBonzoMask", "autoBonzoMaskHealth", "", "getAutoBonzoMaskHealth", "()F", "setAutoBonzoMaskHealth", "(F)V", "autoCloseChest", "getAutoCloseChest", "setAutoCloseChest", "autoCloseExperiments", "getAutoCloseExperiments", "setAutoCloseExperiments", "autoDaed", "getAutoDaed", "setAutoDaed", "autoDaedArmorSwap", "getAutoDaedArmorSwap", "setAutoDaedArmorSwap", "autoDaedPetNameOne", "", "getAutoDaedPetNameOne", "()Ljava/lang/String;", "setAutoDaedPetNameOne", "(Ljava/lang/String;)V", "autoDaedPetNameTwo", "getAutoDaedPetNameTwo", "setAutoDaedPetNameTwo", "autoDojo", "getAutoDojo", "setAutoDojo", "autoExperiments", "getAutoExperiments", "setAutoExperiments", "autoExperimentsDelay", "", "getAutoExperimentsDelay", "()I", "setAutoExperimentsDelay", "(I)V", "autoFriendHi", "getAutoFriendHi", "setAutoFriendHi", "autoFriendHiCooldown", "getAutoFriendHiCooldown", "setAutoFriendHiCooldown", "autoFriendHiType", "getAutoFriendHiType", "setAutoFriendHiType", "autoGuildHi", "getAutoGuildHi", "setAutoGuildHi", "autoGuildHiCustomMessage", "getAutoGuildHiCustomMessage", "setAutoGuildHiCustomMessage", "autoGuildHiFrequency", "getAutoGuildHiFrequency", "setAutoGuildHiFrequency", "autoHiCustomCommand", "getAutoHiCustomCommand", "setAutoHiCustomCommand", "autoHiFriends", "getAutoHiFriends", "setAutoHiFriends", "autoMelody", "getAutoMelody", "setAutoMelody", "autoOrb", "getAutoOrb", "setAutoOrb", "autoP3P5GhostBlocks", "getAutoP3P5GhostBlocks", "setAutoP3P5GhostBlocks", "autoThankYou", "getAutoThankYou", "setAutoThankYou", "barnFishingTimer", "getBarnFishingTimer", "setBarnFishingTimer", "barnFishingTimerText", "getBarnFishingTimerText", "setBarnFishingTimerText", "betterFarmingHitboxes", "getBetterFarmingHitboxes", "setBetterFarmingHitboxes", "betterLootShare", "getBetterLootShare", "setBetterLootShare", "betterMiniColor", "Ljava/awt/Color;", "getBetterMiniColor", "()Ljava/awt/Color;", "setBetterMiniColor", "(Ljava/awt/Color;)V", "betterStonk", "getBetterStonk", "setBetterStonk", "betterStonkShiftOnly", "getBetterStonkShiftOnly", "setBetterStonkShiftOnly", "bonzoMaskTimer", "getBonzoMaskTimer", "setBonzoMaskTimer", "bossESP", "getBossESP", "setBossESP", "bossESPColor", "getBossESPColor", "setBossESPColor", "customESPMobs", "getCustomESPMobs", "setCustomESPMobs", "daedSwapHealthType", "getDaedSwapHealthType", "setDaedSwapHealthType", "deviceBeaconColor", "getDeviceBeaconColor", "setDeviceBeaconColor", "disableVisible", "getDisableVisible", "setDisableVisible", "displayBarnFishingTimerNotification", "getDisplayBarnFishingTimerNotification", "setDisplayBarnFishingTimerNotification", "dragonTimers", "getDragonTimers", "setDragonTimers", "dropshipNotification", "getDropshipNotification", "setDropshipNotification", "empNotification", "getEmpNotification", "setEmpNotification", "espOnNotifiedMobs", "getEspOnNotifiedMobs", "setEspOnNotifiedMobs", "espSelector", "getEspSelector", "setEspSelector", "fishingKilling", "getFishingKilling", "setFishingKilling", "fishingMove", "getFishingMove", "setFishingMove", "fishingRecastDelay", "getFishingRecastDelay", "setFishingRecastDelay", "fishingRotate", "getFishingRotate", "setFishingRotate", "fishingTotem", "getFishingTotem", "setFishingTotem", "fishingTracker", "getFishingTracker", "setFishingTracker", "fishingTrackerMarina", "getFishingTrackerMarina", "setFishingTrackerMarina", "fishingTrackerSpooky", "getFishingTrackerSpooky", "setFishingTrackerSpooky", "fishingTrackerTimeSince", "getFishingTrackerTimeSince", "setFishingTrackerTimeSince", "fishingTrackerType", "getFishingTrackerType", "setFishingTrackerType", "fishingTrackerTypeAutoDetect", "getFishingTrackerTypeAutoDetect", "setFishingTrackerTypeAutoDetect", "fishingTrackerWinter", "getFishingTrackerWinter", "setFishingTrackerWinter", "focusOnGoldenFish", "getFocusOnGoldenFish", "setFocusOnGoldenFish", "funnyFishing", "getFunnyFishing", "setFunnyFishing", "funnyFishingAutoHook", "getFunnyFishingAutoHook", "setFunnyFishingAutoHook", "funnyFishingAutoKillingDelay", "getFunnyFishingAutoKillingDelay", "setFunnyFishingAutoKillingDelay", "funnyFishingAutoSell", "getFunnyFishingAutoSell", "setFunnyFishingAutoSell", "glowColor", "getGlowColor", "setGlowColor", "glowOnMob", "getGlowOnMob", "setGlowOnMob", "grimNotification", "getGrimNotification", "setGrimNotification", "gwSharkNotification", "getGwSharkNotification", "setGwSharkNotification", "helmetToSwapNameOne", "getHelmetToSwapNameOne", "setHelmetToSwapNameOne", "helmetToSwapNameTwo", "getHelmetToSwapNameTwo", "setHelmetToSwapNameTwo", "hideDefaultNames", "getHideDefaultNames", "setHideDefaultNames", "hydraNotification", "getHydraNotification", "setHydraNotification", "jawbusNotification", "getJawbusNotification", "setJawbusNotification", "jerryKB", "getJerryKB", "setJerryKB", "lastGuildHi", "getLastGuildHi", "setLastGuildHi", "leverBeaconColor", "getLeverBeaconColor", "setLeverBeaconColor", "manualHealthDaed", "getManualHealthDaed", "setManualHealthDaed", "miniESP", "getMiniESP", "setMiniESP", "mobNotification", "getMobNotification", "setMobNotification", "monolithESP", "getMonolithESP", "setMonolithESP", "nutterNotification", "getNutterNotification", "setNutterNotification", "percentageHealthDaed", "getPercentageHealthDaed", "setPercentageHealthDaed", "realisticHeight", "getRealisticHeight", "setRealisticHeight", "realisticHeightType", "getRealisticHeightType", "setRealisticHeightType", "removeArmorGlint", "getRemoveArmorGlint", "setRemoveArmorGlint", "removeBlindness", "getRemoveBlindness", "setRemoveBlindness", "slayerESP", "getSlayerESP", "setSlayerESP", "slayerESPType", "getSlayerESPType", "setSlayerESPType", "specialTropyMode", "getSpecialTropyMode", "setSpecialTropyMode", "spiritMaskTimer", "getSpiritMaskTimer", "setSpiritMaskTimer", "starredESPType", "getStarredESPType", "setStarredESPType", "starredMobESP", "getStarredMobESP", "setStarredMobESP", "starredMobESPColor", "getStarredMobESPColor", "setStarredMobESPColor", "stopOpeningNecromancyGUI", "getStopOpeningNecromancyGUI", "setStopOpeningNecromancyGUI", "terminalBeaconColor", "getTerminalBeaconColor", "setTerminalBeaconColor", "terminalWaypoints", "getTerminalWaypoints", "setTerminalWaypoints", "thankYouMessage", "getThankYouMessage", "setThankYouMessage", "thunderNotification", "getThunderNotification", "setThunderNotification", "timeoutDelay", "getTimeoutDelay", "setTimeoutDelay", "timestampOfBarnFishingNotification", "getTimestampOfBarnFishingNotification", "setTimestampOfBarnFishingNotification", "totemTimer", "getTotemTimer", "setTotemTimer", "worseMiniColor", "getWorseMiniColor", "setWorseMiniColor", "yetiNotification", "getYetiNotification", "setYetiNotification", "openYouTubeLink", "", "ConfigSorting", "JooonAddons"}
)
public final class Config extends Vigilant {
   @NotNull
   public static final Config INSTANCE = new Config();
   private static boolean betterLootShare;
   private static boolean glowOnMob;
   @NotNull
   private static Color glowColor;
   private static boolean disableVisible;
   private static int espSelector;
   private static boolean mobNotification;
   @NotNull
   private static String customESPMobs;
   private static boolean thunderNotification;
   private static boolean jawbusNotification;
   private static boolean gwSharkNotification;
   private static boolean hydraNotification;
   private static boolean grimNotification;
   private static boolean empNotification;
   private static boolean nutterNotification;
   private static boolean yetiNotification;
   private static boolean espOnNotifiedMobs;
   private static boolean autoFriendHi;
   @NotNull
   private static String autoHiFriends;
   private static int autoFriendHiCooldown;
   private static int autoFriendHiType;
   @NotNull
   private static String autoHiCustomCommand;
   private static boolean autoGuildHi;
   private static int lastGuildHi;
   private static boolean autoDojo;
   @NotNull
   private static String autoGuildHiCustomMessage;
   private static int autoGuildHiFrequency;
   private static boolean autoThankYou;
   @NotNull
   private static String thankYouMessage;
   private static boolean funnyFishing;
   private static int specialTropyMode;
   private static boolean focusOnGoldenFish;
   private static boolean barnFishingTimer;
   private static boolean displayBarnFishingTimerNotification;
   private static int timestampOfBarnFishingNotification;
   @NotNull
   private static String barnFishingTimerText;
   private static boolean fishingTracker;
   private static int fishingTrackerType;
   private static boolean fishingTrackerTypeAutoDetect;
   private static boolean fishingTrackerMarina;
   private static boolean fishingTrackerSpooky;
   private static boolean fishingTrackerWinter;
   private static boolean fishingTrackerTimeSince;
   @NotNull
   private static String helmetToSwapNameOne;
   @NotNull
   private static String helmetToSwapNameTwo;
   private static boolean dragonTimers;
   private static boolean starredMobESP;
   @NotNull
   private static Color starredMobESPColor;
   private static boolean terminalWaypoints;
   private static boolean monolithESP;
   private static boolean funnyFishingAutoSell;
   @NotNull
   private static Color deviceBeaconColor;
   @NotNull
   private static Color terminalBeaconColor;
   @NotNull
   private static Color leverBeaconColor;
   private static boolean betterStonk;
   private static boolean betterStonkShiftOnly;
   private static int starredESPType;
   private static boolean autoCloseChest;
   private static boolean hideDefaultNames;
   private static boolean removeBlindness;
   private static boolean autoExperiments;
   private static boolean autoCloseExperiments;
   private static int autoExperimentsDelay;
   private static boolean miniESP;
   private static boolean slayerESP;
   private static boolean betterFarmingHitboxes;
   @NotNull
   private static Color bossESPColor;
   @NotNull
   private static Color worseMiniColor;
   @NotNull
   private static Color betterMiniColor;
   private static int slayerESPType;
   private static boolean bossESP;
   private static boolean autoOrb;
   private static boolean stopOpeningNecromancyGUI;
   private static boolean autoDaed;
   private static boolean autoDaedArmorSwap;
   @NotNull
   private static String autoDaedPetNameOne;
   @NotNull
   private static String autoDaedPetNameTwo;
   private static int daedSwapHealthType;
   private static float percentageHealthDaed;
   @NotNull
   private static String manualHealthDaed;
   private static boolean autoP3P5GhostBlocks;
   private static boolean bonzoMaskTimer;
   private static boolean autoMelody;
   private static boolean spiritMaskTimer;
   private static boolean autoBonzoMask;
   private static float autoBonzoMaskHealth;
   private static boolean realisticHeight;
   private static int realisticHeightType;
   private static boolean jerryKB;
   private static boolean autoBlazeDaggers;
   private static boolean fishingMove;
   private static boolean fishingRotate;
   private static int fishingKilling;
   private static boolean fishingTotem;
   private static int fishingRecastDelay;
   private static boolean dropshipNotification;
   private static boolean totemTimer;
   private static boolean funnyFishingAutoHook;
   private static boolean removeArmorGlint;
   private static int funnyFishingAutoKillingDelay;
   private static int timeoutDelay;

   private Config() {
      super(new File(JooonAddons.Companion.getConfigDirectory(), "config.toml"), "§a§l§nJooonAddons V2!", (PropertyCollector)null, (SortingBehavior)(new Config.ConfigSorting()), 4, (DefaultConstructorMarker)null);
   }

   public final boolean getBetterLootShare() {
      return betterLootShare;
   }

   public final void setBetterLootShare(boolean <set-?>) {
      betterLootShare = var1;
   }

   public final boolean getGlowOnMob() {
      return glowOnMob;
   }

   public final void setGlowOnMob(boolean <set-?>) {
      glowOnMob = var1;
   }

   @NotNull
   public final Color getGlowColor() {
      return glowColor;
   }

   public final void setGlowColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      glowColor = var1;
   }

   public final boolean getDisableVisible() {
      return disableVisible;
   }

   public final void setDisableVisible(boolean <set-?>) {
      disableVisible = var1;
   }

   public final int getEspSelector() {
      return espSelector;
   }

   public final void setEspSelector(int <set-?>) {
      espSelector = var1;
   }

   public final boolean getMobNotification() {
      return mobNotification;
   }

   public final void setMobNotification(boolean <set-?>) {
      mobNotification = var1;
   }

   @NotNull
   public final String getCustomESPMobs() {
      return customESPMobs;
   }

   public final void setCustomESPMobs(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      customESPMobs = var1;
   }

   public final boolean getThunderNotification() {
      return thunderNotification;
   }

   public final void setThunderNotification(boolean <set-?>) {
      thunderNotification = var1;
   }

   public final boolean getJawbusNotification() {
      return jawbusNotification;
   }

   public final void setJawbusNotification(boolean <set-?>) {
      jawbusNotification = var1;
   }

   public final boolean getGwSharkNotification() {
      return gwSharkNotification;
   }

   public final void setGwSharkNotification(boolean <set-?>) {
      gwSharkNotification = var1;
   }

   public final boolean getHydraNotification() {
      return hydraNotification;
   }

   public final void setHydraNotification(boolean <set-?>) {
      hydraNotification = var1;
   }

   public final boolean getGrimNotification() {
      return grimNotification;
   }

   public final void setGrimNotification(boolean <set-?>) {
      grimNotification = var1;
   }

   public final boolean getEmpNotification() {
      return empNotification;
   }

   public final void setEmpNotification(boolean <set-?>) {
      empNotification = var1;
   }

   public final boolean getNutterNotification() {
      return nutterNotification;
   }

   public final void setNutterNotification(boolean <set-?>) {
      nutterNotification = var1;
   }

   public final boolean getYetiNotification() {
      return yetiNotification;
   }

   public final void setYetiNotification(boolean <set-?>) {
      yetiNotification = var1;
   }

   public final boolean getEspOnNotifiedMobs() {
      return espOnNotifiedMobs;
   }

   public final void setEspOnNotifiedMobs(boolean <set-?>) {
      espOnNotifiedMobs = var1;
   }

   public final boolean getAutoFriendHi() {
      return autoFriendHi;
   }

   public final void setAutoFriendHi(boolean <set-?>) {
      autoFriendHi = var1;
   }

   @NotNull
   public final String getAutoHiFriends() {
      return autoHiFriends;
   }

   public final void setAutoHiFriends(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      autoHiFriends = var1;
   }

   public final int getAutoFriendHiCooldown() {
      return autoFriendHiCooldown;
   }

   public final void setAutoFriendHiCooldown(int <set-?>) {
      autoFriendHiCooldown = var1;
   }

   public final int getAutoFriendHiType() {
      return autoFriendHiType;
   }

   public final void setAutoFriendHiType(int <set-?>) {
      autoFriendHiType = var1;
   }

   @NotNull
   public final String getAutoHiCustomCommand() {
      return autoHiCustomCommand;
   }

   public final void setAutoHiCustomCommand(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      autoHiCustomCommand = var1;
   }

   public final boolean getAutoGuildHi() {
      return autoGuildHi;
   }

   public final void setAutoGuildHi(boolean <set-?>) {
      autoGuildHi = var1;
   }

   public final int getLastGuildHi() {
      return lastGuildHi;
   }

   public final void setLastGuildHi(int <set-?>) {
      lastGuildHi = var1;
   }

   public final boolean getAutoDojo() {
      return autoDojo;
   }

   public final void setAutoDojo(boolean <set-?>) {
      autoDojo = var1;
   }

   @NotNull
   public final String getAutoGuildHiCustomMessage() {
      return autoGuildHiCustomMessage;
   }

   public final void setAutoGuildHiCustomMessage(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      autoGuildHiCustomMessage = var1;
   }

   public final int getAutoGuildHiFrequency() {
      return autoGuildHiFrequency;
   }

   public final void setAutoGuildHiFrequency(int <set-?>) {
      autoGuildHiFrequency = var1;
   }

   public final boolean getAutoThankYou() {
      return autoThankYou;
   }

   public final void setAutoThankYou(boolean <set-?>) {
      autoThankYou = var1;
   }

   @NotNull
   public final String getThankYouMessage() {
      return thankYouMessage;
   }

   public final void setThankYouMessage(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      thankYouMessage = var1;
   }

   public final boolean getFunnyFishing() {
      return funnyFishing;
   }

   public final void setFunnyFishing(boolean <set-?>) {
      funnyFishing = var1;
   }

   public final int getSpecialTropyMode() {
      return specialTropyMode;
   }

   public final void setSpecialTropyMode(int <set-?>) {
      specialTropyMode = var1;
   }

   public final boolean getFocusOnGoldenFish() {
      return focusOnGoldenFish;
   }

   public final void setFocusOnGoldenFish(boolean <set-?>) {
      focusOnGoldenFish = var1;
   }

   public final boolean getBarnFishingTimer() {
      return barnFishingTimer;
   }

   public final void setBarnFishingTimer(boolean <set-?>) {
      barnFishingTimer = var1;
   }

   public final boolean getDisplayBarnFishingTimerNotification() {
      return displayBarnFishingTimerNotification;
   }

   public final void setDisplayBarnFishingTimerNotification(boolean <set-?>) {
      displayBarnFishingTimerNotification = var1;
   }

   public final int getTimestampOfBarnFishingNotification() {
      return timestampOfBarnFishingNotification;
   }

   public final void setTimestampOfBarnFishingNotification(int <set-?>) {
      timestampOfBarnFishingNotification = var1;
   }

   @NotNull
   public final String getBarnFishingTimerText() {
      return barnFishingTimerText;
   }

   public final void setBarnFishingTimerText(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      barnFishingTimerText = var1;
   }

   public final boolean getFishingTracker() {
      return fishingTracker;
   }

   public final void setFishingTracker(boolean <set-?>) {
      fishingTracker = var1;
   }

   public final int getFishingTrackerType() {
      return fishingTrackerType;
   }

   public final void setFishingTrackerType(int <set-?>) {
      fishingTrackerType = var1;
   }

   public final boolean getFishingTrackerTypeAutoDetect() {
      return fishingTrackerTypeAutoDetect;
   }

   public final void setFishingTrackerTypeAutoDetect(boolean <set-?>) {
      fishingTrackerTypeAutoDetect = var1;
   }

   public final boolean getFishingTrackerMarina() {
      return fishingTrackerMarina;
   }

   public final void setFishingTrackerMarina(boolean <set-?>) {
      fishingTrackerMarina = var1;
   }

   public final boolean getFishingTrackerSpooky() {
      return fishingTrackerSpooky;
   }

   public final void setFishingTrackerSpooky(boolean <set-?>) {
      fishingTrackerSpooky = var1;
   }

   public final boolean getFishingTrackerWinter() {
      return fishingTrackerWinter;
   }

   public final void setFishingTrackerWinter(boolean <set-?>) {
      fishingTrackerWinter = var1;
   }

   public final boolean getFishingTrackerTimeSince() {
      return fishingTrackerTimeSince;
   }

   public final void setFishingTrackerTimeSince(boolean <set-?>) {
      fishingTrackerTimeSince = var1;
   }

   @NotNull
   public final String getHelmetToSwapNameOne() {
      return helmetToSwapNameOne;
   }

   public final void setHelmetToSwapNameOne(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      helmetToSwapNameOne = var1;
   }

   @NotNull
   public final String getHelmetToSwapNameTwo() {
      return helmetToSwapNameTwo;
   }

   public final void setHelmetToSwapNameTwo(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      helmetToSwapNameTwo = var1;
   }

   public final boolean getDragonTimers() {
      return dragonTimers;
   }

   public final void setDragonTimers(boolean <set-?>) {
      dragonTimers = var1;
   }

   public final boolean getStarredMobESP() {
      return starredMobESP;
   }

   public final void setStarredMobESP(boolean <set-?>) {
      starredMobESP = var1;
   }

   @NotNull
   public final Color getStarredMobESPColor() {
      return starredMobESPColor;
   }

   public final void setStarredMobESPColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      starredMobESPColor = var1;
   }

   public final boolean getTerminalWaypoints() {
      return terminalWaypoints;
   }

   public final void setTerminalWaypoints(boolean <set-?>) {
      terminalWaypoints = var1;
   }

   public final boolean getMonolithESP() {
      return monolithESP;
   }

   public final void setMonolithESP(boolean <set-?>) {
      monolithESP = var1;
   }

   public final boolean getFunnyFishingAutoSell() {
      return funnyFishingAutoSell;
   }

   public final void setFunnyFishingAutoSell(boolean <set-?>) {
      funnyFishingAutoSell = var1;
   }

   @NotNull
   public final Color getDeviceBeaconColor() {
      return deviceBeaconColor;
   }

   public final void setDeviceBeaconColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      deviceBeaconColor = var1;
   }

   @NotNull
   public final Color getTerminalBeaconColor() {
      return terminalBeaconColor;
   }

   public final void setTerminalBeaconColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      terminalBeaconColor = var1;
   }

   @NotNull
   public final Color getLeverBeaconColor() {
      return leverBeaconColor;
   }

   public final void setLeverBeaconColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      leverBeaconColor = var1;
   }

   public final boolean getBetterStonk() {
      return betterStonk;
   }

   public final void setBetterStonk(boolean <set-?>) {
      betterStonk = var1;
   }

   public final boolean getBetterStonkShiftOnly() {
      return betterStonkShiftOnly;
   }

   public final void setBetterStonkShiftOnly(boolean <set-?>) {
      betterStonkShiftOnly = var1;
   }

   public final int getStarredESPType() {
      return starredESPType;
   }

   public final void setStarredESPType(int <set-?>) {
      starredESPType = var1;
   }

   public final boolean getAutoCloseChest() {
      return autoCloseChest;
   }

   public final void setAutoCloseChest(boolean <set-?>) {
      autoCloseChest = var1;
   }

   public final boolean getHideDefaultNames() {
      return hideDefaultNames;
   }

   public final void setHideDefaultNames(boolean <set-?>) {
      hideDefaultNames = var1;
   }

   public final boolean getRemoveBlindness() {
      return removeBlindness;
   }

   public final void setRemoveBlindness(boolean <set-?>) {
      removeBlindness = var1;
   }

   public final boolean getAutoExperiments() {
      return autoExperiments;
   }

   public final void setAutoExperiments(boolean <set-?>) {
      autoExperiments = var1;
   }

   public final boolean getAutoCloseExperiments() {
      return autoCloseExperiments;
   }

   public final void setAutoCloseExperiments(boolean <set-?>) {
      autoCloseExperiments = var1;
   }

   public final int getAutoExperimentsDelay() {
      return autoExperimentsDelay;
   }

   public final void setAutoExperimentsDelay(int <set-?>) {
      autoExperimentsDelay = var1;
   }

   public final boolean getMiniESP() {
      return miniESP;
   }

   public final void setMiniESP(boolean <set-?>) {
      miniESP = var1;
   }

   public final boolean getSlayerESP() {
      return slayerESP;
   }

   public final void setSlayerESP(boolean <set-?>) {
      slayerESP = var1;
   }

   public final boolean getBetterFarmingHitboxes() {
      return betterFarmingHitboxes;
   }

   public final void setBetterFarmingHitboxes(boolean <set-?>) {
      betterFarmingHitboxes = var1;
   }

   @NotNull
   public final Color getBossESPColor() {
      return bossESPColor;
   }

   public final void setBossESPColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      bossESPColor = var1;
   }

   @NotNull
   public final Color getWorseMiniColor() {
      return worseMiniColor;
   }

   public final void setWorseMiniColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      worseMiniColor = var1;
   }

   @NotNull
   public final Color getBetterMiniColor() {
      return betterMiniColor;
   }

   public final void setBetterMiniColor(@NotNull Color <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      betterMiniColor = var1;
   }

   public final int getSlayerESPType() {
      return slayerESPType;
   }

   public final void setSlayerESPType(int <set-?>) {
      slayerESPType = var1;
   }

   public final boolean getBossESP() {
      return bossESP;
   }

   public final void setBossESP(boolean <set-?>) {
      bossESP = var1;
   }

   public final boolean getAutoOrb() {
      return autoOrb;
   }

   public final void setAutoOrb(boolean <set-?>) {
      autoOrb = var1;
   }

   public final boolean getStopOpeningNecromancyGUI() {
      return stopOpeningNecromancyGUI;
   }

   public final void setStopOpeningNecromancyGUI(boolean <set-?>) {
      stopOpeningNecromancyGUI = var1;
   }

   public final boolean getAutoDaed() {
      return autoDaed;
   }

   public final void setAutoDaed(boolean <set-?>) {
      autoDaed = var1;
   }

   public final boolean getAutoDaedArmorSwap() {
      return autoDaedArmorSwap;
   }

   public final void setAutoDaedArmorSwap(boolean <set-?>) {
      autoDaedArmorSwap = var1;
   }

   @NotNull
   public final String getAutoDaedPetNameOne() {
      return autoDaedPetNameOne;
   }

   public final void setAutoDaedPetNameOne(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      autoDaedPetNameOne = var1;
   }

   @NotNull
   public final String getAutoDaedPetNameTwo() {
      return autoDaedPetNameTwo;
   }

   public final void setAutoDaedPetNameTwo(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      autoDaedPetNameTwo = var1;
   }

   public final int getDaedSwapHealthType() {
      return daedSwapHealthType;
   }

   public final void setDaedSwapHealthType(int <set-?>) {
      daedSwapHealthType = var1;
   }

   public final float getPercentageHealthDaed() {
      return percentageHealthDaed;
   }

   public final void setPercentageHealthDaed(float <set-?>) {
      percentageHealthDaed = var1;
   }

   @NotNull
   public final String getManualHealthDaed() {
      return manualHealthDaed;
   }

   public final void setManualHealthDaed(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      manualHealthDaed = var1;
   }

   public final boolean getAutoP3P5GhostBlocks() {
      return autoP3P5GhostBlocks;
   }

   public final void setAutoP3P5GhostBlocks(boolean <set-?>) {
      autoP3P5GhostBlocks = var1;
   }

   public final boolean getBonzoMaskTimer() {
      return bonzoMaskTimer;
   }

   public final void setBonzoMaskTimer(boolean <set-?>) {
      bonzoMaskTimer = var1;
   }

   public final boolean getAutoMelody() {
      return autoMelody;
   }

   public final void setAutoMelody(boolean <set-?>) {
      autoMelody = var1;
   }

   public final boolean getSpiritMaskTimer() {
      return spiritMaskTimer;
   }

   public final void setSpiritMaskTimer(boolean <set-?>) {
      spiritMaskTimer = var1;
   }

   public final boolean getAutoBonzoMask() {
      return autoBonzoMask;
   }

   public final void setAutoBonzoMask(boolean <set-?>) {
      autoBonzoMask = var1;
   }

   public final float getAutoBonzoMaskHealth() {
      return autoBonzoMaskHealth;
   }

   public final void setAutoBonzoMaskHealth(float <set-?>) {
      autoBonzoMaskHealth = var1;
   }

   public final boolean getRealisticHeight() {
      return realisticHeight;
   }

   public final void setRealisticHeight(boolean <set-?>) {
      realisticHeight = var1;
   }

   public final int getRealisticHeightType() {
      return realisticHeightType;
   }

   public final void setRealisticHeightType(int <set-?>) {
      realisticHeightType = var1;
   }

   public final boolean getJerryKB() {
      return jerryKB;
   }

   public final void setJerryKB(boolean <set-?>) {
      jerryKB = var1;
   }

   public final boolean getAutoBlazeDaggers() {
      return autoBlazeDaggers;
   }

   public final void setAutoBlazeDaggers(boolean <set-?>) {
      autoBlazeDaggers = var1;
   }

   public final boolean getFishingMove() {
      return fishingMove;
   }

   public final void setFishingMove(boolean <set-?>) {
      fishingMove = var1;
   }

   public final boolean getFishingRotate() {
      return fishingRotate;
   }

   public final void setFishingRotate(boolean <set-?>) {
      fishingRotate = var1;
   }

   public final int getFishingKilling() {
      return fishingKilling;
   }

   public final void setFishingKilling(int <set-?>) {
      fishingKilling = var1;
   }

   public final boolean getFishingTotem() {
      return fishingTotem;
   }

   public final void setFishingTotem(boolean <set-?>) {
      fishingTotem = var1;
   }

   public final int getFishingRecastDelay() {
      return fishingRecastDelay;
   }

   public final void setFishingRecastDelay(int <set-?>) {
      fishingRecastDelay = var1;
   }

   public final boolean getDropshipNotification() {
      return dropshipNotification;
   }

   public final void setDropshipNotification(boolean <set-?>) {
      dropshipNotification = var1;
   }

   public final boolean getTotemTimer() {
      return totemTimer;
   }

   public final void setTotemTimer(boolean <set-?>) {
      totemTimer = var1;
   }

   public final boolean getFunnyFishingAutoHook() {
      return funnyFishingAutoHook;
   }

   public final void setFunnyFishingAutoHook(boolean <set-?>) {
      funnyFishingAutoHook = var1;
   }

   public final boolean getRemoveArmorGlint() {
      return removeArmorGlint;
   }

   public final void setRemoveArmorGlint(boolean <set-?>) {
      removeArmorGlint = var1;
   }

   public final int getFunnyFishingAutoKillingDelay() {
      return funnyFishingAutoKillingDelay;
   }

   public final void setFunnyFishingAutoKillingDelay(int <set-?>) {
      funnyFishingAutoKillingDelay = var1;
   }

   public final int getTimeoutDelay() {
      return timeoutDelay;
   }

   public final void setTimeoutDelay(int <set-?>) {
      timeoutDelay = var1;
   }

   private final void openYouTubeLink() {
      try {
         String url = "https://www.youtube.com/@jooonlol";
         Desktop desktop = Desktop.getDesktop();
         desktop.browse(new URI(url));
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   // $FF: synthetic method
   public static final void access$openYouTubeLink(Config $this) {
      $this.openYouTubeLink();
   }

   static {
      Color var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      glowColor = var10000;
      espSelector = 2;
      customESPMobs = "MobOne, MobTwo";
      autoHiFriends = "PlayerOne, PlayerTwo";
      autoFriendHiCooldown = 3;
      autoHiCustomCommand = "/msg [IGN] Hi [IGN]!";
      autoGuildHiCustomMessage = "Hi Guild!";
      thankYouMessage = "Thank you [IGN]! <3";
      timestampOfBarnFishingNotification = 240;
      barnFishingTimerText = "Kill";
      helmetToSwapNameOne = "Rabbit Hat";
      helmetToSwapNameTwo = "Bonzo's Mask";
      var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      starredMobESPColor = var10000;
      var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      deviceBeaconColor = var10000;
      var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      terminalBeaconColor = var10000;
      var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      leverBeaconColor = var10000;
      starredESPType = 1;
      autoExperimentsDelay = 200;
      var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      bossESPColor = var10000;
      var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      worseMiniColor = var10000;
      var10000 = Color.BLUE;
      Intrinsics.checkNotNullExpressionValue(var10000, "BLUE");
      betterMiniColor = var10000;
      slayerESPType = 2;
      autoDaedPetNameOne = "";
      autoDaedPetNameTwo = "";
      manualHealthDaed = "150k";
      autoBonzoMaskHealth = 0.3F;
      realisticHeight = true;
      fishingRecastDelay = 275;
      funnyFishingAutoKillingDelay = 100;
      timeoutDelay = 200;
      INSTANCE.category("Auto Features", (Function1)null.INSTANCE);
      INSTANCE.category("Fishing", (Function1)null.INSTANCE);
      INSTANCE.category("Dungeons", (Function1)null.INSTANCE);
      INSTANCE.category("Auto Features", (Function1)null.INSTANCE);
      INSTANCE.markDirty();
      INSTANCE.writeData();
   }

   @Metadata(
      mv = {1, 8, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0006\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\b0\u0007j\n\u0012\u0006\b\u0000\u0012\u00020\b`\tH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"},
      d2 = {"Lme/jooon/JooonAddons/config/Config$ConfigSorting;", "Lgg/essential/vigilance/data/SortingBehavior;", "()V", "categories", "", "", "getCategoryComparator", "Ljava/util/Comparator;", "Lgg/essential/vigilance/data/Category;", "Lkotlin/Comparator;", "JooonAddons"}
   )
   private static final class ConfigSorting extends SortingBehavior {
      @NotNull
      private final List<String> categories;

      public ConfigSorting() {
         String[] var1 = new String[]{"Features", "Fishing", "Dungeons"};
         this.categories = CollectionsKt.listOf(var1);
      }

      @NotNull
      public Comparator<? super Category> getCategoryComparator() {
         Comparator var10000 = Comparator.comparingInt(Config.ConfigSorting::getCategoryComparator$lambda$0);
         Intrinsics.checkNotNullExpressionValue(var10000, "override fun getCategory….indexOf(category.name) }");
         return var10000;
      }

      private static final int getCategoryComparator$lambda$0(Function1 $tmp0, Object p0) {
         Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
         return ((Number)$tmp0.invoke(p0)).intValue();
      }
   }
}
