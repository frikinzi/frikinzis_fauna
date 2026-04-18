package com.frikinzi.creatures.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Stores Set<String> of discovered birds, e.g. lovebird_3_f (entity type + variant + gender)
public class FieldGuideCapability {
    public static final Capability<FieldGuideCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});
    private final Set<String> discovered = new HashSet<>();

    public boolean discover(String key) {
        //System.out.println(discovered);
        return discovered.add(key);
    }

    public boolean hasDiscoveredAny(String key) {
        boolean result = discovered.stream().anyMatch(k -> k.startsWith(key + "_"));
        return result;
    }

    public Set<String> getAll() {
        return Collections.unmodifiableSet(discovered);
    }

    public void serializeNBT(CompoundTag tag) {
        ListTag list = new ListTag();
        discovered.forEach(s -> list.add(StringTag.valueOf(s)));
        tag.put("discovered", list);
    }

    public void deserializeNBT(CompoundTag tag) {
        discovered.clear();
        tag.getList("discovered", 8).forEach(t -> discovered.add(t.getAsString()));
    }

    public Set<Integer> getDiscoveredVariants(String entityKey) {
        return discovered.stream()
                .filter(k -> k.startsWith(entityKey + "_"))
                .map(k -> Integer.parseInt(k.split("_")[1]))
                .collect(Collectors.toSet());
    }

    public Set<String> getDiscoveredGenders(String entityKey, int variant) {
        String prefix = entityKey + "_" + variant + "_";
        return discovered.stream()
                .filter(k -> k.startsWith(prefix))
                .map(k -> k.substring(prefix.length()))
                .filter(g -> !g.isEmpty())
                .collect(Collectors.toSet());
    }

    public void copyFrom(FieldGuideCapability other) {
        this.discovered.addAll(other.discovered);
    }

    public void replaceAll(Set<String> entries) {
        this.discovered.clear();
        this.discovered.addAll(entries);
    }

}