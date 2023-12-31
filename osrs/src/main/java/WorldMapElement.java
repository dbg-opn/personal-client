import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("hp")
@Implements("WorldMapElement")
public class WorldMapElement extends DualNode {
	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "Lom;"
	)
	@Export("WorldMapElement_archive")
	public static AbstractArchive WorldMapElement_archive;
	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "[Lhp;"
	)
	@Export("WorldMapElement_cached")
	public static WorldMapElement[] WorldMapElement_cached;
	@ObfuscatedName("ax")
	@ObfuscatedSignature(
		descriptor = "Llr;"
	)
	@Export("HitSplatDefinition_cached2")
	static EvictingDualNodeHashTable HitSplatDefinition_cached2;
	@ObfuscatedName("ao")
	@Export("objectId")
	public final int objectId;
	@ObfuscatedName("ah")
	@Export("sprite1")
	public int sprite1;
	@ObfuscatedName("ar")
	@Export("sprite2")
	int sprite2;
	@ObfuscatedName("ab")
	@Export("name")
	public String name;
	@ObfuscatedName("am")
	public int field1523;
	@ObfuscatedName("av")
	@Export("textSize")
	public int textSize;
	@ObfuscatedName("aa")
	public boolean field1517;
	@ObfuscatedName("ap")
	public boolean field1518;
	@ObfuscatedName("ay")
	@Export("menuActions")
	public String[] menuActions;
	@ObfuscatedName("as")
	@Export("menuTargetName")
	public String menuTargetName;
	@ObfuscatedName("aj")
	int[] field1515;
	@ObfuscatedName("an")
	int field1512;
	@ObfuscatedName("au")
	int field1514;
	@ObfuscatedName("ai")
	int field1511;
	@ObfuscatedName("ae")
	int field1510;
	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "Lhb;"
	)
	@Export("horizontalAlignment")
	public HorizontalAlignment horizontalAlignment;
	@ObfuscatedName("aq")
	@ObfuscatedSignature(
		descriptor = "Lhr;"
	)
	@Export("verticalAlignment")
	public VerticalAlignment verticalAlignment;
	@ObfuscatedName("az")
	int[] field1516;
	@ObfuscatedName("at")
	byte[] field1509;
	@ObfuscatedName("af")
	@Export("category")
	public int category;

	static {
		HitSplatDefinition_cached2 = new EvictingDualNodeHashTable(256);
	}

	public WorldMapElement(int var1) {
		this.sprite1 = -1;
		this.sprite2 = -1;
		this.textSize = 0;
		this.field1517 = true;
		this.field1518 = false;
		this.menuActions = new String[5];
		this.field1512 = Integer.MAX_VALUE;
		this.field1514 = Integer.MAX_VALUE;
		this.field1511 = Integer.MIN_VALUE;
		this.field1510 = Integer.MIN_VALUE;
		this.horizontalAlignment = HorizontalAlignment.HorizontalAlignment_centered;
		this.verticalAlignment = VerticalAlignment.VerticalAlignment_centered;
		this.category = -1;
		this.objectId = var1;
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(Lul;B)V",
		garbageValue = "7"
	)
	@Export("decode")
	public void decode(Buffer var1) {
		while (true) {
			int var2 = var1.readUnsignedByte();
			if (var2 == 0) {
				return;
			}

			this.decodeNext(var1, var2);
		}
	}

	@ObfuscatedName("ak")
	@ObfuscatedSignature(
		descriptor = "(Lul;IB)V",
		garbageValue = "-63"
	)
	@Export("decodeNext")
	void decodeNext(Buffer var1, int var2) {
		if (var2 == 1) {
			this.sprite1 = var1.readNullableLargeSmart();
		} else if (var2 == 2) {
			this.sprite2 = var1.readNullableLargeSmart();
		} else if (var2 == 3) {
			this.name = var1.readStringCp1252NullTerminated();
		} else if (var2 == 4) {
			this.field1523 = var1.readMedium();
		} else if (var2 == 5) {
			var1.readMedium();
		} else if (var2 == 6) {
			this.textSize = var1.readUnsignedByte();
		} else {
			int var3;
			if (var2 == 7) {
				var3 = var1.readUnsignedByte();
				if ((var3 & 1) == 0) {
					this.field1517 = false;
				}

				if ((var3 & 2) == 2) {
					this.field1518 = true;
				}
			} else if (var2 == 8) {
				var1.readUnsignedByte();
			} else if (var2 >= 10 && var2 <= 14) {
				this.menuActions[var2 - 10] = var1.readStringCp1252NullTerminated();
			} else if (var2 == 15) {
				var3 = var1.readUnsignedByte();
				this.field1515 = new int[var3 * 2];

				int var4;
				for (var4 = 0; var4 < var3 * 2; ++var4) {
					this.field1515[var4] = var1.readShort();
				}

				var1.readInt();
				var4 = var1.readUnsignedByte();
				this.field1516 = new int[var4];

				int var5;
				for (var5 = 0; var5 < this.field1516.length; ++var5) {
					this.field1516[var5] = var1.readInt();
				}

				this.field1509 = new byte[var3];

				for (var5 = 0; var5 < var3; ++var5) {
					this.field1509[var5] = var1.readByte();
				}
			} else if (var2 != 16) {
				if (var2 == 17) {
					this.menuTargetName = var1.readStringCp1252NullTerminated();
				} else if (var2 == 18) {
					var1.readNullableLargeSmart();
				} else if (var2 == 19) {
					this.category = var1.readUnsignedShort();
				} else if (var2 == 21) {
					var1.readInt();
				} else if (var2 == 22) {
					var1.readInt();
				} else if (var2 == 23) {
					var1.readUnsignedByte();
					var1.readUnsignedByte();
					var1.readUnsignedByte();
				} else if (var2 == 24) {
					var1.readShort();
					var1.readShort();
				} else if (var2 == 25) {
					var1.readNullableLargeSmart();
				} else if (var2 == 28) {
					var1.readUnsignedByte();
				} else if (var2 == 29) {
					this.horizontalAlignment = (HorizontalAlignment)SequenceDefinition.findEnumerated(class202.method1003(), var1.readUnsignedByte());
				} else if (var2 == 30) {
					VerticalAlignment[] var6 = new VerticalAlignment[]{VerticalAlignment.VerticalAlignment_centered, VerticalAlignment.field1643, VerticalAlignment.field1644};
					this.verticalAlignment = (VerticalAlignment)SequenceDefinition.findEnumerated(var6, var1.readUnsignedByte());
				}
			}
		}

	}

	@ObfuscatedName("ax")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "-4"
	)
	public void method967() {
		if (this.field1515 != null) {
			for (int var1 = 0; var1 < this.field1515.length; var1 += 2) {
				if (this.field1515[var1] < this.field1512) {
					this.field1512 = this.field1515[var1];
				} else if (this.field1515[var1] > this.field1511) {
					this.field1511 = this.field1515[var1];
				}

				if (this.field1515[var1 + 1] < this.field1514) {
					this.field1514 = this.field1515[var1 + 1];
				} else if (this.field1515[var1 + 1] > this.field1510) {
					this.field1510 = this.field1515[var1 + 1];
				}
			}
		}

	}

	@ObfuscatedName("ao")
	@ObfuscatedSignature(
		descriptor = "(ZI)Lud;",
		garbageValue = "794347545"
	)
	@Export("getSpriteBool")
	public SpritePixels getSpriteBool(boolean var1) {
		int var2 = this.sprite1 * 1563781855 * -155660001;
		return this.getSprite(var2);
	}

	@ObfuscatedName("ah")
	@ObfuscatedSignature(
		descriptor = "(IB)Lud;",
		garbageValue = "-97"
	)
	@Export("getSprite")
	SpritePixels getSprite(int var1) {
		if (var1 < 0) {
			return null;
		} else {
			SpritePixels var2 = (SpritePixels) HitSplatDefinition_cached2.get((long)var1);
			if (var2 != null) {
				return var2;
			} else {
				var2 = class47.SpriteBuffer_getSprite(WorldMapElement_archive, var1, 0);
				if (var2 != null) {
					HitSplatDefinition_cached2.put(var2, (long)var1);
				}

				return var2;
			}
		}
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(B)I",
		garbageValue = "1"
	)
	@Export("getObjectId")
	public int getObjectId() {
		return this.objectId;
	}

	@ObfuscatedName("by")
	@ObfuscatedSignature(
		descriptor = "(ILds;ZI)I",
		garbageValue = "2010949873"
	)
	static int method971(int var0, Script var1, boolean var2) {
		int var3;
		if (var0 == 3500) {
			var3 = Interpreter.Interpreter_intStack[--Interpreter.Interpreter_intStackSize];
			Interpreter.Interpreter_intStack[++Interpreter.Interpreter_intStackSize - 1] = Client.keyHandlerInstance.getKeyPressed(var3) ? 1 : 0;
			return 1;
		} else if (var0 == 3501) {
			var3 = Interpreter.Interpreter_intStack[--Interpreter.Interpreter_intStackSize];
			Interpreter.Interpreter_intStack[++Interpreter.Interpreter_intStackSize - 1] = Client.keyHandlerInstance.method1149(var3) ? 1 : 0;
			return 1;
		} else if (var0 == 3502) {
			var3 = Interpreter.Interpreter_intStack[--Interpreter.Interpreter_intStackSize];
			Interpreter.Interpreter_intStack[++Interpreter.Interpreter_intStackSize - 1] = Client.keyHandlerInstance.method1151(var3) ? 1 : 0;
			return 1;
		} else {
			return 2;
		}
	}
}