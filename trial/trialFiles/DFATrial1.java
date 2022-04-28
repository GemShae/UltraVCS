package trialFiles;

//Wrapper class that models the finite state machine
public class DFATrial1 {
    protected States currentState;

    private enum States {
        //Finite set of states
        OFFPOSITION(true), IGNITIONONPOSITION(true), HOLDPOSITION(true), ENGINESTARTEDPOSITION(true),
        STATIONARYPOSITION(true), INREVERSEMOTIONPOSITION(true), INFORWARDMOTIONPOSITION(true),
        CRUISECONTROLENGAGEDPOSITION(true);

       //Transition table
    /*    static {
            OFFPOSITION.startSignal = IGNITIONONPOSITION;
            OFFPOSITION.brakeHeldSignal = HOLDPOSITION;

            IGNITIONONPOSITION.startSignal = OFFPOSITION;
            IGNITIONONPOSITION.brakeHeldSignal = HOLDPOSITION;

            HOLDPOSITION.startSignal = ENGINESTARTEDPOSITION;

            ENGINESTARTEDPOSITION.startSignal = OFFPOSITION;
            ENGINESTARTEDPOSITION.seatBeltEngagedSignal = STATIONARYPOSITION;

            STATIONARYPOSITION.accelerateSignal = STATIONARYPOSITION;
            STATIONARYPOSITION.parkSelectedSignal = ENGINESTARTEDPOSITION;
            STATIONARYPOSITION.reverseSelectedSignal = INREVERSEMOTIONPOSITION;
            STATIONARYPOSITION.driveSelectedSignal = INFORWARDMOTIONPOSITION;

            INREVERSEMOTIONPOSITION.brakeHeldSignal = STATIONARYPOSITION;
            INREVERSEMOTIONPOSITION.brakePressedSignal = INREVERSEMOTIONPOSITION;
            INREVERSEMOTIONPOSITION.accelerateSignal = INREVERSEMOTIONPOSITION;

            INFORWARDMOTIONPOSITION.brakeHeldSignal = STATIONARYPOSITION;
            INFORWARDMOTIONPOSITION.brakePressedSignal = INFORWARDMOTIONPOSITION;
            INFORWARDMOTIONPOSITION.accelerateSignal = INFORWARDMOTIONPOSITION;
            INFORWARDMOTIONPOSITION.setCruiseControlSignal = CRUISECONTROLENGAGEDPOSITION;

            CRUISECONTROLENGAGEDPOSITION.brakePressedSignal = INFORWARDMOTIONPOSITION;
            CRUISECONTROLENGAGEDPOSITION.accelerateSignal = CRUISECONTROLENGAGEDPOSITION;
        } */

        //Alphabet
        States startSignal, brakeHeldSignal, seatBeltEngagedSignal, parkSelectedSignal, accelerateSignal, reverseSelectedSignal,
        driveSelectedSignal, brakePressedSignal, setCruiseControlSignal;

        //final
        final boolean ACCEPT;

        //Constructor for state. Every state is either accepting or not.
        States (boolean accept) {
            this.ACCEPT = accept;
        }

        //Transition Function
        States transition(String transition) {
            return switch (transition) {
                case "Start Signal" -> this.startSignal;
                case "Brake-Held Signal" -> this.brakeHeldSignal;
                case "Seat-Belt-Engaged Signal" -> this.seatBeltEngagedSignal;
                case "Park-Selected Signal" -> this.parkSelectedSignal;
                case "Accelerate Signal" -> this.accelerateSignal;
                case "Reverse-Selected Signal" -> this.reverseSelectedSignal;
                case "Drive-Selected Signal" -> this.driveSelectedSignal;
                case "Brake-Pressed Signal" -> this.brakePressedSignal;
                case "Set-Cruise-Control Signal" -> this.setCruiseControlSignal;
                default -> null;
            };
        }
    }

    //Default Constructor
    public DFATrial1() {
        //Initial State
        this.currentState = States.OFFPOSITION;
        System.out.println("Car created with initial Off State");

        //Need something to deal with getting the required transition. Maybe ask user or something. Could use a JPane Popup to ask for
        //a new transition state
        String transition="REACH";

        //Need to be constantly getting to current state to math up with the new transition
        while (transition.compareTo("REACH")!=0){
            stateChanged(getState(), transition);
        }
    }

    //Report the current state of the finite state machine
    public States getState() {
        return this.currentState;
    }

    //If given a specific transition then change state to the correct one
    public void stateChanged(States state, String transition) {
        System.out.println("Changing state of car");

        //Check if it's in the off position
        if (state == States.OFFPOSITION) {
            offPosition();

            //Get the transition required
            States transitionSignal = States.OFFPOSITION.transition(transition);

            //Match the transitions to states to get new state and action to be done to car or shown on screen
            //Some if statement
            /* If (whatever state + transition is valid and can be found)
                    this.state = newState, whatever that is.

                Maybe return the new state or maybe this.state would cover that
             */
        }

        System.out.println("State of car changed");
    }

    /** State Functions **/
    public void offPosition() {
        //Actions to be done to show car is off

    }


    public static void main(String[] args) {
        DFATrial1 car = new DFATrial1();


    }

    //Check if DFATrial1 accepts supplied string
 /*   public boolean accept(String string) {
        States state = States.OFFPOSITION;

        for (int i = 0; i < string.length(); i++) {
            state = state.transition(string);
        }
        return state.ACCEPT;
    } */

}
