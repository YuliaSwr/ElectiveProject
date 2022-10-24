package com.sida.electivefinalproject.entity;

public enum Specialization {
    TECHNOLOGY,
    ART,
    SCIENCE,
    ECONOMY,
    HUMANITIES;

    public static Specialization getName(int i) {
        switch(i) {
            case 1:
                return Specialization.TECHNOLOGY;
            case 2:
                return Specialization.ART;
            case 3:
                return Specialization.SCIENCE;
            case 4:
                return Specialization.ECONOMY;
            case 5:
                return Specialization.HUMANITIES;
            default:
                return null;
        }
    }

    public static int getNumber(Specialization spec) {
        switch(spec) {
            case TECHNOLOGY:
                return 1;
            case ART:
                return 2;
            case SCIENCE:
                return 3;
            case ECONOMY:
                return 4;
            case HUMANITIES:
                return 5;
            default:
                return -1;
        }
    }
}
