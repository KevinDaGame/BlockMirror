package com.github.kevindagame.blockmirror.commands.resolver

import org.bukkit.Bukkit
import org.bukkit.Location
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.process.ValueResolver

class LocationResolver : ValueResolver<Location> {

    override fun resolve(context: ValueResolver.ValueResolverContext): Location {
        val actor = context.actor<BukkitCommandActor>()
        when (val first = context.pop()) {
            "here" -> return actor.requirePlayer().location
            "there" -> return actor.requirePlayer().getTargetBlock(null, 100).location
            else -> {
                val targetPlayer = Bukkit.getPlayer(first)
                if (targetPlayer != null)
                    return targetPlayer.location
                val x = first.toDouble()
                val y = context.popDouble()
                val z = context.popDouble()
                return Location(actor.requirePlayer().world, x, y, z)
            }
        }
    }
}