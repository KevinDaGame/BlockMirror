package com.github.kevindagame.blockmirror.mirror

import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import org.bukkit.block.data.BlockData
import org.bukkit.util.BoundingBox

class MirrorBox(val name: String) {
    private val mirrors: MutableList<Mirror> = mutableListOf()
    lateinit var start: Location
    lateinit var end: Location
    private val boundingBox: BoundingBox
        get() = BoundingBox.of(start, end)

    fun addMirror(mirror: Mirror) {
        mirrors.add(mirror)
    }

    fun handleBlockPlace(blockData: BlockData, location: Location) {
        if (boundingBox.contains(location.x, location.y, location.z)) {
            mirrors.forEach { it.apply(blockData, location) }
        }
    }

    fun printMirrorBox() {
        if (!::start.isInitialized || !::end.isInitialized) {
            return
        }
        val step = 1
        //loop over each x coordinate between start and end
        // Keep in mind it may be negative

        val minX = start.x.coerceAtMost(end.x)
        val maxX = start.x.coerceAtLeast(end.x)
        val minY = start.y.coerceAtMost(end.y)
        val maxY = start.y.coerceAtLeast(end.y)
        val minZ = start.z.coerceAtMost(end.z)
        val maxZ = start.z.coerceAtLeast(end.z)

        for (x in minX.toInt()..maxX.toInt() step step) {
            //spawn particle block dust colored black
            start.world.spawnParticle(Particle.REDSTONE, x.toDouble(), minY, minZ, 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, x.toDouble(), minY, maxZ, 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, x.toDouble(), maxY, minZ, 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, x.toDouble(), maxY, maxZ, 1, DustOptions(Color.BLACK, 1.0f))
        }

        for (y in minY.toInt()..maxY.toInt() step step) {
            //spawn particle block dust colored black
            start.world.spawnParticle(Particle.REDSTONE, minX, y.toDouble(), minZ, 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, minX, y.toDouble(), maxZ, 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, maxX, y.toDouble(), minZ, 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, maxX, y.toDouble(), maxZ, 1, DustOptions(Color.BLACK, 1.0f))
        }

        for (z in minZ.toInt()..maxZ.toInt() step step) {
            //spawn particle block dust colored black
            start.world.spawnParticle(Particle.REDSTONE, minX, minY, z.toDouble(), 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, minX, maxY, z.toDouble(), 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, maxX, minY, z.toDouble(), 1, DustOptions(Color.BLACK, 1.0f))
            start.world.spawnParticle(Particle.REDSTONE, maxX, maxY, z.toDouble(), 1, DustOptions(Color.BLACK, 1.0f))
        }

    }
}