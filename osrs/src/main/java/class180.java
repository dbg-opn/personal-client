import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("gu")
public class class180 extends class183 {
	@ObfuscatedName("ab")
	static int field1467;
	@ObfuscatedName("ag")
	@Export("Interpreter_stringStackSize")
	static int Interpreter_stringStackSize;
	@ObfuscatedName("ac")
	int field1469;
	// $FF: synthetic field
	@ObfuscatedSignature(
		descriptor = "Lgr;"
	)
	final class172 this$0;

	@ObfuscatedSignature(
		descriptor = "(Lgr;Ljava/lang/String;I)V"
	)
	class180(class172 var1, String var2, int var3) {
		super(var1, var2);
		this.this$0 = var1;
		this.field1469 = var3;
	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "-1007456210"
	)
	public int vmethod3379() {
		return 0;
	}

	@ObfuscatedName("ah")
	@ObfuscatedSignature(
		descriptor = "(S)I",
		garbageValue = "-19272"
	)
	@Export("vmethod3378")
	public int vmethod3378() {
		return this.field1469;
	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(Luy;I)V",
		garbageValue = "2145499670"
	)
	@Export("updatePlayer")
	static final void updatePlayer(PacketBuffer var0) {
		var0.importIndex();
		int var1 = Client.localPlayerIndex;
		Player var2 = TextureProvider.localPlayer = Client.players[var1] = new Player();
		var2.index = var1;
		int var3 = var0.readBits(30);
		byte var4 = (byte)(var3 >> 28);
		int var5 = var3 >> 14 & 16383;
		int var6 = var3 & 16383;
		var2.pathX[0] = var5 - class20.baseX;
		var2.x = (var2.pathX[0] << 7) + (var2.transformedSize() << 6);
		var2.pathY[0] = var6 - class19.baseY;
		var2.y = (var2.pathY[0] << 7) + (var2.transformedSize() << 6);
		class87.Client_plane = var2.plane = var4;
		if (Players.cachedAppearanceBuffer[var1] != null) {
			var2.read(Players.cachedAppearanceBuffer[var1]);
		}

		Players.Players_count = 0;
		Players.Players_indices[++Players.Players_count - 1] = var1;
		Players.activityFlags[var1] = 0;
		Players.Players_emptyIdxCount = 0;

		for (int var7 = 1; var7 < 2048; ++var7) {
			if (var1 != var7) {
				int var8 = var0.readBits(18);
				int var9 = var8 >> 16;
				int var10 = var8 >> 8 & 597;
				int var11 = var8 & 597;
				Players.Players_regions[var7] = (var10 << 14) + var11 + (var9 << 28);
				Players.Players_orientations[var7] = 0;
				Players.Players_targetIndices[var7] = -1;
				Players.Players_emptyIndices[++Players.Players_emptyIdxCount - 1] = var7;
				Players.activityFlags[var7] = 0;
			}
		}

		var0.exportIndex();
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(IB)Lix;",
		garbageValue = "3"
	)
	@Export("getParamDefinition")
	public static ParamComposition getParamDefinition(int var0) {
		ParamComposition var1 = (ParamComposition)ParamComposition.ParamDefinition_cached.get((long)var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = ParamComposition.ParamDefinition_archive.takeFile(11, var0);
			var1 = new ParamComposition();
			if (var2 != null) {
				var1.decode(new Buffer(var2));
			}

			var1.postDecode();
			ParamComposition.ParamDefinition_cached.put(var1, (long)var0);
			return var1;
		}
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(IB)Lug;",
		garbageValue = "-114"
	)
	@Export("getDbRowType")
	public static DbRowType getDbRowType(int var0) {
		DbRowType var1 = (DbRowType)DbRowType.DBRowType_cache.get((long)var0);
		if (var1 != null) {
			return var1;
		} else {
			byte[] var2 = DbRowType.field4144.takeFile(38, var0);
			var1 = new DbRowType();
			if (var2 != null) {
				var1.method2543(new Buffer(var2));
			}

			var1.method2546();
			DbRowType.DBRowType_cache.put(var1, (long)var0);
			return var1;
		}
	}

	@ObfuscatedName("kp")
	@ObfuscatedSignature(
		descriptor = "(Ldj;IILip;I)V",
		garbageValue = "-537634927"
	)
	static final void method933(Player var0, int var1, int var2, MoveSpeed var3) {
		int var4 = var0.pathX[0];
		int var5 = var0.pathY[0];
		int var6 = var0.transformedSize();
		if (var4 >= var6 && var4 < 104 - var6 && var5 >= var6 && var5 < 104 - var6) {
			if (var1 >= var6 && var1 < 104 - var6 && var2 >= var6 && var2 < 104 - var6) {
				class232 var7 = Client.field651;
				int var10 = var0.transformedSize();
				Client.field650.approxDestinationX = var1;
				Client.field650.approxDestinationY = var2;
				Client.field650.approxDestinationSizeX = 1;
				Client.field650.approxDestinationSizeY = 1;
				ApproximateRouteStrategy var11 = Client.field650;
				int var12 = var7.method1190(var4, var5, var10, var11, Client.collisionMaps[var0.plane], true, Client.field592, Client.field593);
				if (var12 >= 1) {
					for (int var13 = 0; var13 < var12 - 1; ++var13) {
						var0.method503(Client.field592[var13], Client.field593[var13], var3);
					}

				}
			}
		}
	}

	@ObfuscatedName("oo")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "505958374"
	)
	static void method934(int var0) {
		Client.oculusOrbState = var0;
	}
}