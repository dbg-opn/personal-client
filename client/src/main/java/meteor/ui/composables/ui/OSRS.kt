package meteor.ui.composables.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eventbus.events.ConfigChanged
import kotlinx.coroutines.launch
import meteor.Main
import meteor.rs.Applet
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JPanel


var loaded = false
var applet = java.applet.Applet()
var subscribed = false
var gamePanel = JPanel()

@Composable
fun OSRSPanel() {
            Row {
                SwingPanel(
                    Color.Black,
                    modifier = Modifier.fillMaxSize(),
                    factory = {
                        if (!loaded) {
                            gamePanel.apply {
                                minimumSize = Dimension(765, 503)
                                size = Dimension()
                                layout = BorderLayout()
                                applet = Applet.applet
                                add(Applet.applet)
                                Applet.applet.init()
                                Applet.applet.start()
                                Main.finishStartup()
                                loaded = true
                            }
                        } else {
                            gamePanel
                        }
                    })
            }

}
