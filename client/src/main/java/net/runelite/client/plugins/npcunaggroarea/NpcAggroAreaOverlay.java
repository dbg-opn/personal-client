/*
 * Copyright (c) 2018, Woox <https://github.com/wooxsolo>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.npcunaggroarea;

import meteor.Main;
import meteor.ui.overlay.Overlay;
import meteor.ui.overlay.OverlayLayer;
import meteor.ui.overlay.OverlayPosition;
import meteor.ui.overlay.OverlayPriority;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.geometry.Geometry;
import org.jetbrains.annotations.NotNull;
import org.rationalityfrontline.kevent.KEvent;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.time.Instant;

class NpcAggroAreaOverlay extends Overlay
{
	private static final int MAX_LOCAL_DRAW_LENGTH = 100 * Perspective.LOCAL_TILE_SIZE;

	private final Client client = Main.INSTANCE.getClient();
	private final NpcAggroAreaConfig config;
	private final NpcAggroAreaPlugin plugin;

	public NpcAggroAreaOverlay(NpcAggroAreaConfig config, NpcAggroAreaPlugin plugin)
	{
		this.config = config;
		this.plugin = plugin;

		setLayer(OverlayLayer.ABOVE_SCENE);
		setPriority(OverlayPriority.LOW);
		setPosition(OverlayPosition.DYNAMIC);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!plugin.isActive() || plugin.getSafeCenters()[1] == null)
		{
			return null;
		}

		final Player localPlayer = client.getLocalPlayer();
		if (localPlayer.getHealthScale() == -1 && config.hideIfOutOfCombat())
		{
			return null;
		}

		GeneralPath lines = plugin.getLinesToDisplay()[client.getPlane()];
		if (lines == null)
		{
			return null;
		}

		Color outlineColor = config.unaggroAreaColor();
		AggressionTimer timer = plugin.getCurrentTimer();
		if (outlineColor == null || timer == null || Instant.now().compareTo(timer.getEndTime()) < 0)
		{
			outlineColor = config.aggroAreaColor();
		}

		renderPath(graphics, lines, outlineColor);
		return null;
	}

	private void renderPath(Graphics2D graphics, GeneralPath path, Color color)
	{
		LocalPoint playerLp = client.getLocalPlayer().getLocalLocation();
		Rectangle viewArea = new Rectangle(
			playerLp.getX() - MAX_LOCAL_DRAW_LENGTH,
			playerLp.getY() - MAX_LOCAL_DRAW_LENGTH,
			MAX_LOCAL_DRAW_LENGTH * 2,
			MAX_LOCAL_DRAW_LENGTH * 2);

		graphics.setColor(color);
		graphics.setStroke(new BasicStroke(1));

		path = Geometry.clipPath(path, viewArea);
		path = Geometry.filterPath(path, (p1, p2) ->
			Perspective.localToCanvas(client, new LocalPoint((int)p1[0], (int)p1[1]), client.getPlane()) != null &&
			Perspective.localToCanvas(client, new LocalPoint((int)p2[0], (int)p2[1]), client.getPlane()) != null);
		path = Geometry.transformPath(path, coords ->
		{
			Point point = Perspective.localToCanvas(client, new LocalPoint((int)coords[0], (int)coords[1]), client.getPlane());
			coords[0] = point.getX();
			coords[1] = point.getY();
		});

		graphics.draw(path);
	}

	@NotNull
	@Override
	public KEvent getKEVENT_INSTANCE() {
		return Main.INSTANCE.getEventBus();
	}

	@NotNull
	@Override
	public String getSUBSCRIBER_TAG() {
		return "npcaggroarea";
	}
}
