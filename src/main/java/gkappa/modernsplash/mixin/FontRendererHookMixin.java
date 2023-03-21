package gkappa.modernsplash.mixin;

import bre.smoothfont.FontRendererHook;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FontRendererHook.class, remap = false)
public class FontRendererHookMixin {
    @Shadow
    public FontRenderer fontRenderer;
    @Shadow
    private void removeFontRendererHook() {}
    @Inject(method = "initAfterConfigLoaded(Z)V", at = @At("HEAD"))
    private void cancelSmoothFont(boolean deferredInit, CallbackInfo ci) {
        String cls = this.fontRenderer.getClass().getName();
        if (cls.equals("gkappa.modernsplash.CustomSplash.SplashFontRenderer")) {
            this.removeFontRendererHook();
        }
    }
}
