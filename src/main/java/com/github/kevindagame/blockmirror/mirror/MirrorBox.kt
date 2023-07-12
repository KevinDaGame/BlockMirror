package com.github.kevindagame.blockmirror.mirror

import org.bukkit.Location
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player
import org.bukkit.util.BoundingBox

class MirrorBox(val boundingBox: BoundingBox, val player: Player) {
    private val mirrors: MutableList<Mirror> = mutableListOf()

    fun addMirror(mirror: Mirror) {
        mirrors.add(mirror)
    }

    fun handleBlockPlace(blockData: BlockData, location: Location) {
        if(boundingBox.contains(location.x, location.y, location.z)) {
            mirrors.forEach { it.apply(blockData, location)}
        }
    }
}