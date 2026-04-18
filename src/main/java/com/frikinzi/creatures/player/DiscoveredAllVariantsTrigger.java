package com.frikinzi.creatures.player;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class DiscoveredAllVariantsTrigger extends SimpleCriterionTrigger<DiscoveredAllVariantsTrigger.TriggerInstance> {
    public static final ResourceLocation ID = new ResourceLocation("creatures", "discovered_all_variants");

    @Override
    public ResourceLocation getId() { return ID; }

    @Override
    protected TriggerInstance createInstance(JsonObject json,
                                             ContextAwarePredicate player, DeserializationContext context) {
        String entityKey = json.has("entity_key") ? json.get("entity_key").getAsString() : "";
        return new TriggerInstance(player, entityKey);
    }

    public void trigger(ServerPlayer player, String entityKey) {
        this.trigger(player, instance -> instance.matches(entityKey));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final String entityKey;

        public TriggerInstance(ContextAwarePredicate player, String entityKey) {
            super(ID, player);
            this.entityKey = entityKey;
        }

        public boolean matches(String entityKey) {
            return this.entityKey.equals(entityKey);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject json = super.serializeToJson(context);
            json.addProperty("entity_key", this.entityKey);
            return json;
        }
    }
}
