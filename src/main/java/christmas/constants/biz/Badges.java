package christmas.constants.biz;

public enum Badges {
    SANTA("산타", 20_000),
    TREE("트리", 15_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String name;
    private final int condition;

    Badges(String name, int condition) {
        this.name = name;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public int getCondition() {
        return condition;
    }

    public static Badges getContionsBadge(int amount) {
        for (Badges badge : values()) {
            if (badge.condition <= amount) {
                return badge;
            }
        }
        return NONE;
    }
}
