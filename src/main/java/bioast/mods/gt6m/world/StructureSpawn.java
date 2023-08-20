package bioast.mods.gt6m.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;

public class StructureSpawn {
    Structure structure;
    int spawnWeight;
    ArrayList<BiomeDictionary.Type> biomeWhitelist;
    ArrayList<Integer> dimensionIDs;
    StructureSize size;
    ArrayList<StructureType> allowedTypes;

    public StructureSpawn(Structure structure, int spawnWeight, ArrayList<BiomeDictionary.Type> biomeWhitelist, ArrayList<Integer> dimensionIDs, ArrayList<StructureType> allowedTypes, StructureSize size) {
        this.structure = structure;
        this.spawnWeight = spawnWeight;
        this.biomeWhitelist = biomeWhitelist;
        this.dimensionIDs = dimensionIDs;
        this.allowedTypes = allowedTypes;
        this.size = size;
    }

    public int getWeightForBiome(BiomeGenBase biome, StructureSize size, StructureType type) {
        if (this.size != size) {
            return 0;
        } else if (!this.allowedTypes.contains(type)) {
            return 0;
        } else if (this.biomeWhitelist == null) {
            return this.spawnWeight;
        } else {
            for(int i = 0; i < this.biomeWhitelist.size(); ++i) {
                if (BiomeDictionary.isBiomeOfType(biome, (BiomeDictionary.Type)this.biomeWhitelist.get(i))) {
                    return this.spawnWeight;
                }
            }

            return 0;
        }
    }

    public boolean dimensionMatches(World w) {
        int id = w.provider.dimensionId;
        return this.dimensionIDs.contains(id);
    }
}
