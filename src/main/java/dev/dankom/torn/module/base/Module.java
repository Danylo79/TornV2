package dev.dankom.torn.module.base;

import dev.dankom.torn.Torn;
import dev.dankom.torn.event.events.MotionUpdateEvent;
import dev.dankom.torn.event.events.PacketEvent;
import dev.dankom.torn.event.events.Render2DEvent;
import dev.dankom.torn.event.events.UpdateEvent;
import dev.dankom.torn.gui.notification.Notification;
import dev.dankom.torn.gui.notification.NotificationManager;
import dev.dankom.torn.gui.notification.NotificationType;
import dev.dankom.torn.module.ModuleManager;
import dev.dankom.torn.settings.Setting;
import dev.dankom.torn.settings.SettingsManager;
import dev.dankom.torn.util.wrapper.Invoker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;

public class Module {
    private final String name;
    private String enabledModName;
    private String description;
    private final Category category;
    private int key;
    private final Color color;
    private boolean showInClickGui;
    private boolean showInEnabledMods;
    private final ModuleManager moduleManager;
    private boolean toggled;
    public boolean visible = true;
    public Minecraft mc;
    public SettingsManager settingsManager;
    public Invoker invoker;

    public Module(String name, String description, Category category, int key, Color color, boolean showInClickGui, boolean showInEnabledMods) {
        this.name = name;
        this.enabledModName = name;
        this.description = description;
        this.category = category;
        this.key = key;
        this.color = color;
        this.showInClickGui = showInClickGui;
        this.showInEnabledMods = showInEnabledMods;
        this.toggled = false;
        this.mc = Minecraft.getMinecraft();
        this.invoker = Torn.getWrapper().getInvoker();
        this.moduleManager = Torn.getModuleManager();
        this.settingsManager = Torn.getSettingsManager();
    }

    //Events
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }
    public void onToggle() {}
    public void onTick() {}
    public void onRenderTick() {}
    public void onRender() {}
    public void onRender(RenderGameOverlayEvent e) {}
    public void onUpdate(UpdateEvent e) {}
    public void onMove(MotionUpdateEvent e) {}
    public void onPacket(PacketEvent e) {}
    public void onRender2D(Render2DEvent e) {}
    public void onEvent(Event event) {}
    //

    //Getters and Setters
    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKeyCode(int key) {
        this.key = key;
        Torn.save();
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public boolean isShowInClickGui() {
        return showInClickGui;
    }
    //

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        onToggle();
        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
        Torn.save();
    }

    public void toggle() {
        setToggled(!isToggled());
    }

    public Setting getSetting(String name) {
        return settingsManager.getSetting(this, name);
    }

    public void addSetting(Setting s) {
        Torn.getSettingsManager().rSetting(s);
    }

    public String getEnabledModName() {
        return enabledModName;
    }

    public void setEnabledModName(String enabledModName) {
        this.enabledModName = enabledModName;
    }

    public boolean isShowInEnabledMods() {
        return showInEnabledMods;
    }

    public boolean isInitialized() {
        return mc != null;
    }

    public FontRenderer fontRenderer() {
        return mc.fontRendererObj;
    }

    public Color getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    @SubscribeEvent
    public void render(RenderGameOverlayEvent e) {
        if (!isInitialized()) { return; }
        onRender();
        onRender(e);
    }

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent e) {
        if (!isInitialized()) { return; }
        onTick();
    }

    public void setHide(boolean hide) {
        showInEnabledMods = hide;
    }

    public boolean isHidden() {
        return showInEnabledMods;
    }

    @SubscribeEvent
    public void update(UpdateEvent e) {
        onUpdate(e);
    }

    @SubscribeEvent
    public void move(MotionUpdateEvent e) {
        onMove(e);
    }

    @SubscribeEvent
    public void packet(PacketEvent e) {
        onPacket(e);
    }

    @SubscribeEvent
    public void render2D(Render2DEvent e) {
        onRender2D(e);
    }

    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent e) {
        onRenderTick();
    }

    @SubscribeEvent
    public void event(Event event) {
        onEvent(event);
    }
}
