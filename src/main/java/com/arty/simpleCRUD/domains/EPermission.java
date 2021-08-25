package com.arty.simpleCRUD.domains;

public enum EPermission
{
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    ROOM_READ("room:read"),
    ROOM_WRITE("room:write"),
    PRESENTATION_READ("presentation:read"),
    PRESENTATION_WRITE("presentation:write"),
    SCHEDULE_READ("schedule:read"),
    SCHEDULE_WRITE("schedule:write");

    private final String permission;

    EPermission(String permission)
    {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
