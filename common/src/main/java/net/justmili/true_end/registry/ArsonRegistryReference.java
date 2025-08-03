package net.justmili.true_end.registry;

public class ArsonRegistryReference
{
    private int registryIndex;
    public ArsonRegistry.ArsonRegistryRegistrationResult arsonRegistryRegistrationResult;

    ArsonRegistryReference(ArsonRegistry.ArsonRegistryRegistrationResult arsonRegistryRegistrationResult, int registryIndex) {
        this.registryIndex = registryIndex;
        this.arsonRegistryRegistrationResult = arsonRegistryRegistrationResult;
    }

    int get() {
        return registryIndex;
    }

    public ArsonRegistry.ArsonRegistryRegistrationResult getArsonRegistryRegistrationResult() {
        return arsonRegistryRegistrationResult;
    }
}
