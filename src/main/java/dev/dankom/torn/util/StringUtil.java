package dev.dankom.torn.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class StringUtil {
    public static void drawString(String s, Color color, double size, int x, int y) {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        GL11.glPushMatrix();
        GL11.glScaled(size, size, size);
        fontRenderer.drawString(s, x, y, color.getRGB(), true);
        GL11.glPopMatrix();
    }
}
