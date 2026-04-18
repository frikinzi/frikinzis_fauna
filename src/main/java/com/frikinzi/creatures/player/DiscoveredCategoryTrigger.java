package com.frikinzi.creatures.player;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;

public class DiscoveredCategoryTrigger extends SimpleCriterionTrigger<DiscoveredCategoryTrigger.TriggerInstance> {
    public static final ResourceLocation ID = new ResourceLocation("creatures", "discovered_category");

    @Override
    public ResourceLocation getId() { return ID; }

    @Override
    protected TriggerInstance createInstance(JsonObject json,
                                             ContextAwarePredicate player, DeserializationContext context) {
        String category = GsonHelper.getAsString(json, "category", "");
        return new TriggerInstance(player, category);
    }

    public void trigger(ServerPlayer player, String category) {
        this.trigger(player, instance -> instance.matches(category));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final String category;

        public TriggerInstance(ContextAwarePredicate player, String category) {
            super(ID, player);
            this.category = category;
        }

        public boolean matches(String category) {
            return this.category.equals(category);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject json = super.serializeToJson(context);
            json.addProperty("category", this.category);
            return json;
        }
    }
}