package net.runelite.client.plugins.questhelper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.runelite.client.plugins.questhelper.questhelpers.QuestHelper
import net.runelite.client.plugins.questhelper.requirements.Requirement
import net.runelite.client.plugins.questhelper.requirements.item.ItemRequirement
import dev.hoot.api.game.GameThread
import eventbus.events.GameTick
import eventbus.events.ItemContainerChanged
import kotlinx.coroutines.runBlocking
import meteor.Main
import meteor.ui.composables.PluginPanel
import meteor.ui.composables.preferences.*

class QuestHelperPluginPanel(var questHelper: QuestHelper) : PluginPanel() {
    var quest = questHelper.quest

    //We use mutableStateOf so compose is aware of changes to it, and will redraw
    //Checking requirements met must be done on ClientThread (so not in @Composable function)
    //So the actual checking is done in the plugin onGameTick
    var generalRequirementsMet = mutableStateOf(ArrayList<RequirementMet>())
    var itemRequirementsMet = mutableStateOf(ArrayList<RequirementMet>())
    var itemRecommendMet = mutableStateOf(ArrayList<RequirementMet>())

    //Static data
    val enemiesToDefeat = questHelper.combatRequirements
    val rewards = questHelper.questRewards

    @Composable override fun Header() {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
            modifier = Modifier.width(300.dp).fillMaxHeight(0.05f).background(background  )) {
            MaterialTheme(colors = if (darkLightMode.value)darkThemeColors else lightThemeColors ) {
                Text(quest.getName(),style = TextStyle(color = Color.Cyan, fontSize = 20.sp))
            }
        }
    }

    @Composable override fun Content() {
        LazyColumn(modifier = Modifier.width(300.dp).fillMaxHeight().background(surface )) {
            item {
                Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
                    MaterialTheme(colors = if (darkLightMode.value)darkThemeColors else lightThemeColors ) {
                        Requirements(generalRequirementsMet.value, "General requirements")
                        Requirements(itemRequirementsMet.value, "Item requirements")
                        Requirements(itemRecommendMet.value, "Item recommendation")
                        NonUpdatingTextList(enemiesToDefeat, "Enemies to defeat")
                        NonUpdatingTextList(rewards, "Quest rewards")
                    }
                }
            }
        }

    }

    @Composable fun Requirements(requirements: ArrayList<RequirementMet>, text: String) {
        if (requirements.isNotEmpty()) {
            Row(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 30.dp).background(darkThemeColors.surface)){
                Text(text, style = TextStyle(color = Color.Cyan, fontSize = 14.sp), modifier = Modifier.align(Alignment.Bottom))
            }

            LazyColumn(modifier = Modifier.fillMaxWidth().height((requirements.size * 32).dp)
                .background(darkThemeColors.surface), horizontalAlignment = Alignment.CenterHorizontally, ) {
                items(items = requirements, itemContent = { requirement ->
                    Row(modifier = Modifier.fillMaxWidth(0.9f).height(32.dp).background(background  )){
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth(0.9f).height(32.dp).background(background  )) {

                            if (requirement.requirement is ItemRequirement && requirement.requirement.quantity != -1) {
                                Text(
                                    "${requirement.requirement.quantity}",
                                    style = TextStyle(color = Color.White, fontSize = 14.sp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                val color = getColorForItemReq(requirement.requirement)
                                Text(
                                    requirement.requirement.displayText,
                                    style = TextStyle(color = color, fontSize = 14.sp)
                                )
                            } else {
                                val meetsRequirement = requirement.met
                                val color = if (meetsRequirement) Color.Green else Color.Red
                                Text(
                                    requirement.requirement.displayText,
                                    style = TextStyle(color = color, fontSize = 14.sp)
                                )
                            }
                        }
                    }
                })
            }
        }
    }

    fun getColorForItemReq(ir: ItemRequirement) : Color {
        var color = Color.Red
        try {
            when (ir.getColorConsideringBank(Main.client, false, questHelper.questBank.bankItems, questHelper.config)) {
                java.awt.Color.WHITE -> color = Color.Yellow
                java.awt.Color.GREEN -> color = Color.Green
            }
        } catch (_: Exception) {}
        return color
    }

    @Composable fun NonUpdatingTextList(list: List<String>?, text: String) {
        if (!list.isNullOrEmpty()) {
            Row(modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 30.dp).background(darkThemeColors.surface)){
                Text(text, style = TextStyle(color = Color.Cyan, fontSize = 14.sp), modifier = Modifier.align(Alignment.Bottom))
            }

            LazyColumn(modifier = Modifier.fillMaxWidth().height((list.size * 32).dp).background(darkThemeColors.surface), horizontalAlignment = Alignment.CenterHorizontally, ) {
                items(items = list, itemContent = { line ->
                    if (line == "</br>") {
                        Spacer(Modifier.height(10.dp).background(background ) )
                    }
                    else {
                        Row(modifier = Modifier.fillMaxWidth(0.9f).height(32.dp).background(background ) ){
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth(0.9f).height(32.dp).background(background ))
                            {
                                val color = Color.White
                                Text(line, style = TextStyle(color = color, fontSize = 14.sp))
                            }
                        }
                    }
                })
            }
        }
    }
}