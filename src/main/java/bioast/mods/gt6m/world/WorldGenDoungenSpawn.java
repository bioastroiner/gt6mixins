package bioast.mods.gt6m.world;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.BiomeDictionary;
import techguns.worldgen.TGStructureSpawnRegister;
import techguns.worldgen.structures.Structure;

import java.util.Random;

public class WorldGenDoungenSpawn implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == 0) {
            BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(chunkX * 16, chunkZ * 16);
            this.generateSurface(world, random, chunkX, chunkZ, biome);
        }
    }

    private void generateEnd(World world, Random random, int ChunkX, int ChunkZ, BiomeGenBase biome) {
    }

    private void generateNether(World world, Random random, int ChunkX, int ChunkZ, BiomeGenBase biome) {
    }

    private void generateSurface(World world, Random random, int cx, int cz, BiomeGenBase biome) {
        int SPAWNWEIGHT_SMALL = 16;
        int SPAWNWEIGHT_BIG = 64;
        int SPAWNWEIGHT_MEDIUM = 32;
        StructureSize size = null;
        int sizeX;
        int sizeZ;
        if (cx % SPAWNWEIGHT_BIG == 0 && cz % SPAWNWEIGHT_BIG == 0) {
            size = StructureSize.BIG;
        } else if (cx % SPAWNWEIGHT_MEDIUM == 0 && cz % SPAWNWEIGHT_MEDIUM == 0) {
            size = StructureSize.MEDIUM;
        } else if (cx % SPAWNWEIGHT_SMALL == 0 && cz % SPAWNWEIGHT_SMALL == 0) {
            size = StructureSize.SMALL;
        }

        if (size != null) {
            StructureType type = StructureType.LAND;
            if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.OCEAN)) {
                type = StructureType.WATER;
            }

            Structure s = TGStructureSpawnRegister.choseStructure(random, biome, size, type);
            if (s != null) {
                sizeX = s.getSizeX(random);
                sizeZ = s.getSizeZ(random);
                s.spawnStructureWorldgen(world, cx, cz, sizeX, 8, sizeZ, random, biome);
            }
        }

    }

}


