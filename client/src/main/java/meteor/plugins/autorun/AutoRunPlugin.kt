package meteor.plugins.autorun

import eventbus.events.GameTick
import meteor.plugins.Plugin
import meteor.plugins.PluginDescriptor
import meteor.rs.ClientThread
import net.runelite.api.widgets.WidgetInfo
import java.util.*

@PluginDescriptor(name = "AutoRun", description = "Automatically enables run.", enabledByDefault = true)

class AutoRunPlugin : Plugin() {
    val config = configuration<AutoRunConfig>()
    private val clientThread: ClientThread = ClientThread
    private val rand = Random()
    private var nextRunThreshhold = -1


    override fun onStart() {}
    override fun onStop() {}


    override fun onGameTick(it: GameTick) {
        if (nextRunThreshhold <= 0) nextRunThreshhold = randInt(config.minRun(), config.maxRun()) else {
            if (!isRunning && client.energy > nextRunThreshhold) {
                toggleRun()
                nextRunThreshhold = -1
            }
        }
    }

    val isRunning: Boolean
        get() = client.getVarpValue(173) == 1

    private fun toggleRun() {
        val runOrb = client.getWidget(WidgetInfo.MINIMAP_TOGGLE_RUN_ORB)
        runOrb?.let {
            it.interact("Toggle Run")
        }
    }

    fun randInt(r: Random, min: Int, max: Int): Int {
        return r.nextInt(max - min + 1) + min
    }

    fun randInt(min: Int, max: Int): Int {
        var max = max
        if (max < min) max = min
        return randInt(rand, min, max)
    }
}