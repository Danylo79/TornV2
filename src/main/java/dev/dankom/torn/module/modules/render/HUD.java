package dev.dankom.torn.module.modules.render;

import dev.dankom.torn.Torn;
import dev.dankom.torn.gui.clickgui.ClickGui;
import dev.dankom.torn.module.base.Category;
import dev.dankom.torn.module.base.Module;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.theme.Theme;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HUD extends Module {

    private List<Integer> fps = new ArrayList<>();

    public HUD() {
        super("HUD", "Shows the Heads Up Display", Category.RENDER, -1, new Color(0, 255, 15), true, true);
        addSetting(new Setting("FPS", this, false));
        addSetting(new Setting("BPS", this, false));
        addSetting(new Setting("Time", this, false));
    }

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void onRender(RenderGameOverlayEvent e) {
        if (!isToggled()) return;

        FontRenderer fontRenderer = mc.fontRendererObj;
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

        GL11.glScaled(2.0, 2.0, 2.0);
        int i = fontRenderer.drawString(Torn.CLIENT_NAME, 2, 2, Theme.getInstance().getColorInt(), true);
        GL11.glScaled(0.5, 0.5, 0.5);

        fontRenderer.drawString(Torn.CLIENT_VERSION, i * 2, fontRenderer.FONT_HEIGHT * 2 - 7, Theme.getInstance().getColorInt(), true);
        fontRenderer.drawString("by " + Torn.CLIENT_AUTHOR, 8, fontRenderer.FONT_HEIGHT * 2 + 2, Theme.getInstance().getColorInt(), true);
        boolean fps = getSetting("FPS").getValBoolean();
        boolean bps = getSetting("BPS").getValBoolean();
        boolean time = getSetting("Time").getValBoolean();
        if (fps) {
            fontRenderer.drawString("FPS: " + Minecraft.getDebugFPS(), 4, res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, ClickGui.getColor(), true);
        }
        if (bps) {
            double currSpeed = Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
            fontRenderer.drawString(String.format("BPS: %.2f", currSpeed), (fps ? 45 : 4), res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 2, ClickGui.getColor(), true);
        }
        if (time) {
            LocalDateTime now = LocalDateTime.now(Clock.systemDefaultZone());
            Calendar cal = Calendar.getInstance();
            String currTime = timeFormat.format(now);
            fontRenderer.drawString(currTime, res.getScaledWidth() - fontRenderer.getStringWidth(currTime), res.getScaledHeight() - fontRenderer.FONT_HEIGHT - 3, ClickGui.getColor(), true);
        }
    }
}
