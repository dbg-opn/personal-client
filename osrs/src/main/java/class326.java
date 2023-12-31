import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

import java.util.PriorityQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

@ObfuscatedName("ml")
class class326 implements Callable {
	@ObfuscatedName("ax")
	@Export("SpriteBuffer_xOffsets")
	public static int[] SpriteBuffer_xOffsets;
	// $FF: synthetic field
	@ObfuscatedSignature(
		descriptor = "Lmt;"
	)
	@Export("this$0")
	@ObfuscatedName("this$0")
	final MidiPcmStream this$0;
	// $FF: synthetic field
	@Export("val$cancelled")
	@ObfuscatedName("val$cancelled")
	final AtomicBoolean val$cancelled;

	@ObfuscatedSignature(
		descriptor = "(Lmt;Ljava/util/concurrent/atomic/AtomicBoolean;)V"
	)
	class326(MidiPcmStream var1, AtomicBoolean var2) {
		this.this$0 = var1;
		this.val$cancelled = var2;
	}

	@Export("call")
	@ObfuscatedName("call")
	public Object call() {
		PriorityQueue var1 = this.this$0.field2825;

		while (true) {
			class323 var2 = null;
			synchronized(var1) {
				if (var1.isEmpty() || this.val$cancelled.get()) {
					return null;
				}

				var2 = (class323)var1.remove();
			}

			var2.field2843.method299();
		}
	}
}