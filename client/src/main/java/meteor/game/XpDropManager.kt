/*
 * Copyright (c) 2021, ThatGamerBlue <thatgamerblue@gmail.com>
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
package meteor.game

import eventbus.Events
import eventbus.events.StatChanged
import eventbus.events.XPDrop
import meteor.Main
import meteor.plugins.EventSubscriber
import net.runelite.api.Skill
import java.util.*

class XpDropManager : EventSubscriber() {
    init {
        subscribe()
        eventListening = true
    }

    private val client = Main.client

    private val previousSkillExpTable: MutableMap<Skill, Int> = EnumMap(
        Skill::class.java
    )

    override fun onStatChanged(event: StatChanged) {
        val skill = event.skill
        val xp = client.getSkillExperience(skill)
        val previous = previousSkillExpTable.put(skill, xp)
        if (previous != null) {
            val previousExpGained = xp - previous
            val xpDropEvent = XPDrop(skill, previousExpGained)
            Main.client.callbacks.post(Events.XP_DROP, xpDropEvent)
        }
    }
}