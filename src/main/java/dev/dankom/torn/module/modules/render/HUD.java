package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.Torn;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.theme.Theme;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class HUD extends Module {

    public HUD() {
        super("HUD", Category.RENDER, -1, new Color(0, 255, 15), true, true);
    }

    @Override
    public void onRender(RenderGameOverlayEvent e) {
        if (!isToggled()) return;

        FontRenderer fontRenderer = mc.fontRendererObj;

        GL11.glScaled(2.0, 2.0, 2.0);
        int i = fontRenderer.drawString(Torn.CLIENT_NAME, 2, 2, Theme.getInstance().getColor(), true);
        GL11.glScaled(0.5, 0.5, 0.5);

        fontRenderer.drawString(Torn.CLIENT_VERSION, i * 2, fontRenderer.FONT_HEIGHT * 2 - 7, Theme.getInstance().getColor(), true);
        fontRenderer.drawString("by " + Torn.CLIENT_AUTHOR, 8, fontRenderer.FONT_HEIGHT * 2 + 2, Theme.getInstance().getColor(), true);
    }
}
