package electrodynamics.client;

import electrodynamics.Electrodynamics;
import electrodynamics.IElectrodynamicsProxy;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IElectrodynamicsProxy {

    @Override
    public void preInit() {}

    @Override
    public void init() {
        Electrodynamics.renderIdFull = RenderingRegistry.getNextAvailableRenderId();

        Electrodynamics.renderIdFlat = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void postInit() {}
}