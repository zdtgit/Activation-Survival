package com.activation.survival.listeners

import com.activation.survival.extensions.string.legacy
import me.clip.placeholderapi.PlaceholderAPI
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRespawnEvent
import kotlin.random.Random

class PlayerListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%")
        val name = player.name

        if (player.hasPlayedBefore()) {
            event.joinMessage("${prefix}${name}&e님이 접속하셨습니다.".legacy)
        } else {
            event.joinMessage("${prefix}${name}&e님이 처음 접속하셨습니다.".legacy)
        }
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player
        val prefix = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%")
        val name = player.name

        event.quitMessage("${prefix}${name}&e님이 접속하셨습니다.".legacy)
    }

    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {
        val player = event.player

        if (!event.isBedSpawn && !event.isAnchorSpawn) {
            val location = generateSafeLocation()

            location.chunk.load()

            event.respawnLocation = location

            player.sendMessage("&c당신의 집 침대 또는 충전된 리스폰 정박기가 없어 임의의 좌표로 리스폰됩니다.".legacy)
        }
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        event.deathMessage(event.deathMessage()?.color(NamedTextColor.RED))
    }

    private fun generateSafeLocation(): Location {
        var location = generateLocation()

        while (!isSafe(location)) {
            location = generateLocation()
        }

        return location
    }

    private fun generateLocation(): Location {
        val world = Bukkit.getWorld("world")!!
        val x = Random.nextInt(-5000, 5000)
        val z = Random.nextInt(-5000, 5000)
        val y = world.getHighestBlockYAt(x, z)

        return Location(world, x.toDouble(), y.toDouble(), z.toDouble())
    }

    private fun isSafe(location: Location): Boolean {
        val blocks = setOf(Material.LAVA, Material.WATER)

        val world = location.world
        val x = location.blockX
        val y = location.blockY
        val z = location.blockZ

        val above = world.getBlockAt(x, y + 1, z)
        val block = location.block
        val below = world.getBlockAt(x, y - 1, z)

        return !blocks.contains(below.type) && block.type == Material.AIR && above.type == Material.AIR
    }
}