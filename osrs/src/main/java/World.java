import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;
import netscape.js.JSObject;

import java.util.Date;


@ObfuscatedName("cv")
@Implements("World")
public class World {
	@ObfuscatedName("ax")
	@Export("Tiles_underlays")
	static short[][][] Tiles_underlays;
	@ObfuscatedName("ah")
	@Export("World_count")
	static int World_count;
	@ObfuscatedName("ar")
	@Export("World_listCount")
	static int World_listCount;
	@ObfuscatedName("ab")
	@Export("World_sortOption2")
	static int[] World_sortOption2;
	@ObfuscatedName("am")
	@Export("World_sortOption1")
	static int[] World_sortOption1;
	@ObfuscatedName("ag")
	@Export("id")
	int id;
	@ObfuscatedName("aa")
	@Export("properties")
	int properties;
	@ObfuscatedName("ap")
	@Export("population")
	int population;
	@ObfuscatedName("ay")
	@Export("host")
	String host;
	@ObfuscatedName("as")
	@Export("activity")
	String activity;
	@ObfuscatedName("aj")
	@Export("location")
	int location;
	@ObfuscatedName("an")
	@Export("index")
	int index;
	@ObfuscatedName("au")
	String field680;

	static {
		World_count = 0;
		World_listCount = 0;
		World_sortOption2 = new int[]{1, 1, 1, 1};
		World_sortOption1 = new int[]{0, 1, 2, 3};
	}

	World() {
	}

	@ObfuscatedName("av")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "844978616"
	)
	@Export("isMembersOnly")
	boolean isMembersOnly() {
		return (class525.field4150.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "-249260285"
	)
	@Export("isDeadman")
	boolean isDeadman() {
		return (class525.field4156.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("aa")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "1975874921"
	)
	boolean method390() {
		return (class525.field4154.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("ap")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "32"
	)
	@Export("isPvp")
	boolean isPvp() {
		return (class525.field4153.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "0"
	)
	boolean method392() {
		return (class525.field4157.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "42"
	)
	boolean method393() {
		return (class525.field4158.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("aj")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "-1488383834"
	)
	@Export("isBeta")
	boolean isBeta() {
		return (class525.field4151.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("an")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "-1132976200"
	)
	boolean method395() {
		return (class525.field4159.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("au")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "-43"
	)
	boolean method396() {
		return (class525.field4155.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("ai")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "165086370"
	)
	boolean method397() {
		return (class525.field4160.rsOrdinal() & this.properties) != 0;
	}

	@ObfuscatedName("ol")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;S)V",
		garbageValue = "-14910"
	)
	static void method398(String var0) {
		HealthBarDefinition.param9 = var0;

		try {
			String var1 = class159.client.getParameter(Integer.toString(18));
			String var2 = class159.client.getParameter(Integer.toString(13));
			String var3 = var1 + "settings=" + var0 + "; version=1; path=/; domain=" + var2;
			String var5;
			if (var0.length() == 0) {
				var3 = var3 + "; Expires=Thu, 01-Jan-1970 00:00:00 GMT; Max-Age=0";
			} else {
				String var4 = var3 + "; Expires=";
				long var6 = GrandExchangeOfferTotalQuantityComparator.method1971() + 94608000000L;
				Calendar.Calendar_calendar.setTime(new Date(var6));
				int var8 = Calendar.Calendar_calendar.get(7);
				int var9 = Calendar.Calendar_calendar.get(5);
				int var10 = Calendar.Calendar_calendar.get(2);
				int var11 = Calendar.Calendar_calendar.get(1);
				int var12 = Calendar.Calendar_calendar.get(11);
				int var13 = Calendar.Calendar_calendar.get(12);
				int var14 = Calendar.Calendar_calendar.get(13);
				var5 = Calendar.DAYS_OF_THE_WEEK[var8 - 1] + ", " + var9 / 10 + var9 % 10 + "-" + Calendar.MONTH_NAMES_ENGLISH_GERMAN[0][var10] + "-" + var11 + " " + var12 / 10 + var12 % 10 + ":" + var13 / 10 + var13 % 10 + ":" + var14 / 10 + var14 % 10 + " GMT";
				var3 = var4 + var5 + "; Max-Age=" + 94608000L;
			}

			Client var16 = class159.client;
			var5 = "document.cookie=\"" + var3 + "\"";
			JSObject.getWindow(var16).eval(var5);
		} catch (Throwable var15) {
		}

	}
}