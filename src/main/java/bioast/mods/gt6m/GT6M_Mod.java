package bioast.mods.gt6m;

import bioast.mods.gt6m.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import gregapi.api.Abstract_Mod;
import gregapi.api.Abstract_Proxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = GT6M_Mod.MODID, version = GT6M_Mod.VERSION, name = GT6M_Mod.MODNAME,
    acceptedMinecraftVersions = "[1.7.10]", dependencies = "")
public class GT6M_Mod extends Abstract_Mod {

    public static final String MODID = "GRADLETOKEN_MODID";
    public static final String MODNAME = "GRADLETOKEN_MODNAME";
    public static final String VERSION = "GRADLETOKEN_VERSION";
    public static final String GROUPNAME = "GRADLETOKEN_GROUPNAME";

    public static final Logger LOG = LogManager.getLogger(MODID);
    public static gregapi.code.ModData MOD_DATA = new gregapi.code.ModData(MODID, MODNAME);
    public static GT6M_Mod instance;
    @SidedProxy(clientSide = "bioast.mods.gt6m.proxy.ClientProxy", serverSide = "bioast.mods.gt6m.proxy.CommonProxy")
    public static CommonProxy proxy;

//    GT6M_Mod() {
//        instance = this;
//    }
//
//    public static GT6M_Mod getInstance() {
//        return instance;
//    }

    @Override
    public String getModID() {
        return MODID;
    }

    @Override
    public String getModName() {
        return MODNAME;
    }

    @Override
    public String getModNameForLog() {
        return MODNAME;
    }

    @Override
    public Abstract_Proxy getProxy() {
        return proxy;
    }

    // Do not change these 7 Functions. Just keep them this way.
    @cpw.mods.fml.common.Mod.EventHandler
    public final void onPreLoad(cpw.mods.fml.common.event.FMLPreInitializationEvent aEvent) {
        onModPreInit(aEvent);
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public final void onLoad(cpw.mods.fml.common.event.FMLInitializationEvent aEvent) {
        onModInit(aEvent);
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public final void onPostLoad(cpw.mods.fml.common.event.FMLPostInitializationEvent aEvent) {
        onModPostInit(aEvent);
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public final void onServerStarting(cpw.mods.fml.common.event.FMLServerStartingEvent aEvent) {
        onModServerStarting(aEvent);
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public final void onServerStarted(cpw.mods.fml.common.event.FMLServerStartedEvent aEvent) {
        onModServerStarted(aEvent);
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public final void onServerStopping(cpw.mods.fml.common.event.FMLServerStoppingEvent aEvent) {
        onModServerStopping(aEvent);
    }

    @cpw.mods.fml.common.Mod.EventHandler
    public final void onServerStopped(cpw.mods.fml.common.event.FMLServerStoppedEvent aEvent) {
        onModServerStopped(aEvent);
    }

    @Override
    public void onModPreInit2(FMLPreInitializationEvent aEvent) {
        instance = this;
        proxy.preInit(aEvent);
    }

    @Override
    public void onModInit2(FMLInitializationEvent aEvent) {
        proxy.init(aEvent);
    }

    @Override
    public void onModPostInit2(FMLPostInitializationEvent aEvent) {
        proxy.postInit(aEvent);
    }

    @Override
    public void onModServerStarting2(FMLServerStartingEvent aEvent) {
        proxy.serverStarting(aEvent);
    }

    @Override
    public void onModServerStarted2(FMLServerStartedEvent aEvent) {

    }

    @Override
    public void onModServerStopping2(FMLServerStoppingEvent aEvent) {

    }

    @Override
    public void onModServerStopped2(FMLServerStoppedEvent aEvent) {

    }
}
