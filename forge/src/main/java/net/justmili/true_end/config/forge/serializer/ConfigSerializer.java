package net.justmili.true_end.config.forge.serializer;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class ConfigSerializer {
	private final File configFile;
	private final String extension;

	public ConfigSerializer(String configFileName, String extension) {
		this.extension = extension;
		extension = "." + extension;
		this.configFile = FMLPaths.CONFIGDIR.get().resolve(configFileName.endsWith(extension) ? configFileName : configFileName + extension).toFile();
	}

	protected File getConfigFile() {
		return this.configFile;
	}

	abstract public void serialize(Map<String, Object> entries);

	abstract public Map<String, Object> deserialize();

	protected void createConfigFile() {
		try {
			this.getConfigFile().createNewFile();
		} catch (IOException ignored) {}
	}

	public String getMessage() {
		return "Loaded " + this.extension.toUpperCase() + " config file " + this.getConfigFile().toPath();
	}
}