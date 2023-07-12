package com.github.kevindagame.blockmirror

import com.github.kevindagame.blockmirror.mirror.Mirror
import com.github.kevindagame.blockmirror.mirror.MirrorBox
import com.github.kevindagame.blockmirror.mirror.MirrorManager
import org.bukkit.Axis
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.BoundingBox

class BlockMirror : JavaPlugin() {
    lateinit var mirrorManager: MirrorManager
    lateinit var mirrorBox : MirrorBox
    override fun onEnable() {
        //register command
        mirrorManager = MirrorManager()
        mirrorBox = MirrorBox(BoundingBox(0.0, 0.0, 0.0, 10.0, 10.0, 10.0), server.getOfflinePlayer("KevDaDev").player!!)
        val world = server.getWorld("world")
        val location1 = Location(world, -10.0, -10.0, 0.0)
        val location2 = Location(world, 10.0, 10.0, 0.0)
        mirrorBox.addMirror(Mirror(location1, location2, Axis.Z, server.getOfflinePlayer("KevDaDev").player!! ))

        server.pluginManager.registerEvents(BlockMirrorListener(this), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
