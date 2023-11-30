package by.bsuir.lab2.controller.data;

public enum EnumRole {
    USER(1), ADMIN(2);
    private final int id;

    EnumRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EnumRole fromId(int id) {
        for (EnumRole e : EnumRole.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
