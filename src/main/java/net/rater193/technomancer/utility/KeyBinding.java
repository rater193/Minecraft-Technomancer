package net.rater193.technomancer.utility;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {

    public static final String KEY_CATEGORY_TECHNOMANCER = "key.category.technomancer.keybinds";
    public static final String KEY_DEFRAGMENT_RAM = "key.technomancer.defragmentram";

    public static final KeyMapping DEFRAGMENT_RAM_KEY = new KeyMapping(KEY_DEFRAGMENT_RAM, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_BACKSLASH, KEY_CATEGORY_TECHNOMANCER);

}
