package me.i234.gangamlorder.learning1.object;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TemporaryObject {
    private static final Map<UUID, Integer> temporary = new HashMap<>();

    public static void registerTask(UUID uniqueID, int currentY) {
        temporary.put(uniqueID, currentY);
    }

    public static int getY(UUID uuid) {
        if (temporary.get(uuid) == null) {
            return -1;
        }
        return temporary.get(uuid);
    }

    public static void updateY(UUID uuid, int newY) {
        temporary.replace(uuid, newY);
    }


}
