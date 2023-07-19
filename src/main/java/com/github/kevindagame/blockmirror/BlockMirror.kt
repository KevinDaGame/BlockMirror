package com.github.kevindagame.blockmirror

import com.github.kevindagame.blockmirror.commands.BlockMirrorCommand
import com.github.kevindagame.blockmirror.commands.TestCommand
import com.github.kevindagame.blockmirror.commands.resolver.LocationResolver
import com.github.kevindagame.blockmirror.mirror.Mirror
import com.github.kevindagame.blockmirror.mirror.MirrorBox
import com.github.kevindagame.blockmirror.mirror.MirrorManager
import org.bukkit.Axis
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.BoundingBox
import revxrsal.commands.bukkit.BukkitCommandHandler

class BlockMirror : JavaPlugin() {
    lateinit var mirrorManager: MirrorManager

    lateinit var commandHandler : BukkitCommandHandler
    override fun onEnable() {
        //register command
        mirrorManager = MirrorManager()
        commandHandler = BukkitCommandHandler.create(this)
        server.pluginManager.registerEvents(BlockMirrorListener(this), this)

        commandHandler.registerValueResolver(Location::class.java, LocationResolver())
        commandHandler.register(TestCommand())
        commandHandler.register(BlockMirrorCommand(this))

        server.scheduler.scheduleSyncRepeatingTask(this, {
            for(player in server.onlinePlayers) {
                val mirrorBoxes = mirrorManager.getOrPut(player)
                for(mirrorBox in mirrorBoxes.values) {
                    mirrorBox.printMirrorBox()
                }
            }
        }, 0, 20)
    }


    override fun onDisable() {
        // Plugin shutdown logic
    }

}
