package team.chisel.client.render.texture;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import team.chisel.api.render.RenderType;
import team.chisel.client.render.QuadHelper;
import team.chisel.client.render.ctx.ModuleBlockRenderContext;
import team.chisel.client.render.type.BlockRenderTypeR;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Texture for R texture types
 */
public class ChiselTextureR extends AbstractChiselTexture<ModuleBlockRenderContext> {

    public ChiselTextureR(BlockRenderTypeR type, TextureAtlasSprite[] sprites) {
        super(type, sprites);
    }

    @Override
    public List<BakedQuad> getSideQuads(EnumFacing side, ModuleBlockRenderContext context) {

        int bound = context.getVariationSize();
        int wid = (int)Math.sqrt(bound);
        Random random = new Random(seed);
        int num = random.nextInt(bound) + 1;
        float interval = 16 / wid;
        int unitsAcross = num % wid;
        int unitsDown = num / wid;
        if (unitsAcross == 0) {
            unitsAcross++;
        }
        if (unitsDown == 0) {
            unitsDown++;
        }
        float maxU = unitsAcross * interval;
        float maxV = unitsDown * interval;
        //Chisel.logger.info("maxU: "+maxU+" maxV: "+maxV);
        List<BakedQuad> toReturn = new ArrayList<BakedQuad>();
        for (EnumFacing f : EnumFacing.values()) {
            toReturn.add(makeQuad(f, r.getDefaultTexture(), new float[]{maxU - interval, maxV - interval, maxU, maxV}));
        }
        return toReturn;
    }
}