package com.korobeinik.taczarmors.content;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.korobeinik.taczarmors.client.render.CombatArmorRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class CombatArmorItem extends ArmorItem implements GeoItem {
    private final Multimap<Attribute, AttributeModifier> defModifiers;
    protected static final UUID[] ARMOR_MODIFIERS = new UUID[]{
            UUID.fromString("2AD3F246-FEE2-4E67-B886-69FD380BB150"),
            UUID.fromString("0F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("E8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("945DB27C-C624-495F-8C9F-6020A9A58B6B")
    };
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected final String suitName;
    private final CombatArmorMaterials combatArmorMaterial;

    public CombatArmorItem(CombatArmorMaterials armorMaterial, Type type) {
        this(armorMaterial.getName(), armorMaterial, type);
    }

    public CombatArmorItem(String suitName, CombatArmorMaterials armorMaterial, Type type) {
        super(armorMaterial, type, new Item.Properties());
        this.combatArmorMaterial = armorMaterial;
        this.suitName = suitName;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[type.ordinal()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", armorMaterial.getDefenseForType(this.getType()), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", armorMaterial.getToughness(), AttributeModifier.Operation.ADDITION));
        if (armorMaterial.getKnockbackResistance() > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", armorMaterial.getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
        }
        defModifiers = builder.build();
    }

    @Override
    public @NotNull CombatArmorMaterials getMaterial(){
        return this.combatArmorMaterial;
    }

    public String getSuitName() {
        return this.suitName;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot p_40390_) {
        return p_40390_ == this.type.getSlot() ? this.defModifiers : super.getDefaultAttributeModifiers(p_40390_);
    }
//item.color
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @javax.annotation.Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            list.add(Component.translatable("item.tooltip.taczarmors.bonuses"));
            for (CombatArmorBonus bonus: CombatArmorBonus.values()) {
                appendBonus(bonus, list);
            }
        }
        else {
            list.add(Component.translatable("item.tooltip.taczarmors.bonuses_press_shift"));
        }
        super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
    }

    private void appendBonus(CombatArmorBonus bonus, List<Component> list){
        if (this.getMaterial().getBonusForType(bonus, type)!=0){
            list.add(Component.literal(bonus.name() + ": " + this.getMaterial().getBonusForType(bonus, type)).withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null) this.renderer = new CombatArmorRenderer(suitName);


                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, 20, animationState -> {
            animationState.setAnimation(DefaultAnimations.IDLE);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "minecraft:textures/models/armor/diamond_layer_1.png";
    }
}
