import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

@ObfuscatedName("sq")
public class class478 implements class476 {
	@ObfuscatedName("ac")
	final Map field3937;

	public class478(Map var1) {
		this.field3937 = var1;
	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(B)Lst;",
		garbageValue = "1"
	)
	@Export("vmethod2213")
	public class475 vmethod2213() {
		return null;
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(B)[B",
		garbageValue = "17"
	)
	@Export("vmethod7797")
	public byte[] vmethod7797() throws UnsupportedEncodingException {
		return this.method2383().getBytes("UTF-8");
	}

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "(I)Ljava/lang/String;",
		garbageValue = "1386536231"
	)
	public String method2383() throws UnsupportedEncodingException {
		StringBuilder var1 = new StringBuilder();
		Iterator var2 = this.field3937.entrySet().iterator();

		while (var2.hasNext()) {
			Entry var3 = (Entry)var2.next();
			String var4 = URLEncoder.encode((String)var3.getKey(), "UTF-8");
			String var5 = URLEncoder.encode((String)var3.getValue(), "UTF-8");
			var1.append(var4).append("=").append(var5).append("&");
		}

		if (var1.length() == 0) {
			return "";
		} else {
			var1.deleteCharAt(var1.length() - 1);
			var1.insert(0, "?");
			return var1.toString();
		}
	}

	@ObfuscatedName("bx")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;I)I",
		garbageValue = "1087819617"
	)
	@Export("stringCp1252NullTerminatedByteSize")
	public static int stringCp1252NullTerminatedByteSize(String var0) {
		return var0.length() + 1;
	}

	@ObfuscatedName("lm")
	@ObfuscatedSignature(
		descriptor = "(IIIII)V",
		garbageValue = "2037077846"
	)
	static void method2385(int var0, int var1, int var2, int var3) {
		Widget var4 = class33.widgetDefinition.getWidgetChild(var0, var1);
		if (var4 != null && var4.onTargetEnter != null) {
			ScriptEvent var5 = new ScriptEvent();
			var5.widget = var4;
			var5.args = var4.onTargetEnter;
			Interpreter.runScriptEvent(var5);
		}

		Client.selectedSpellItemId = var3;
		Client.isSpellSelected = true;
		JagexCache.selectedSpellWidget = var0;
		Client.selectedSpellChildIndex = var1;
		class31.field82 = var2;
		ClanChannelMember.invalidateWidget(var4);
	}
}