package com.activation.survival

import com.activation.survival.managers.ListenerManager
import org.bukkit.plugin.java.JavaPlugin

class ActivationSurvival: JavaPlugin() {
    override fun onEnable() {
        ListenerManager.init()
    }

    companion object {
        val instance: ActivationSurvival
            get() = getPlugin(ActivationSurvival::class.java)
    }
}