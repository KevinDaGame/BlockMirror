package com.github.kevindagame.blockmirror.commands

import org.bukkit.entity.Player

class AddMirrorCommand: IBlockMirrorCommand {
    override val name: String
        get() = "add"
    override val aliases: List<String>
        get() = listOf("add")
    override val permission: String
        get() = "blockmirror.add"

    override fun doCommand(player: Player, args: Array<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun tabComplete(args: Array<String>): List<String> {
        TODO("Not yet implemented")
    }
}