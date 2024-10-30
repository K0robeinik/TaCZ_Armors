package com.korobeinik.taczarmors.content;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PoweredCombatArmorItem extends VariedCombatArmorItem{
    private final Multimap<Attribute, AttributeModifier> defModifiers;
    private final PoweredCombatArmorMaterial armorMaterial;
    public PoweredCombatArmorItem(PoweredCombatArmorMaterial armorMaterial, Type type, String[] variant) {
        this(armorMaterial.getName(), armorMaterial, type, variant);
    }

    public PoweredCombatArmorItem(String suitName, PoweredCombatArmorMaterial armorMaterial, Type type, String[] variant) {
        super(suitName, armorMaterial, type, variant);
        this.armorMaterial = armorMaterial;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIERS[type.ordinal()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", armorMaterial.getDefenseForType(this.getType()), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", armorMaterial.getToughness(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Health bonus", armorMaterial.getHpBonus(this.getType()), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Speed", armorMaterial.getSpeed(this.getType()), AttributeModifier.Operation.MULTIPLY_TOTAL));
        builder.put(ForgeMod.SWIM_SPEED.get(), new AttributeModifier(uuid, "Swim Speed", armorMaterial.getSwimSpeed(this.getType()), AttributeModifier.Operation.MULTIPLY_TOTAL));
        builder.put(ForgeMod.STEP_HEIGHT_ADDITION.get(), new AttributeModifier(uuid, "Swim Speed", armorMaterial.getStepHeight(this.getType()), AttributeModifier.Operation.ADDITION));
        if (armorMaterial.getKnockbackResistance() > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", armorMaterial.getKnockbackResistance(), AttributeModifier.Operation.ADDITION));
        }
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Attack Damage", armorMaterial.getAttackDamage(this.getType()), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Attack Speed", armorMaterial.getAttackSpeed(this.getType()), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_KNOCKBACK, new AttributeModifier(uuid, "Attack Knockback", armorMaterial.getAttackKnockback(this.getType()), AttributeModifier.Operation.MULTIPLY_BASE));
        defModifiers = builder.build();
    }

    @Override
    public @NotNull PoweredCombatArmorMaterial getMaterial() {
        return this.armorMaterial;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot p_40390_) {
        return p_40390_ == this.type.getSlot() ? this.defModifiers : super.getDefaultAttributeModifiers(p_40390_);
    }
}
