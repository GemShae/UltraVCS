package dfa;

public enum Alphabet {
    //Transition for States
    startSignal,
    brakeHeldSignal,
    seatBeltEngagedSignal,
    parkSelectedSignal,
    accelerateSignal,
    reverseSelectedSignal,
    driveSelectedSignal,
    brakePressedSignal,
    setCruiseControlSignal

    //Transition Function
    /*Alphabet transition(String transition) {
        System.out.println("In Alphabet Transition");
        return switch (transition) {
            case "1" -> startSignal;
            case "2" -> brakeHeldSignal;
            case "3" -> seatBeltEngagedSignal;
            case "4" -> parkSelectedSignal;
            case "5" -> accelerateSignal;
            case "6" -> reverseSelectedSignal;
            case "7" -> driveSelectedSignal;
            case "8" -> brakePressedSignal;
            case "9" -> setCruiseControlSignal;
            default -> null;
        };
    } */
}
