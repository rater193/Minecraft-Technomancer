package net.rater193.technomancer.utility;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import static net.rater193.technomancer.utility.ModLangs.KEYS.KEY_DEFRAGMENT_RAM;
import static net.rater193.technomancer.utility.ModLangs.KEYS.KEY_CATEGORY_TECHNOMANCER;

public class KeyBinding {

    public static final KeyMapping DEFRAGMENT_RAM_KEY = new KeyMapping(KEY_DEFRAGMENT_RAM, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_BACKSLASH, KEY_CATEGORY_TECHNOMANCER);

}
