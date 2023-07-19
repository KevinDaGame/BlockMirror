package com.github.kevindagame.blockmirror.commands

import com.github.kevindagame.blockmirror.BlockMirror
import com.github.kevindagame.blockmirror.mirror.MirrorBox
import net.kyori.adventure.text.Component.text
import org.bukkit.Location
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Optional
import revxrsal.commands.annotation.Subcommand
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.player

@Command("blockmirror", "bm")
class BlockMirrorCommand(private val blockMirror: BlockMirror) {
    @Subcommand("box create")
    fun createMirrorBox(
        sender: BukkitCommandActor,
        name: String,
    ) {
        try {
            blockMirror.mirrorManager.addMirrorBox(sender.player, MirrorBox(name))
            sender.reply(text("Created box with name $name"))
        } catch (e: IllegalArgumentException) {
            sender.reply(text("Box with name $name already exists"))

        }
    }

    @Subcommand("box delete")
    fun deleteMirrorBox(
        sender: BukkitCommandActor,
        name: String,
    ) {
        try {
            blockMirror.mirrorManager.removeMirrorBox(sender.player, name)
            sender.reply(text("Deleted box with name $name"))
        } catch (e: IllegalArgumentException) {
            sender.reply(text("Box with name $name does not exist"))

        }
    }

    @Subcommand("box list")
    fun listMirrorBoxes(
        sender: BukkitCommandActor,
    ) {
        val mirrorBoxes = blockMirror.mirrorManager.getOrPut(sender.player)
        if (mirrorBoxes.isEmpty()) {
            sender.reply(text("You have no boxes"))
            return
        }
        sender.reply(text("Your boxes: ${mirrorBoxes.keys.joinToString(", ")}"))
    }

    @Subcommand("box start", "box setstart")
    fun setStart(
        sender: BukkitCommandActor,
        boxName: String,
        @Optional location: Location?,
    ) {
       val mirrorBoxes = blockMirror.mirrorManager.getOrPut(sender.player)

        if (!mirrorBoxes.containsKey(boxName)) {
            sender.reply(text("Box with name $boxName does not exist"))
            return
        }
        val mirrorBox = mirrorBoxes[boxName]!!

        if(location != null) {
            mirrorBox.start = location
            sender.reply(text("Set start of box $boxName to $location"))
        } else {
            mirrorBox.start = sender.player.location
            sender.reply(text("Set start of box $boxName to your location"))
        }
    }

    @Subcommand("box end", "box setend")
    fun setEnd(
        sender: BukkitCommandActor,
        boxName: String,
        @Optional location: Location?,
    ) {
        val mirrorBoxes = blockMirror.mirrorManager.getOrPut(sender.player)

        if (!mirrorBoxes.containsKey(boxName)) {
            sender.reply(text("Box with name $boxName does not exist"))
            return
        }
        val mirrorBox = mirrorBoxes[boxName]!!

        if(location != null) {
            mirrorBox.end = location
            sender.reply(text("Set end of box $boxName to $location"))
        } else {
            mirrorBox.end = sender.player.location
            sender.reply(text("Set end of box $boxName to your location"))
        }
    }

    @Subcommand("mirror create")
    fun createMirror(
        sender: BukkitCommandActor,
        name: String,
        boxName: String,
    ) {
        sender.reply(text("Created mirror with name $name in box $boxName"))
    }

    @Subcommand("mirror delete")
    fun deleteMirror(
        sender: BukkitCommandActor,
        name: String,
        boxName: String,
    ) {
        sender.reply(text("Deleted mirror with name $name in box $boxName"))
    }

    @Subcommand("mirror list")
    fun listMirrors(
        sender: BukkitCommandActor,
        boxName: String,
    ) {
        sender.reply(text("Listed mirrors in box $boxName"))
    }
}
