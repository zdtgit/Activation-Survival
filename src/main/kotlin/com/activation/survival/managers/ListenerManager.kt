package com.activation.survival.managers

import com.activation.survival.ActivationSurvival
import com.activation.survival.listeners.PlayerListener
import org.bukkit.event.Listener

object ListenerManager {
    private val plugin = ActivationSurvival.instance

    fun init() {
        register(PlayerListener())
    }

    private fun register(listener: Listener) {
        plugin.server.pluginManager.registerEvents(listener, plugin)
    }
}