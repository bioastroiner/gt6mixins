package bioast.mods.gt6m.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import techguns.worldgen.structures.FactoryHouseSmall;

import java.util.ArrayList;
import java.util.Random;

public class StructureSpawnRegister {
    static ArrayList<StructureSpawn> spawns_small = new ArrayList();
    static ArrayList<StructureSpawn> spawns_big = new ArrayList();
    static ArrayList<StructureSpawn> spawns_medium = new ArrayList();
    static ArrayList<Integer> OVERWORLD = new ArrayList(1);
    static ArrayList<StructureType> LAND = new ArrayList(1);
    static ArrayList<StructureType> WATER = new ArrayList(1);
    static ArrayList<BiomeDictionary.Type> DESERTS_ONLY = new ArrayList(2);

    public static Structure choseStructure(Random rnd, BiomeGenBase biome, StructureSize size, StructureType type) {
        int totalweight = 0;
        ArrayList spawns;
        if (size == StructureSize.BIG) {
            spawns = spawns_big;
        } else if (size == StructureSize.MEDIUM) {
            spawns = spawns_medium;
        } else {
            spawns = spawns_small;
        }

        int roll;
        for(roll = 0; roll < spawns.size(); ++roll) {
            totalweight += ((StructureSpawn)spawns.get(roll)).getWeightForBiome(biome, size, type);
        }

        if (totalweight > 0) {
            roll = rnd.nextInt(totalweight) + 1;
            int weight = 0;

            for(int i = 0; i < spawns.size(); ++i) {
                weight += ((StructureSpawn)spawns.get(i)).getWeightForBiome(biome, size, type);
                if (roll <= weight) {
                    return ((StructureSpawn)spawns.get(i)).structure;
                }
            }
        }

        return null;
    }

    static {
        OVERWORLD.add(0);
        LAND.add(StructureType.LAND);
        WATER.add(StructureType.WATER);
        DESERTS_ONLY.add(BiomeDictionary.Type.SANDY);
        DESERTS_ONLY.add(BiomeDictionary.Type.WASTELAND);
        spawns_small.add(new StructureSpawn((new FactoryHouseSmall(8, 0, 7, 9, 5, 10)).setXZSize(11, 10), 1, (ArrayList)null, OVERWORLD, LAND, StructureSize.SMALL));
//        spawns_small.add(new StructureSpawn((new SmallTrainstation(0, 0, 0, 0, 0, 0)).setXZSize(11, 12), 1, (ArrayList)null, OVERWORLD, LAND, StructureSize.SMALL));
//        spawns_medium.add(new StructureSpawn((new HouseMedium(16, 12, 16, 16, 12, 16)).setXZSize(16, 16), 1, (ArrayList)null, OVERWORLD, LAND, StructureSize.MEDIUM));
//        spawns_medium.add(new StructureSpawn((new AlienBugNestStructure()).setXZSize(4, 4), 1, DESERTS_ONLY, OVERWORLD, LAND, StructureSize.MEDIUM));
//        spawns_medium.add(new StructureSpawn((new BigBunker(32, 14, 17, 32, 14, 17)).setXZSize(32, 17), 1, (ArrayList)null, OVERWORLD, LAND, StructureSize.MEDIUM));
//        spawns_big.add(new StructureSpawn(new MilitaryBaseStructure(0, 0, 0, 0, 0, 0), 1, (ArrayList)null, OVERWORLD, LAND, StructureSize.BIG));
//        spawns_big.add(new StructureSpawn((new AircraftCarrierNew(54, 24, 21, 54, 24, 21)).setXZSize(54, 21), 1, (ArrayList)null, OVERWORLD, WATER, StructureSize.BIG));
//        spawns_big.add(new StructureSpawn((new Submarine()).setXZSize(33, 7), 1, (ArrayList)null, OVERWORLD, WATER, StructureSize.BIG));
//    }
}
