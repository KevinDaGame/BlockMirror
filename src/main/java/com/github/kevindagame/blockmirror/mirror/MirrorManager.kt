package com.github.kevindagame.blockmirror.mirror

import org.bukkit.entity.Player
import java.util.*

class MirrorManager {
    private val mirrors: MutableMap<UUID, MutableMap<String, MirrorBox>> = mutableMapOf()

    fun getOrPut(player: Player): MutableMap<String, MirrorBox> {
        return mirrors.getOrPut(player.uniqueId) { mutableMapOf() }
    }

    fun addMirrorBox(player: Player, mirrorBox: MirrorBox) {
        val mirrorBoxes = getOrPut(player)
        if(mirrorBoxes.containsKey(mirrorBox.name)) {
            throw IllegalArgumentException("MirrorBox with name ${mirrorBox.name} already exists")
        }
        mirrorBoxes[mirrorBox.name] = mirrorBox
    }

    fun removeMirrorBox(player: Player, name: String) {
        val mirrorBoxes = getOrPut(player)
        if(!mirrorBoxes.containsKey(name)) {
            throw IllegalArgumentException("MirrorBox with name $name does not exist")
        }
        mirrorBoxes.remove(name)
    }
}