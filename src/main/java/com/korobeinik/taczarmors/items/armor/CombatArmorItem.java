package com.korobeinik.taczarmors.items.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.korobeinik.taczarmors.client.render.CombatArmorRenderer;
import com.korobeinik.taczarmors.init.AttributeInit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.ForgeMod;
import net.puffish.attributesmod.AttributesMod;
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
    protected final String modelName;
    private final CombatArmorMaterials combatArmorMaterial;

    public CombatArmorItem(CombatArmorMaterials armorMaterial, Type type) {
        this(armorMaterial.getName(), armorMaterial, type);
    }

    public CombatArmorItem(String suitName, CombatArmorMaterials armorMaterial, Type type) {
        this(suitName, armorMaterial.getName(), armorMaterial, type);
    }

    public CombatArmorItem(String suitName, String modelName, CombatArmorMaterials armorMaterial, Type type) {
        super(armorMaterial, type, new Item.Properties());
        this.combatArmorMaterial = armorMaterial;
        this.suitName = suitName;
        this.modelName = modelName;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[type.ordinal()];
        if (armorMaterial.getDefenseForType(this.type) > 0)
            builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", armorMaterial.getDefenseForType(this.type), AttributeModifier.Operation.ADDITION));
        if (armorMaterial.getToughness() > 0)
            builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", armorMaterial.getToughness(), AttributeModifier.Operation.ADDITION));
        if (armorMaterial.getKnockbackResistance() > 0)
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", armorMaterial.getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
        putAttribute(builder, Attributes.MOVEMENT_SPEED, uuid, CombatArmorBonus.SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL);
        putAttribute(builder, Attributes.MAX_HEALTH, uuid, CombatArmorBonus.HEALTH, AttributeModifier.Operation.ADDITION);
        putAttribute(builder, Attributes.ATTACK_DAMAGE, uuid, CombatArmorBonus.ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION);
        putAttribute(builder, Attributes.ATTACK_SPEED, uuid, CombatArmorBonus.ATTACK_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL);
        putAttribute(builder, Attributes.ATTACK_KNOCKBACK, uuid, CombatArmorBonus.ATTACK_KNOCKBACK, AttributeModifier.Operation.ADDITION);
        putAttribute(builder, ForgeMod.STEP_HEIGHT_ADDITION.get(), uuid, CombatArmorBonus.STEP_HEIGHT, AttributeModifier.Operation.ADDITION);
        putAttribute(builder, ForgeMod.SWIM_SPEED.get(), uuid, CombatArmorBonus.SWIM_SPEED,  AttributeModifier.Operation.MULTIPLY_TOTAL);
        putAttribute(builder, AttributesMod.JUMP, uuid, CombatArmorBonus.JUMPHEIGHT, AttributeModifier.Operation.MULTIPLY_TOTAL);
        putAttribute(builder, AttributeInit.FALL_HEIGHT.get(), uuid, CombatArmorBonus.FALLHEIGHT, AttributeModifier.Operation.ADDITION);
        putAttribute(builder, AttributesMod.SPRINTING_SPEED, uuid, CombatArmorBonus.SPRINT_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL);
        putAttribute(builder, AttributesMod.MINING_SPEED, uuid, CombatArmorBonus.MINING_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL);
        putAttribute(builder, AttributesMod.STAMINA, uuid, CombatArmorBonus.STAMINA, AttributeModifier.Operation.MULTIPLY_TOTAL);
        putAttribute(builder, AttributesMod.RESISTANCE, uuid, CombatArmorBonus.RESISTANCE, AttributeModifier.Operation.MULTIPLY_TOTAL);
        defModifiers = builder.build();
    }

    private void putAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, Attribute key, UUID uuid, CombatArmorBonus bonus, AttributeModifier.Operation operation) {
        double value = this.combatArmorMaterial.getBonusForType(bonus, this.type);
        if (value!=0) builder.put(key, new AttributeModifier(uuid, bonus.name(), value, operation));
    }

    @Override
    public @NotNull CombatArmorMaterials getMaterial(){
        return this.combatArmorMaterial;
    }

    public String getSuitName() {
        return this.suitName;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == this.type.getSlot() ? this.defModifiers : ImmutableMultimap.of();
    }
//item.color
    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @javax.annotation.Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            list.add(Component.translatable("item.tooltip.taczarmors.bonuses").withStyle(ChatFormatting.GRAY));
            appendAttributes(pStack, defModifiers, list);
        }
        else {
            super.appendHoverText(pStack, pLevel, list, pIsAdvanced);
            appendMain(pStack, pLevel, list, pIsAdvanced);
            list.add(Component.empty());
            list.add(Component.translatable("item.tooltip.taczarmors.bonuses_press_shift").withStyle(ChatFormatting.GRAY));
        }
    }

    protected void appendMain(@NotNull ItemStack pStack, @javax.annotation.Nullable Level pLevel, @NotNull List<Component> list, @NotNull TooltipFlag pIsAdvanced){}

    private void appendAttributes(ItemStack stack, Multimap<Attribute, AttributeModifier> attributeMap, List<Component> list) {
        ChatFormatting color = stack.getItem() instanceof PoweredCombatArmorItem poweredItem && !poweredItem.isCharged(stack) ? ChatFormatting.DARK_GRAY : ChatFormatting.BLUE;
        attributeMap.keys().forEach(
                attribute -> attributeMap.get(attribute).stream().findFirst().ifPresent(
                        attributeModifier -> list.add(Component.translatable(attribute.getDescriptionId()).append(": ").append(String.valueOf((float) attributeModifier.getAmount())).withStyle(color))
                )
        );
    }

    @Override
    public int getDefaultTooltipHideFlags(@NotNull ItemStack stack) {
        return 66;
    }

    public boolean tryConsumeEnergy(ItemStack stack, int amount) {
        return true;
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
