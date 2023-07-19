package com.github.kevindagame.blockmirror

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class BlockMirrorListener(val blockMirror: BlockMirror) : Listener {

    @EventHandler
    fun onBlockPlace(blockPlaceEvent: BlockPlaceEvent) {
        blockMirror.mirrorManager.getOrPut(blockPlaceEvent.player).forEach {
            it.value.handleBlockPlace(blockPlaceEvent.block.blockData, blockPlaceEvent.block.location)
        }
    }
}