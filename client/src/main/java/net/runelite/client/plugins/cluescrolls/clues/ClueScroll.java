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

import java.awt.Graphics2D;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import meteor.ui.overlay.PanelComponent;
import net.runelite.api.annotations.Varbit;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;

public abstract class ClueScroll
{
	@Setter(AccessLevel.PROTECTED)
	@Getter(AccessLevel.PUBLIC)
	private boolean requiresSpade;

	@Setter(AccessLevel.PROTECTED)
	@Getter(AccessLevel.PUBLIC)
	private boolean requiresLight;

	@Setter(AccessLevel.PROTECTED)
	@Getter(onMethod_ = {@Varbit}, value = AccessLevel.PUBLIC)
	private int firePitVarbitId = -1;

	@Setter(AccessLevel.PROTECTED)
	@Getter(AccessLevel.PUBLIC)
	private Enemy enemy;

	public abstract void makeOverlayHint(PanelComponent panelComponent, ClueScrollPlugin plugin);

	public abstract void makeWorldOverlayHint(Graphics2D graphics, ClueScrollPlugin plugin);
}
