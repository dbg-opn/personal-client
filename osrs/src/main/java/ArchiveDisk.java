import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

import java.io.EOFException;
import java.io.IOException;

@ObfuscatedName("rw")
@Implements("ArchiveDisk")
public final class ArchiveDisk {
	@ObfuscatedName("ac")
	@Export("ArchiveDisk_buffer")
	static byte[] ArchiveDisk_buffer;
	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "Ltl;"
	)
	@Export("datFile")
	BufferedFile datFile;
	@ObfuscatedName("ak")
	@ObfuscatedSignature(
		descriptor = "Ltl;"
	)
	@Export("idxFile")
	BufferedFile idxFile;
	@ObfuscatedName("ax")
	@Export("archive")
	int archive;
	@ObfuscatedName("ao")
	@Export("maxEntrySize")
	int maxEntrySize;

	static {
		ArchiveDisk_buffer = new byte[520];
	}

	@ObfuscatedSignature(
		descriptor = "(ILtl;Ltl;I)V"
	)
	public ArchiveDisk(int var1, BufferedFile var2, BufferedFile var3, int var4) {
		this.datFile = null;
		this.idxFile = null;
		this.maxEntrySize = 65000;
		this.archive = var1;
		this.datFile = var2;
		this.idxFile = var3;
		this.maxEntrySize = var4;
	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(II)[B",
		garbageValue = "896649257"
	)
	@Export("read")
	public byte[] read(int var1) {
		synchronized(this.datFile) {
			Object var10000;
			try {
				if (this.idxFile.length() < (long)(var1 * 6 + 6)) {
					var10000 = null;
					return (byte[])var10000;
				}

				this.idxFile.seek((long)(var1 * 6));
				this.idxFile.read(ArchiveDisk_buffer, 0, 6);
				int var3 = ((ArchiveDisk_buffer[0] & 255) << 16) + (ArchiveDisk_buffer[2] & 255) + ((ArchiveDisk_buffer[1] & 255) << 8);
				int var4 = (ArchiveDisk_buffer[5] & 255) + ((ArchiveDisk_buffer[3] & 255) << 16) + ((ArchiveDisk_buffer[4] & 255) << 8);
				if (var3 < 0 || var3 > this.maxEntrySize) {
					var10000 = null;
					return (byte[])var10000;
				}

				if (var4 > 0 && (long)var4 <= this.datFile.length() / 520L) {
					byte[] var5 = new byte[var3];
					int var6 = 0;
					int var7 = 0;

					while (var6 < var3) {
						if (var4 == 0) {
							var10000 = null;
							return (byte[])var10000;
						}

						this.datFile.seek((long)var4 * 520L);
						int var8 = var3 - var6;
						int var9;
						int var10;
						int var11;
						int var12;
						byte var13;
						if (var1 > 65535) {
							if (var8 > 510) {
								var8 = 510;
							}

							var13 = 10;
							this.datFile.read(ArchiveDisk_buffer, 0, var8 + var13);
							var9 = ((ArchiveDisk_buffer[1] & 255) << 16) + ((ArchiveDisk_buffer[0] & 255) << 24) + (ArchiveDisk_buffer[3] & 255) + ((ArchiveDisk_buffer[2] & 255) << 8);
							var10 = (ArchiveDisk_buffer[5] & 255) + ((ArchiveDisk_buffer[4] & 255) << 8);
							var11 = (ArchiveDisk_buffer[8] & 255) + ((ArchiveDisk_buffer[7] & 255) << 8) + ((ArchiveDisk_buffer[6] & 255) << 16);
							var12 = ArchiveDisk_buffer[9] & 255;
						} else {
							if (var8 > 512) {
								var8 = 512;
							}

							var13 = 8;
							this.datFile.read(ArchiveDisk_buffer, 0, var8 + var13);
							var9 = (ArchiveDisk_buffer[1] & 255) + ((ArchiveDisk_buffer[0] & 255) << 8);
							var10 = (ArchiveDisk_buffer[3] & 255) + ((ArchiveDisk_buffer[2] & 255) << 8);
							var11 = ((ArchiveDisk_buffer[5] & 255) << 8) + ((ArchiveDisk_buffer[4] & 255) << 16) + (ArchiveDisk_buffer[6] & 255);
							var12 = ArchiveDisk_buffer[7] & 255;
						}

						if (var9 == var1 && var7 == var10 && var12 == this.archive) {
							if (var11 >= 0 && (long)var11 <= this.datFile.length() / 520L) {
								int var14 = var13 + var8;

								for (int var15 = var13; var15 < var14; ++var15) {
									var5[var6++] = ArchiveDisk_buffer[var15];
								}

								var4 = var11;
								++var7;
								continue;
							}

							var10000 = null;
							return (byte[])var10000;
						}

						var10000 = null;
						return (byte[])var10000;
					}

					byte[] var20 = var5;
					return var20;
				}

				var10000 = null;
			} catch (IOException var18) {
				return null;
			}

			return (byte[])var10000;
		}
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(I[BII)Z",
		garbageValue = "-473377734"
	)
	@Export("write")
	public boolean write(int var1, byte[] var2, int var3) {
		synchronized(this.datFile) {
			if (var3 >= 0 && var3 <= this.maxEntrySize) {
				boolean var5 = this.write0(var1, var2, var3, true);
				if (!var5) {
					var5 = this.write0(var1, var2, var3, false);
				}

				return var5;
			} else {
				throw new IllegalArgumentException("" + this.archive + ',' + var1 + ',' + var3);
			}
		}
	}

	@ObfuscatedName("ak")
	@ObfuscatedSignature(
		descriptor = "(I[BIZI)Z",
		garbageValue = "1363704518"
	)
	@Export("write0")
	boolean write0(int var1, byte[] var2, int var3, boolean var4) {
		synchronized(this.datFile) {
			try {
				int var6;
				boolean var10000;
				if (var4) {
					if (this.idxFile.length() < (long)(var1 * 6 + 6)) {
						var10000 = false;
						return var10000;
					}

					this.idxFile.seek((long)(var1 * 6));
					this.idxFile.read(ArchiveDisk_buffer, 0, 6);
					var6 = (ArchiveDisk_buffer[5] & 255) + ((ArchiveDisk_buffer[3] & 255) << 16) + ((ArchiveDisk_buffer[4] & 255) << 8);
					if (var6 <= 0 || (long)var6 > this.datFile.length() / 520L) {
						var10000 = false;
						return var10000;
					}
				} else {
					var6 = (int)((this.datFile.length() + 519L) / 520L);
					if (var6 == 0) {
						var6 = 1;
					}
				}

				ArchiveDisk_buffer[0] = (byte)(var3 >> 16);
				ArchiveDisk_buffer[1] = (byte)(var3 >> 8);
				ArchiveDisk_buffer[2] = (byte)var3;
				ArchiveDisk_buffer[3] = (byte)(var6 >> 16);
				ArchiveDisk_buffer[4] = (byte)(var6 >> 8);
				ArchiveDisk_buffer[5] = (byte)var6;
				this.idxFile.seek((long)(var1 * 6));
				this.idxFile.write(ArchiveDisk_buffer, 0, 6);
				int var7 = 0;
				int var8 = 0;

				while (true) {
					if (var7 < var3) {
						label154: {
							int var9 = 0;
							int var10;
							if (var4) {
								this.datFile.seek((long)var6 * 520L);
								int var11;
								int var12;
								if (var1 > 65535) {
									try {
										this.datFile.read(ArchiveDisk_buffer, 0, 10);
									} catch (EOFException var17) {
										break label154;
									}

									var10 = ((ArchiveDisk_buffer[1] & 255) << 16) + ((ArchiveDisk_buffer[0] & 255) << 24) + (ArchiveDisk_buffer[3] & 255) + ((ArchiveDisk_buffer[2] & 255) << 8);
									var11 = (ArchiveDisk_buffer[5] & 255) + ((ArchiveDisk_buffer[4] & 255) << 8);
									var9 = (ArchiveDisk_buffer[8] & 255) + ((ArchiveDisk_buffer[7] & 255) << 8) + ((ArchiveDisk_buffer[6] & 255) << 16);
									var12 = ArchiveDisk_buffer[9] & 255;
								} else {
									try {
										this.datFile.read(ArchiveDisk_buffer, 0, 8);
									} catch (EOFException var16) {
										break label154;
									}

									var10 = (ArchiveDisk_buffer[1] & 255) + ((ArchiveDisk_buffer[0] & 255) << 8);
									var11 = (ArchiveDisk_buffer[3] & 255) + ((ArchiveDisk_buffer[2] & 255) << 8);
									var9 = ((ArchiveDisk_buffer[5] & 255) << 8) + ((ArchiveDisk_buffer[4] & 255) << 16) + (ArchiveDisk_buffer[6] & 255);
									var12 = ArchiveDisk_buffer[7] & 255;
								}

								if (var10 != var1 || var11 != var8 || var12 != this.archive) {
									var10000 = false;
									return var10000;
								}

								if (var9 < 0 || (long)var9 > this.datFile.length() / 520L) {
									var10000 = false;
									return var10000;
								}
							}

							if (var9 == 0) {
								var4 = false;
								var9 = (int)((this.datFile.length() + 519L) / 520L);
								if (var9 == 0) {
									++var9;
								}

								if (var6 == var9) {
									++var9;
								}
							}

							if (var1 > 65535) {
								if (var3 - var7 <= 510) {
									var9 = 0;
								}

								ArchiveDisk_buffer[0] = (byte)(var1 >> 24);
								ArchiveDisk_buffer[1] = (byte)(var1 >> 16);
								ArchiveDisk_buffer[2] = (byte)(var1 >> 8);
								ArchiveDisk_buffer[3] = (byte)var1;
								ArchiveDisk_buffer[4] = (byte)(var8 >> 8);
								ArchiveDisk_buffer[5] = (byte)var8;
								ArchiveDisk_buffer[6] = (byte)(var9 >> 16);
								ArchiveDisk_buffer[7] = (byte)(var9 >> 8);
								ArchiveDisk_buffer[8] = (byte)var9;
								ArchiveDisk_buffer[9] = (byte)this.archive;
								this.datFile.seek(520L * (long)var6);
								this.datFile.write(ArchiveDisk_buffer, 0, 10);
								var10 = var3 - var7;
								if (var10 > 510) {
									var10 = 510;
								}

								this.datFile.write(var2, var7, var10);
								var7 += var10;
							} else {
								if (var3 - var7 <= 512) {
									var9 = 0;
								}

								ArchiveDisk_buffer[0] = (byte)(var1 >> 8);
								ArchiveDisk_buffer[1] = (byte)var1;
								ArchiveDisk_buffer[2] = (byte)(var8 >> 8);
								ArchiveDisk_buffer[3] = (byte)var8;
								ArchiveDisk_buffer[4] = (byte)(var9 >> 16);
								ArchiveDisk_buffer[5] = (byte)(var9 >> 8);
								ArchiveDisk_buffer[6] = (byte)var9;
								ArchiveDisk_buffer[7] = (byte)this.archive;
								this.datFile.seek(520L * (long)var6);
								this.datFile.write(ArchiveDisk_buffer, 0, 8);
								var10 = var3 - var7;
								if (var10 > 512) {
									var10 = 512;
								}

								this.datFile.write(var2, var7, var10);
								var7 += var10;
							}

							var6 = var9;
							++var8;
							continue;
						}
					}

					var10000 = true;
					return var10000;
				}
			} catch (IOException var18) {
				return false;
			}
		}
	}

	@Export("toString")
	@ObfuscatedName("toString")
	public String toString() {
		return "" + this.archive;
	}

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(Ldl;III)V",
		garbageValue = "168519800"
	)
	@Export("runScript")
	static void runScript(ScriptEvent var0, int var1, int var2) {
		Object[] var3 = var0.args;
		Script var4;
		if (SongTask.isWorldMapEvent(var0.type)) {
			class230.worldMapEvent = (WorldMapEvent)var3[0];
			WorldMapElement var5 = class148.WorldMapElement_get(class230.worldMapEvent.mapElement);
			var4 = class31.getWorldMapScript(var0.type, var5.objectId, var5.category);
		} else {
			int var6 = (Integer)var3[0];
			var4 = class28.getScript(var6);
		}

		if (var4 != null) {
			class158.runScriptLogic(var0, var4, var1, var2);
		}

	}

	@ObfuscatedName("br")
	@ObfuscatedSignature(
		descriptor = "([BIIB)I",
		garbageValue = "125"
	)
	public static int method2309(byte[] var0, int var1, int var2) {
		int var3 = -1;

		for (int var4 = var1; var4 < var2; ++var4) {
			var3 = var3 >>> 8 ^ Buffer.crc32Table[(var3 ^ var0[var4]) & 255];
		}

		var3 = ~var3;
		return var3;
	}

	@ObfuscatedName("nb")
	@ObfuscatedSignature(
		descriptor = "(Lde;ZI)V",
		garbageValue = "-1659889087"
	)
	@Export("closeInterface")
	static final void closeInterface(InterfaceParent var0, boolean var1) {
		int var2 = var0.group;
		int var3 = (int)var0.key;
		var0.remove();
		if (var1) {
			class33.widgetDefinition.method1743(var2);
		}

		class379.method1983(var2);
		Widget var4 = class33.widgetDefinition.method1740(var3);
		if (var4 != null) {
			ClanChannelMember.invalidateWidget(var4);
		}

		if (Client.rootInterface != -1) {
			Interpreter.runIntfCloseListener(Client.rootInterface, 1);
		}

	}

	@ObfuscatedName("nr")
	@ObfuscatedSignature(
		descriptor = "(III)V",
		garbageValue = "-54268811"
	)
	static final void method2314(int var0, int var1) {
		ClanChannel var2 = var0 >= 0 ? Client.currentClanChannels[var0] : ClanChannelMember.guestClanChannel;
		if (var2 != null && var1 >= 0 && var1 < var2.method868()) {
			ClanChannelMember var3 = (ClanChannelMember)var2.members.get(var1);
			if (var3.rank == -1) {
				String var4 = var3.username.getName();
				PacketBufferNode var5 = ClanChannelMember.getPacketBufferNode(ClientPacket.f65, Client.packetWriter.isaacCipher);
				var5.packetBuffer.writeByte(3 + class478.stringCp1252NullTerminatedByteSize(var4));
				var5.packetBuffer.writeByte(var0);
				var5.packetBuffer.writeShort(var1);
				var5.packetBuffer.writeStringCp1252NullTerminated(var4);
				Client.packetWriter.addNode(var5);
			}
		}
	}
}