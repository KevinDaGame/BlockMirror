package com.github.kevindagame.blockmirror.commands

import org.bukkit.BanList
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Range
import revxrsal.commands.annotation.Switch
import revxrsal.commands.util.Strings.colorize
import java.util.*
import java.util.concurrent.TimeUnit


class TestCommand {
    @Command("test")
    fun banPlayer(
        sender: Player,
        @Range(min = 1.0) days: Long,
        toBan: Player,
        reason: String?,
        @Switch("silent") silent: Boolean
    ) {
        if (!silent) Bukkit.broadcastMessage(colorize("Player &6" + toBan.name + " &fhas been banned for $reason!"))
        val expires = Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(days))
    }
}