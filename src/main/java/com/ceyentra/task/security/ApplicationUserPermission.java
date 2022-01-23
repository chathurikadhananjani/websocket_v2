package com.ceyentra.task.security;

public enum ApplicationUserPermission {
    ITEM_READ("item:read"),
    ITEM_WRITE("item:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
