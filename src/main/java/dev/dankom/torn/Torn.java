package dev.dankom.torn;

import dev.dankom.torn.alt.AltManager;
import dev.dankom.torn.command.CommandManager;
import dev.dankom.torn.file.FileManager;
import dev.dankom.torn.gui.clickgui.ClickGui;
import dev.dankom.torn.settings.SettingsManager;
import dev.dankom.torn.listeners.ClickListener;
import dev.dankom.torn.module.ModuleManager;
import dev.dankom.torn.util.wrapper.Wrapper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModDisabledEvent;

@Mod(modid = Torn.MODID, version = Torn.VERSION)
public class Torn
{
    public static final String MODID = "torn";
    public static final String VERSION = "1.0";

    private final static Wrapper wrapper = new Wrapper();
    private final static SettingsManager settingsManager = new SettingsManager();
    private final static ModuleManager moduleManager = new ModuleManager();
    private final static ClickGui clickGui = new ClickGui();
    private final static AltManager altManager = new AltManager();
    private static FileManager fileManager = new FileManager();
    private static CommandManager commandManager = new CommandManager();

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        getFileManager().load();

        MinecraftForge.EVENT_BUS.register(new ClickListener());

        altManager.login(altManager.createAlt("DankomWow"));
    }

    public static ModuleManager getModuleManager() {
        return moduleManager;
    }

    public static SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public static ClickGui getClickGui() {
        return clickGui;
    }

    public static AltManager getAltManager() {
        return altManager;
    }

    public static Wrapper getWrapper() {
        return wrapper;
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

    public static void save() {
        getFileManager().save();
        System.out.println("Saving . . .");
    }
}
