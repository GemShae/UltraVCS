package dfa;

public enum States {
    //Final Set of States
    OFFPOSITION(true),
    IGNITIONONPOSITION(false),
    HOLDPOSITION(false),
    ENGINESTARTEDPOSITION(false),
    STATIONARYPOSITION(false),
    INREVERSEMOTIONPOSITION(false),
    INFORWARDMOTIONPOSITION(false),
    CRUISECONTROLENGAGEDPOSITION(false);

    //To determine accept States from reject states
    final boolean ACCEPT;

    //Constructor for state. Every state is either accepting or not.
    States(boolean accept) {
        this.ACCEPT = accept;
    }

    //State Function that returns the current state
   public States stateCheck(String currentState) {
       System.out.println("In State Check");
        return switch (currentState) {
            case "OFF" -> OFFPOSITION;
            case "IGNITION-ON" -> IGNITIONONPOSITION;
            case "HOLD"-> HOLDPOSITION;
            case "ENGINE-STARTED" -> ENGINESTARTEDPOSITION;
            case "STATIONARY" -> STATIONARYPOSITION;
            case "IN-REVERSE" -> INREVERSEMOTIONPOSITION;
            case "IN-FORWARD" -> INFORWARDMOTIONPOSITION;
            case "CRUISE CONTROL" -> CRUISECONTROLENGAGEDPOSITION;
            default -> null;
        };
    }
}
