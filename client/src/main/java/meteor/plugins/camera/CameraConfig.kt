/*
 * Copyright (c) 2018 Abex
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
package meteor.plugins.camera

import meteor.config.legacy.Config
import meteor.config.legacy.ConfigGroup
import meteor.config.legacy.ConfigItem
import meteor.config.legacy.Range

@ConfigGroup("zoom") // using the old plugin's group name
interface CameraConfig : Config {
    @ConfigItem(
        keyName = "inner",
        name = "Expand inner zoom limit",
        description = "Configures whether or not the inner zoom limit is reduced",
        position = 1
    )
    fun innerLimit(): Boolean {
        return false
    }

    @Range(min = -400, max = 400)
    @ConfigItem(
        keyName = "outerLimit",
        name = "Expand outer zoom limit",
        description = "Configures how much the outer zoom limit is adjusted",
        position = 2
    )
    fun outerLimit(): Int {
        return 0
    }

    @ConfigItem(
        keyName = "relaxCameraPitch",
        name = "Vertical camera",
        description = "Relax the camera's upper pitch limit",
        position = 3
    )
    fun relaxCameraPitch(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "controlFunction",
        name = "Control Function",
        description = "Configures the zoom function when control is pressed",
        position = 4
    )
    fun controlFunction(): ControlFunction {
        return ControlFunction.NONE
    }

    @ConfigItem(
        keyName = "ctrlZoomValue",
        name = "Reset zoom position",
        description = "Position of zoom when it is reset",
        position = 5
    )
    @Range(min = -400, max = 400)
    fun ctrlZoomValue(): Int {
        return 512
    }

    @ConfigItem(keyName = "zoomIncrement", name = "Zoom Speed", description = "Speed of zoom", position = 6)
    fun zoomIncrement(): Int {
        return 25
    }

    @ConfigItem(
        keyName = "rightClickMovesCamera",
        name = "Right click moves camera",
        description = "Remaps right click to middle mouse click if there are no menu options",
        position = 7
    )
    fun rightClickMovesCamera(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "ignoreExamine",
        name = "Ignore Examine",
        description = "Ignore the Examine menu entry",
        position = 8
    )
    fun ignoreExamine(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "middleClickMenu",
        name = "Middle-button opens menu",
        description = "Middle-mouse button always opens the menu",
        position = 9
    )
    fun middleClickMenu(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "compassLookPreservePitch",
        name = "Preserve pitch on compass look",
        description = "Preserves the current pitch value (vertical angle) when using the compass look options.",
        position = 11
    )
    fun compassLookPreservePitch(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "invertYaw",
        name = "Invert Yaw",
        description = "Makes moving the camera horizontally with the mouse backwards",
        position = 12
    )
    fun invertYaw(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "invertPitch",
        name = "Invert Pitch",
        description = "Makes moving the camera vertically with the mouse backwards",
        position = 13
    )
    fun invertPitch(): Boolean {
        return false
    }

    @ConfigItem(
        keyName = "preserveYaw",
        name = "Preserve yaw on world hop",
        description = "Preserves the camera yaw (left/right) when world hopping.",
        position = 14
    )
    fun preserveYaw(): Boolean {
        return false
    }

}