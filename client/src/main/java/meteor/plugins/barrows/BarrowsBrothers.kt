/*
 * Copyright (c) 2018, Seth <Sethtroll3@gmail.com>
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
package meteor.plugins.barrows

import net.runelite.api.Varbits
import net.runelite.api.coords.WorldPoint

internal enum class BarrowsBrothers(var brotherName: String, var worldLocation: WorldPoint, var varbit: Int) {
    AHRIM("Ahrim", WorldPoint(3566, 3289, 0), Varbits.BARROWS_KILLED_AHRIM), DHAROK("Dharok", WorldPoint(3575, 3298, 0), Varbits.BARROWS_KILLED_DHAROK), GUTHAN("Guthan", WorldPoint(3577, 3283, 0), Varbits.BARROWS_KILLED_GUTHAN), KARIL("Karil", WorldPoint(3566, 3275, 0), Varbits.BARROWS_KILLED_KARIL), TORAG("Torag", WorldPoint(3553, 3283, 0), Varbits.BARROWS_KILLED_TORAG), VERAC("Verac", WorldPoint(3557, 3298, 0), Varbits.BARROWS_KILLED_VERAC);
}