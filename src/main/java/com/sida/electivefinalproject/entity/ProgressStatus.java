package com.sida.electivefinalproject.entity;

public enum ProgressStatus {
    UNSTARTED,
    IN_PROGRESS,
    COMPLETED;

    public static int getNumber(ProgressStatus spec) {
        switch (spec) {
            case UNSTARTED:
                return 1;
            case IN_PROGRESS:
                return 2;
            case COMPLETED:
                return 3;
            default:
                return -1;
        }
    }

    public static ProgressStatus getName(int id) {
        switch (id) {
            case 1:
                return ProgressStatus.UNSTARTED;
            case 2:
                return ProgressStatus.IN_PROGRESS;
            case 3:
                return ProgressStatus.COMPLETED;
            default:
                return null;
        }
    }
}
