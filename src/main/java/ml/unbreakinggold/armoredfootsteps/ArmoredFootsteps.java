package ml.unbreakinggold.armoredfootsteps;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmoredFootsteps implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("armoredfootsteps");

    @Override
    public void onInitialize() {
        LOGGER.info("Loaded Armored Footsteps!");
    }
}
