package com.github.kevindagame.blockmirror.mirror

import org.bukkit.Axis
import org.bukkit.Location
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player

class Mirror(val startLocation: Location, val endLocation: Location, val axis: Axis, val player: Player) {

    fun apply(blockData: BlockData, location: Location) {
        //mirror the block to the other side of the mirror along the axis
        val newLocation = when(axis) {
            Axis.X -> {
                if(startLocation.x != endLocation.x) throw IllegalArgumentException("startLocation and endLocation must have the same x value")
                val mirrorX = startLocation.x
                val newX = mirrorX - (location.x - mirrorX)
                // if mirrorx 10 and location.x = 15 then 10 - (15-10) = 5
                // if mirrorx = -20 and location.x = 40 then -20 - (40 - -20) = -80
                // if mirroX = -10 and location.x = -20 then -10 - (-20 - -10) = 0
                Location(location.world, newX, location.y, location.z)

            }
            Axis.Y -> {
                if(startLocation.y != endLocation.y) throw IllegalArgumentException("startLocation and endLocation must have the same y value")
                val mirrorY = startLocation.y
                val newY = mirrorY - (location.y - mirrorY)
                Location(location.world, location.x, newY, location.z)
            }
            Axis.Z -> {
                if(startLocation.z != endLocation.z) throw IllegalArgumentException("startLocation and endLocation must have the same z value")
                val mirrorZ = startLocation.z
                val newZ = mirrorZ - (location.z - mirrorZ)
                Location(location.world, location.x, location.y, newZ)
            }
        }
        newLocation.block.blockData = blockData
    }
}