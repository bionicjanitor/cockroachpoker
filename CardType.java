package com.company;

public enum CardType {
    BAT,RAT,TOAD,ROACH,FLY,SCORPION,SPIDER,STINKBUG,gBAT,gRAT,gTOAD,gROACH,gFLY,gSCORPION,gSPIDER,gSTINKBUG,BACK;

    public static String getFilePath(CardType type)
    {
        switch(type)
        {
            case BAT: return "resources/Bat.jpg";
            case RAT: return "resources/Rat.jpg";
            case TOAD: return "resources/Toad.jpg";
            case ROACH: return "resources/CockRoach.jpg";
            case FLY: return "resources/Fly.jpg";
            case SCORPION: return "resources/Scorpion.jpg";
            case SPIDER: return "resources/Spider.jpg";
            case STINKBUG: return "resources/StinkBug.jpg";
            case gBAT: return "resources/Bat_Gray.jpg";
            case gRAT: return "resources/Rat_Gray.jpg";
            case gTOAD: return "resources/Toad_Gray.jpg";
            case gROACH: return "resources/CockRoach_Gray.jpg";
            case gFLY: return "resources/Fly_Gray.jpg";
            case gSCORPION: return "resources/Scorpion_Gray.jpg";
            case gSPIDER: return "resources/Spider_Gray.jpg";
            case gSTINKBUG: return "resources/StinkBug_Gray.jpg";
            case BACK: return "resources/Back.jpg";
            default: return "resources/Back.jpg";
        }

    }


}
