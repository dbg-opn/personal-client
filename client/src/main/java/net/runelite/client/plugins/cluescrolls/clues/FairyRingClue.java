/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
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
package net.runelite.client.plugins.cluescrolls.clues;

import com.google.common.collect.ImmutableList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import lombok.Getter;
import meteor.ui.components.LineComponent;
import meteor.ui.overlay.PanelComponent;
import meteor.util.OverlayUtil;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import static net.runelite.client.plugins.cluescrolls.ClueScrollOverlay.TITLED_CONTENT_COLOR;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import meteor.ui.table.TitleComponent;

@Getter
public class FairyRingClue extends ClueScroll implements TextClueScroll, LocationClueScroll
{
	private static final List<FairyRingClue> CLUES = ImmutableList.of(
		new FairyRingClue("A I R 2 3 3 1", new WorldPoint(2702, 3246, 0)),
		new FairyRingClue("A I Q 0 4 4 0", new WorldPoint(3000, 3110, 0)),
		new FairyRingClue("A L P 1 1 4 0", new WorldPoint(2504, 3633, 0)),
		new FairyRingClue("B L P 6 2 0 0", new WorldPoint(2439, 5132, 0)),
		new FairyRingClue("B J R 1 1 2 3", new WorldPoint(2648, 4729, 0)),
		new FairyRingClue("B I P 7 0 1 3", new WorldPoint(3407, 3330, 0)),
		new FairyRingClue("C I S 0 0 0 9", new WorldPoint(1630, 3868, 0)),
		new FairyRingClue("C K P 0 2 2 4", new WorldPoint(2073, 4846, 0)),
		new FairyRingClue("D I P 8 5 1 1", new WorldPoint(3041, 4770, 0)),
		new FairyRingClue("D K S 2 3 1 0", new WorldPoint(2747, 3720, 0))
	);

	private String text;
	private WorldPoint location;

	private FairyRingClue(String text, WorldPoint location)
	{
		this.text = text;
		this.location = location;
		setRequiresSpade(true);
	}

	@Override
	public void makeOverlayHint(PanelComponent panelComponent, ClueScrollPlugin plugin)
	{
		panelComponent.getChildren().add(TitleComponent.Companion.builder().text("Fairy Ring Clue"));
		panelComponent.getChildren().add(new LineComponent.Builder().left("Code:").build());
		panelComponent.getChildren().add(new LineComponent.Builder()
			.left(getText().substring(0, 5))
			.leftColor(TITLED_CONTENT_COLOR)
			.build());

		panelComponent.getChildren().add(new LineComponent.Builder()
			.left("Travel to the fairy ring to see where to dig.")
			.build());
	}

	@Override
	public void makeWorldOverlayHint(Graphics2D graphics, ClueScrollPlugin plugin)
	{
		LocalPoint localLocation = LocalPoint.fromWorld(plugin.getC(), getLocation());

		if (localLocation == null)
		{
			return;
		}

		OverlayUtil.INSTANCE.renderTileOverlay(graphics, localLocation, plugin.getSpadeImage(), Color.ORANGE);
	}

	public static FairyRingClue forText(String text)
	{
		for (FairyRingClue clue : CLUES)
		{
			if (clue.text.equalsIgnoreCase(text))
			{
				return clue;
			}
		}

		return null;
	}
}
