package domain.enums;

/**
 * Available swimming styles for races.
 */
public enum SwimmingStyles {
    _MIXED,
    _FREESTYLE,
    _BUTTERFLY,
    _BACKSTROKE;

    public static SwimmingStyles styleFromInteger(Integer value) {
        return switch (value) {
            case 1 -> _MIXED;
            case 2 -> _FREESTYLE;
            case 3 -> _BUTTERFLY;
            case 4 -> _BACKSTROKE;
            default -> null;
        };
    }

    public static Integer integerFromStyle(SwimmingStyles swimmingDistance) {
        return switch (swimmingDistance) {
            case _MIXED -> 1;
            case _FREESTYLE -> 2;
            case _BUTTERFLY -> 3;
            case _BACKSTROKE -> 4;
        };
    }
}
