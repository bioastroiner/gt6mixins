package bioast.mods.gt6m.world.structures;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import team.chisel.init.ChiselBlocks;
import techguns.TGBlocks;
import techguns.util.BlockUtils;
import techguns.util.MBlock;
import bioast.mods.gt6m.world.Structure;

import java.util.ArrayList;
import java.util.Random;

public class FactoryHouseSmall extends Structure {
    static ArrayList<MBlock> blockList = new ArrayList();
    static short[][] blocks;

    public FactoryHouseSmall(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        super(minX, minY, minZ, maxX, maxY, maxZ);
        this.setSwapXZ(true);
    }

    public void setBlocks(World world, int posX, int posY, int posZ, int sizeX, int sizeY, int sizeZ, int direction, BlockUtils.BiomeColorType colorType, Random rnd) {
        int centerX;
        int centerZ;
        if ((sizeX >= this.minX || sizeZ <= this.minX || sizeX < this.minZ) && (sizeZ >= this.minZ || sizeX <= this.minZ || sizeZ < this.minX)) {
            centerX = (int)((float)sizeX / 2.0F);
            centerZ = (int)((float)sizeZ / 2.0F);
        } else {
            direction = (direction + 1) % 4;
            centerZ = (int)((float)sizeX / 2.0F);
            centerX = (int)((float)sizeZ / 2.0F);
        }

        BlockUtils.cleanUpwards(world, blocks, blockList, posX, posY, posZ, centerX, centerZ, direction, 0, 7);
        BlockUtils.placeFoundation(world, blocks, blockList, posX, posY, posZ, centerX, centerZ, direction, 0, 3);
        BlockUtils.placeScannedStructure(world, blocks, blockList, posX, posY, posZ, centerX, centerZ, direction, 0, this.lootTier);
        BlockUtils.placeScannedStructure(world, blocks, blockList, posX, posY, posZ, centerX, centerZ, direction, 1, this.lootTier);
    }

    static {
        blockList.add(new MBlock(TGBlocks.concrete, 5));
        blockList.add(new MBlock(ChiselBlocks.technical, 0));
        blockList.add(new MBlock(ChiselBlocks.factoryblock, 1));
        blockList.add(new MBlock(Blocks.glass_pane, 0));
        blockList.add(new MBlock(ChiselBlocks.technical, 12));
        blockList.add(new MBlock(ChiselBlocks.factoryblock, 9));
        blockList.add(new MBlock(Blocks.chest, 5, true, BlockUtils.BlockType.CHEST));
        blockList.add(new MBlock(TGBlocks.airMarker, 0));
        blockList.add((new MBlock(TGBlocks.lamp01, 4, true, BlockUtils.BlockType.TG)).setPass(1));
        blockList.add((new MBlock(TGBlocks.lamp01, 6, true, BlockUtils.BlockType.TG)).setPass(1));
        blockList.add(new MBlock(ChiselBlocks.factoryblock, 0));
        blockList.add(new MBlock(Blocks.crafting_table, 0));
        blockList.add((new MBlock(TGBlocks.door01, 7, true, BlockUtils.BlockType.DOOR)).setPass(0));
        blockList.add((new MBlock(TGBlocks.door01, 8, true, BlockUtils.BlockType.DOOR)).setPass(1));
        blockList.add((new MBlock(TGBlocks.lamp01, 1, true, BlockUtils.BlockType.TG)).setPass(1));
        blockList.add((new MBlock(TGBlocks.lamp01, 3, true, BlockUtils.BlockType.TG)).setPass(1));
        blockList.add(new MBlock(Blocks.chest, 3, true, BlockUtils.BlockType.CHEST));
        blockList.add((new MBlock(TGBlocks.lamp01, 5, true, BlockUtils.BlockType.TG)).setPass(1));
        blockList.add((new MBlock(TGBlocks.ladder01, 1, true, BlockUtils.BlockType.TG)).setPass(1));
        blockList.add(new MBlock(ChiselBlocks.iron_bars, 6));
        blockList.add((new MBlock(TGBlocks.lamp01, 7, true, BlockUtils.BlockType.TG)).setPass(1));
        blocks = BlockUtils.loadStructureFromFile("factoryBuildingSmall");
    }
}
