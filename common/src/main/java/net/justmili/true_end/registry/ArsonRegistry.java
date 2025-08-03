package net.justmili.true_end.registry;

import net.justmili.true_end.TrueEndCommon;
import net.minecraft.world.level.block.Block;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ArsonRegistry {

    private HashMap<Block, Arsonabilty> items;

    private ArsonRegistry() {
        items = new HashMap<>();
    }

    public static ArsonRegistry createInstance() {
        try {
            return ArsonRegistry.class.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            TrueEndCommon.LOGGER.error("Arson registry constructor initiation error", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            TrueEndCommon.LOGGER.error("Arson registry constructor access error", e);
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            TrueEndCommon.LOGGER.error("Arson registry constructor invocation error", e);
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            TrueEndCommon.LOGGER.error("Arson registry doesn't have to seem a constructor... strange", e);
            throw new RuntimeException(e);
        }
    }

    private ArsonRegistryRegistrationResult registerBlock(Block block, Arsonabilty arsonabilty) {
        if (items.containsKey(block)) {
            return ArsonRegistryRegistrationResult.ARSON_REGISTRY_REGISTRATION_RESULT_FAILURE;
        }
        items.put(block, arsonabilty);
        return ArsonRegistryRegistrationResult.ARSON_REGISTRY_REGISTRATION_RESULT_SUCCESS;
    }

    public ArsonRegistryRegistrationResult registerBlockEntry(Block block, Arsonabilty arsonabilty) {
        Object arsonRegistryRegistrationResult;
        try {
            Method registryMethod = ArsonRegistry.class.getDeclaredMethod("registerBlock", Block.class, Arsonabilty.class);
            registryMethod.setAccessible(true);
            Object registry = this;
            arsonRegistryRegistrationResult = registryMethod.invoke(registry ,block, arsonabilty);
        } catch (NoSuchMethodException e) {
            TrueEndCommon.LOGGER.error("Arson Registry doesn't have a registerBlock function... why???", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            TrueEndCommon.LOGGER.error("Couldn't Invoke Arson Registry registration function", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            TrueEndCommon.LOGGER.error("No permission to Invoke Arson Registry registration function", e);
            throw new RuntimeException(e);
        }
        if (arsonRegistryRegistrationResult.equals(ArsonRegistryRegistrationResult.ARSON_REGISTRY_REGISTRATION_RESULT_SUCCESS)) {
            return ArsonRegistryRegistrationResult.ARSON_REGISTRY_REGISTRATION_RESULT_SUCCESS;
        }
        if (arsonRegistryRegistrationResult.equals(ArsonRegistryRegistrationResult.ARSON_REGISTRY_REGISTRATION_RESULT_FAILURE)) {
            return ArsonRegistryRegistrationResult.ARSON_REGISTRY_REGISTRATION_RESULT_FAILURE;
        }
        return ArsonRegistryRegistrationResult.ARSON_REGISTRY_REGISTRATION_RESULT_FAILURE;
    }

    public Arsonabilty getRegisteredBlockItem(Block block) {
        try {
            // Access the private 'items' field
            java.lang.reflect.Field itemsField = ArsonRegistry.class.getDeclaredField("items");
            itemsField.setAccessible(true);
            Object itemsMap = itemsField.get(this);

            // Get the 'get' method from the HashMap class
            Method getMethod = HashMap.class.getDeclaredMethod("get", Object.class);
            getMethod.setAccessible(true);

            // Invoke the 'get' method reflectively
            return (Arsonabilty) getMethod.invoke(itemsMap, block);
        } catch (NoSuchFieldException e) {
            TrueEndCommon.LOGGER.error("Could not find 'items' field in ArsonRegistry", e);
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            TrueEndCommon.LOGGER.error("No access to 'items' field or get() method", e);
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            TrueEndCommon.LOGGER.error("HashMap.get() method not found", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            TrueEndCommon.LOGGER.error("Error invoking get() on HashMap", e);
            throw new RuntimeException(e);
        }
    }

    public static class Arsonabilty {

        public int encouragement;
        public int flammability;

        public Arsonabilty(int encouragement, int flammability) {
            this.encouragement = encouragement;
            this.flammability = flammability;
        }

    }

    public enum ArsonRegistryRegistrationResult {
        ARSON_REGISTRY_REGISTRATION_RESULT_SUCCESS,
        ARSON_REGISTRY_REGISTRATION_RESULT_FAILURE,
        ARSON_REGISTRY_REGISTRATION_RESULT_UNKNOWN_RETURN_VALUE
    }

}