import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ka")
@Implements("WorldMapLabelSize")
public class WorldMapLabelSize {
	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "Lka;"
	)
	@Export("WorldMapLabelSize_small")
	public static final WorldMapLabelSize WorldMapLabelSize_small;
	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "Lka;"
	)
	@Export("WorldMapLabelSize_medium")
	public static final WorldMapLabelSize WorldMapLabelSize_medium;
	@ObfuscatedName("ak")
	@ObfuscatedSignature(
		descriptor = "Lka;"
	)
	@Export("WorldMapLabelSize_large")
	public static final WorldMapLabelSize WorldMapLabelSize_large;
	@ObfuscatedName("cy")
	@ObfuscatedSignature(
		descriptor = "Loa;"
	)
	@Export("clientLanguage")
	static Language clientLanguage;
	@ObfuscatedName("ax")
	final int field2336;
	@ObfuscatedName("ao")
	final int field2335;
	@ObfuscatedName("ah")
	final int field2334;

	static {
		WorldMapLabelSize_small = new WorldMapLabelSize(1, 0, 4);
		WorldMapLabelSize_medium = new WorldMapLabelSize(0, 1, 2);
		WorldMapLabelSize_large = new WorldMapLabelSize(2, 2, 0);
	}

	WorldMapLabelSize(int var1, int var2, int var3) {
		this.field2336 = var1;
		this.field2335 = var2;
		this.field2334 = var3;
	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(FI)Z",
		garbageValue = "-198292177"
	)
	boolean method1433(float var1) {
		return var1 >= (float)this.field2334;
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(IB)Lka;",
		garbageValue = "108"
	)
	static WorldMapLabelSize method1434(int var0) {
		WorldMapLabelSize[] var1 = new WorldMapLabelSize[]{WorldMapLabelSize_medium, WorldMapLabelSize_large, WorldMapLabelSize_small};
		WorldMapLabelSize[] var2 = var1;

		for (int var3 = 0; var3 < var2.length; ++var3) {
			WorldMapLabelSize var4 = var2[var3];
			if (var0 == var4.field2335) {
				return var4;
			}
		}

		return null;
	}

	@ObfuscatedName("ax")
	@ObfuscatedSignature(
		descriptor = "(II)I",
		garbageValue = "-1105487530"
	)
	public static int method1435(int var0) {
		return class479.field3938[var0 & 16383];
	}

	@ObfuscatedName("ap")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "1216862596"
	)
	static final int method1436() {
		return ViewportMouse.ViewportMouse_y;
	}

	@ObfuscatedName("je")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "-13"
	)
	static boolean method1437() {
		return (Client.drawPlayerNames & 8) != 0;
	}
}