import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;

@ObfuscatedName("ut")
public class class538 {
	@ObfuscatedName("ac")
	public final int field4243;
	@ObfuscatedName("al")
	public Object field4242;

	public class538(int var1) {
		this.field4243 = var1;
	}

	public class538(int var1, Object var2) {
		this.field4243 = var1;
		this.field4242 = var2;
	}

	@Export("hashCode")
	@ObfuscatedName("hashCode")
	public int hashCode() {
		return super.hashCode();
	}

	@Export("equals")
	@ObfuscatedName("equals")
	public boolean equals(Object var1) {
		if (!(var1 instanceof class538)) {
			return false;
		} else {
			class538 var2 = (class538)var1;
			if (var2.field4242 == null && this.field4242 != null) {
				return false;
			} else if (this.field4242 == null && var2.field4242 != null) {
				return false;
			} else {
				return var2.field4243 == this.field4243 && var2.field4242.equals(this.field4242);
			}
		}
	}

	@ObfuscatedName("ac")
	public static final int method2658(double var0, double var2, double var4) {
		double var6 = var4;
		double var8 = var4;
		double var10 = var4;
		if (0.0D != var2) {
			double var12;
			if (var4 < 0.5D) {
				var12 = var4 * (1.0D + var2);
			} else {
				var12 = var2 + var4 - var4 * var2;
			}

			double var14 = var4 * 2.0D - var12;
			double var16 = var0 + 0.3333333333333333D;
			if (var16 > 1.0D) {
				--var16;
			}

			double var20 = var0 - 0.3333333333333333D;
			if (var20 < 0.0D) {
				++var20;
			}

			if (6.0D * var16 < 1.0D) {
				var6 = var16 * 6.0D * (var12 - var14) + var14;
			} else if (2.0D * var16 < 1.0D) {
				var6 = var12;
			} else if (var16 * 3.0D < 2.0D) {
				var6 = (0.6666666666666666D - var16) * (var12 - var14) * 6.0D + var14;
			} else {
				var6 = var14;
			}

			if (6.0D * var0 < 1.0D) {
				var8 = var0 * (var12 - var14) * 6.0D + var14;
			} else if (var0 * 2.0D < 1.0D) {
				var8 = var12;
			} else if (var0 * 3.0D < 2.0D) {
				var8 = var14 + (0.6666666666666666D - var0) * (var12 - var14) * 6.0D;
			} else {
				var8 = var14;
			}

			if (6.0D * var20 < 1.0D) {
				var10 = var14 + var20 * 6.0D * (var12 - var14);
			} else if (2.0D * var20 < 1.0D) {
				var10 = var12;
			} else if (var20 * 3.0D < 2.0D) {
				var10 = var14 + (var12 - var14) * (0.6666666666666666D - var20) * 6.0D;
			} else {
				var10 = var14;
			}
		}

		int var22 = (int)(256.0D * var6);
		int var13 = (int)(var8 * 256.0D);
		int var23 = (int)(var10 * 256.0D);
		int var15 = var23 + (var13 << 8) + (var22 << 16);
		return var15;
	}
}