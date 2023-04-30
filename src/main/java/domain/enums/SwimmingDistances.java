package domain.enums;

/**
 * Available swimming distances for races.
 */
public enum SwimmingDistances {
    _50m,
    _200m,
    _800m,
    _1500m;

    public static SwimmingDistances distanceFromInteger(Integer value) {
        return switch (value) {
            case 1 -> _50m;
            case 2 -> _200m;
            case 3 -> _800m;
            case 4 -> _1500m;
            default -> null;
        };
    }

    public static Integer integerFromDistance(SwimmingDistances swimmingDistance) {
        return switch (swimmingDistance) {
            case _50m -> 1;
            case _200m -> 2;
            case _800m -> 3;
            case _1500m -> 4;
        };
    }
}
