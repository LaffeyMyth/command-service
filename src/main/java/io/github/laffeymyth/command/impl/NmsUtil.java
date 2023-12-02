package io.github.laffeymyth.command.impl;

import org.bukkit.Bukkit;

@SuppressWarnings("unused")
class NmsUtil {

    private final String craftBukkit;
    private final String nms;
    private final String version;

    public NmsUtil() {
        version = Bukkit.getServer().getClass().getName().split("\\.")[3];
        craftBukkit = "org.bukkit.craftbukkit." + version;
        nms = "net.minecraft.server." + version;
    }

    public String getCraftBukkit() {
        return craftBukkit;
    }

    public String getServerVersion() {
        return version;
    }

    public Class<?> getNMS(final String name) {
        try {
            return Class.forName(nms + "." + name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Class<?> getCraftBukkit(final String name) {
        try {
            return Class.forName(craftBukkit + "." + name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
