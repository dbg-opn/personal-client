package meteor.api.movement.pathfinder

import meteor.api.game.Vars
import meteor.api.items.Inventory
import net.runelite.api.ItemID
import net.runelite.api.coords.WorldPoint

enum class TeleportTab(point: WorldPoint, itemName: String, itemId: Int) {
    VARROCK_TELEPORT_TAB(
        WorldPoint(3212, 3424, 0),
        "Varrock teleport",
        ItemID.VARROCK_TELEPORT
    ),
    LUMBRIDGE_TELEPORT_TAB(
        WorldPoint(3225, 3219, 0),
        "Lumbridge teleport",
        ItemID.LUMBRIDGE_TELEPORT
    ),
    FALADOR_TELEPORT_TAB(WorldPoint(2966, 3379, 0), "Falador teleport", ItemID.FALADOR_TELEPORT), CAMELOT_TELEPORT_TAB(
        WorldPoint(2757, 3479, 0),
        "Camelot teleport",
        ItemID.CAMELOT_TELEPORT
    ),
    ARDOUGNE_TELEPORT_TAB(
        WorldPoint(2661, 3300, 0),
        "Ardougne teleport",
        ItemID.ARDOUGNE_TELEPORT
    ),
    WEST_ARDOUGNE_TELEPORT_TAB(
        WorldPoint(2500, 3290, 0),
        "West ardougne teleport",
        ItemID.WEST_ARDOUGNE_TELEPORT
    ),
    RIMMINGTON_TELEPORT_TAB(
        WorldPoint(2954, 3224, 0),
        "Rimmington teleport",
        ItemID.RIMMINGTON_TELEPORT
    ),
    TAVERLEY_TELEPORT_TAB(
        WorldPoint(2894, 3465, 0),
        "Taverley teleport",
        ItemID.TAVERLEY_TELEPORT
    ),
    RELLEKKA_TELEPORT_TAB(
        WorldPoint(2668, 3631, 0),
        "Rellekka teleport",
        ItemID.RELLEKKA_TELEPORT
    ),
    BRIMHAVEN_TELEPORT_TAB(
        WorldPoint(2758, 3178, 0),
        "Brimhaven teleport",
        ItemID.BRIMHAVEN_TELEPORT
    ),
    POLLNIVNEACH_TELEPORT_TAB(
        WorldPoint(3340, 3004, 0),
        "Pollnivneach teleport",
        ItemID.POLLNIVNEACH_TELEPORT
    ),
    YANILLE_TELEPORT_TAB(WorldPoint(2544, 3095, 0), "Yanille teleport", ItemID.YANILLE_TELEPORT), HOSIDIUS_TELEPORT_TAB(
        WorldPoint(1744, 3517, 0),
        "Hosidius teleport",
        ItemID.HOSIDIUS_TELEPORT
    ),
    SALVE_GRAVEYARD_TELEPORT_TAB(
        WorldPoint(3432, 3460, 0),
        "Salve Graveyard teleport",
        ItemID.SALVE_GRAVEYARD_TELEPORT
    );

    private val point: WorldPoint
    val itemName: String
    val itemId: Int

    init {
        this.point = point
        this.itemName = itemName
        this.itemId = itemId
    }

    fun getPoint(): WorldPoint {
        return point
    }

    fun canUse(): Boolean {
        return hasRequirements() && Inventory.getFirst(itemId) != null
    }

    fun hasRequirements(): Boolean {
        return ((this != ARDOUGNE_TELEPORT_TAB || Vars.getVarp(165) >= 30)
                && (this != SALVE_GRAVEYARD_TELEPORT_TAB || Vars.getVarp(302) >= 61))
    }
}