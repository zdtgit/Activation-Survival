package com.activation.survival.extensions.string

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

val String.legacy: Component
    get() = LegacyComponentSerializer.legacyAmpersand().deserialize(this)

val String.mini: Component
    get() = MiniMessage.miniMessage().deserialize(this)