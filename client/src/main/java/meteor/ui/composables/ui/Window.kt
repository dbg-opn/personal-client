package meteor.ui.composables.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import meteor.Main
import meteor.ui.composables.configPanel
import meteor.ui.composables.nodes.sectionItem
import meteor.ui.composables.preferences.*
import java.awt.Dimension


@Composable
fun FrameWindowScope.windowContent() {
    windowFrame {
        Main.window = this@windowContent

        val width = when {
            pluginsOpen.value || configOpen.value || infoPanelOpen.value || hiscoreOpen.value || xpTrackerOpen.value || lootTrackerOpen.value || externalsOpen.value || notesOpen.value -> totalClientWidth
            else -> totalMinimalWidth
        }
        val height = minimumHeight
        window.minimumSize = Dimension(width,height)
        when {
            toolBarOpen.value -> toolBar {
                pluginListButton
            }
        }
        Column(
            verticalArrangement = Arrangement.Top,
        ) {if (configOpen.value == true && pluginPanelIsOpen.value == false)
            Row(
                modifier = Modifier.height(42.dp).width(15.dp).background(surface),
                verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start,
            ) {}
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight().width(15.dp).background(background)
            ) { if (configOpen.value == false && pluginPanelIsOpen.value == true)
                Spacer(modifier = Modifier.width(15.dp))
                sectionItem(modifier = Modifier.background(background).size(25.dp)) {
                    when {
                        !toolBarOpen.value -> toolBarOpen.value = true
                        toolBarOpen.value -> toolBarOpen.value = false
                    }
                }
            }
        }
        content {
            when {
                pluginPanelIsOpen.value -> pluginPanel.value?.CreateComponent()
                configOpen.value -> configPanel()
                pluginsOpen.value -> pluginsPanel()
            }
            gameFrame {
                OSRSPanel()
            }
        }
    }
}





