package com.github.kevindagame.blockmirror.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor.RED
import org.bukkit.entity.Player

interface IBlockMirrorCommand {
    val name: String
    val aliases: List<String>
    val permission: String

    fun execute(player: Player, args: Array<String>): Boolean {
        return if (player.hasPermission(permission)) {
            doCommand(player, args)
        } else {
            player.sendMessage(Component.text("You don't have permission to do that!", RED))
            true
        }
    }

    fun doCommand(player: Player, args: Array<String>): Boolean

    fun tabComplete(args: Array<String>): List<String>
}