package bioast.mods.gt6m.world;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import techguns.util.BlockUtils;

import java.util.Random;

public abstract class Structure {
    public int minX;
    public int minY;
    public int minZ;
    public int maxX;
    public int maxY;
    public int maxZ;
    public int sizeX;
    public int sizeZ;
    public int centX;
    public int centZ;
    public boolean canSwapXZ;
    public boolean removeJunkOnWorldspawn;
    public static BiomeDictionary.Type[] coldTypes;
    public static BiomeDictionary.Type[] sandTypes;
    public static BiomeDictionary.Type[] netherTypes;
    protected int lootTier;

    public Structure() {
        this(0, 0, 0, 0, 0, 0);
    }

    protected int getStep() {
        return 4;
    }

    public Structure(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.removeJunkOnWorldspawn = false;
        this.lootTier = 0;
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public Structure setXZSize(int x, int z) {
        this.sizeX = x;
        this.sizeZ = z;
        this.centX = (int)((float)this.sizeX / 2.0F);
        this.centZ = (int)((float)this.sizeZ / 2.0F);
        return this;
    }

    public int getSizeX(Random rnd) {
        return this.sizeX;
    }

    public int getSizeZ(Random rnd) {
        return this.sizeZ;
    }

    public Structure setSwapXZ(boolean b) {
        this.canSwapXZ = b;
        return this;
    }

    public abstract void setBlocks(World var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, BlockUtils.BiomeColorType var9, Random var10);

    public static int[] rotatePoint(int x, int z, int direction, int centerX, int centerZ) {
        int a = x - centerX;
        int b = z - centerZ;

        for(int i = 0; i < direction; ++i) {
            int a2 = a;
            a = b;
            b = -a2;
        }

        x = a + centerX;
        z = b + centerZ;
        return new int[]{x, z};
    }

    public void spawnStructureWorldgen(World world, int chunkX, int chunkZ, int sizeX, int sizeY, int sizeZ, Random rnd, BiomeGenBase biome) {
        int direction = rnd.nextInt(4);
        int sizeXr = direction != 0 && direction != 2 ? sizeZ : sizeX;
        int sizeZr = direction != 0 && direction != 2 ? sizeX : sizeZ;
        int centerX = (int)((float)sizeX / 2.0F);
        int centerZ = (int)((float)sizeZ / 2.0F);
        int[] p0 = rotatePoint(0, 0, direction, centerX, centerZ);
        int[] p1 = rotatePoint(sizeX, 0, direction, centerX, centerZ);
        int[] p2 = rotatePoint(0, sizeZ, direction, centerX, centerZ);
        int[] p3 = rotatePoint(sizeX, sizeZ, direction, centerX, centerZ);
        int minX = Math.min(Math.min(p0[0], p1[0]), Math.min(p2[0], p3[0]));
        int minZ = Math.min(Math.min(p0[1], p1[1]), Math.min(p2[1], p3[1]));
        int x = minX + chunkX * 16;
        int z = minZ + chunkZ * 16;
        int offsetX = this.getRotationShiftX(direction);
        int offsetZ = this.getRotationShiftZ(direction);
        int y = BlockUtils.getValidSpawnYArea(world, x + offsetX, z + offsetZ, sizeXr, sizeZr, 3, this.getStep());
        if (y >= 0) {
            if (this.removeJunkOnWorldspawn) {
                BlockUtils.removeJunkInArea(world, x - 1, z - 1, sizeXr + 2, sizeZr + 2);
            }

            this.setBlocks(world, x, y - 1, z, sizeX, sizeY, sizeZ, direction, getBiomeColorTypeFromBiome(biome), rnd);
        }
    }

    protected static BlockUtils.BiomeColorType getBiomeColorTypeFromBiome(BiomeGenBase biome) {
        if (isInBiomeList(coldTypes, biome)) {
            return BlockUtils.BiomeColorType.SNOW;
        } else if (isInBiomeList(sandTypes, biome)) {
            return BlockUtils.BiomeColorType.DESERT;
        } else {
            return isInBiomeList(netherTypes, biome) ? BlockUtils.BiomeColorType.NETHER : BlockUtils.BiomeColorType.WOODLAND;
        }
    }

    private static boolean isInBiomeList(BiomeDictionary.Type[] list, BiomeGenBase biome) {
        for(int i = 0; i < list.length; ++i) {
            if (BiomeDictionary.isBiomeOfType(biome, list[i])) {
                return true;
            }
        }

        return false;
    }

    protected int getRotationShiftX(int direction) {
        return 0;
    }

    protected int getRotationShiftZ(int direction) {
        return 0;
    }

    static {
        coldTypes = new BiomeDictionary.Type[]{BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY};
        BiomeDictionary.Type[] var10000 = new BiomeDictionary.Type[]{BiomeDictionary.Type.SANDY, null, null};
        BiomeDictionary.Type var10003 = BiomeDictionary.Type.SAVANNA;
        var10000[1] = BiomeDictionary.Type.BEACH;
        var10000[2] = BiomeDictionary.Type.MESA;
        sandTypes = var10000;
        netherTypes = new BiomeDictionary.Type[]{BiomeDictionary.Type.NETHER};
    }
}
