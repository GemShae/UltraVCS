package dfa;

import car.Mp3;
import frame.Mclaren;
import javazoom.jl.decoder.JavaLayerException;

//Wrapper class that models the finite state machine
public class DFA {
    public States currentState;
    public Alphabet currentTransition;
    public int speed; //Variable to hold current speed
    Mclaren car = new Mclaren();
    Mp4 video = new Mp4();
    Mp3 voiceOver = new Mp3();

    public enum States {
        //Finite set of states
        OFFPOSITION(true), IGNITIONONPOSITION(false), HOLDPOSITION(false), ENGINESTARTEDPOSITION(false),
        STATIONARYPOSITION(false), INREVERSEMOTIONPOSITION(false), INFORWARDMOTIONPOSITION(false),
        CRUISECONTROLENGAGEDPOSITION(false);

        //To determine accept States from reject states
        final boolean ACCEPT;

        //Constructor for state. Every state is either accepting or not.
        States(boolean accept) {
            this.ACCEPT = accept;
        }

        //End of States Enum
    }

    public enum Alphabet {
        //Transitions for States
        startSignal, brakeHeldSignal, seatBeltEngagedSignal, parkSelectedSignal, accelerateSignal, reverseSelectedSignal,
        driveSelectedSignal, brakePressedSignal, setCruiseControlSignal;

        //Transition Function
        Alphabet transition(String transition) {
            System.out.println("Got Transition");
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
        }
        //End of Alphabet Enum
    }

    //Default Constructor
    public DFA() {
        System.out.println("DFA Constructor");
    /*    //Initial State
        this.currentStateLabel = States.OFFPOSITION;
        this.currentTransitionLabel = Alphabet.setCruiseControlSignal; //Revise this
        this.speed = 0;
        System.out.println("Car created with initial Off State"); */

        //Car goes back to off
        //Call off function
    }

    //If given a specific transition then change state to the correct one
    public void stateChanged(States state, String transition) {
        System.out.println("Attempting Changing state of car");

        //Get the transition required
        currentTransition = currentTransition.transition(transition);

        switch (state) {
            //Check if it's in the off position
            case OFFPOSITION -> {
                offPosition();

                if (currentTransition == Alphabet.startSignal) {
                    this.currentState = States.IGNITIONONPOSITION;
                } else if (currentTransition == Alphabet.brakeHeldSignal) {
                    this.currentState = States.HOLDPOSITION;
                }
            }
            case IGNITIONONPOSITION -> {
                ignitionOnPosition();
                if (currentTransition == Alphabet.startSignal) {
                    this.currentState = States.OFFPOSITION;
                } else if (currentTransition == Alphabet.brakeHeldSignal) {
                    this.currentState = States.HOLDPOSITION;
                }
            }
            case HOLDPOSITION -> {
                holdPosition();
                if (currentTransition == Alphabet.startSignal) {
                    this.currentState = States.ENGINESTARTEDPOSITION;
                }
            }
            case ENGINESTARTEDPOSITION -> {
                engineStartedPosition();
                if (currentTransition == Alphabet.startSignal) {
                    this.currentState = States.OFFPOSITION;
                } else if (currentTransition == Alphabet.seatBeltEngagedSignal) {
                    this.currentState = States.STATIONARYPOSITION;
                }
            }
            case STATIONARYPOSITION -> {
                stationaryPosition();
                if (currentTransition == Alphabet.accelerateSignal) {
                    this.currentState = States.STATIONARYPOSITION;
                } else if (currentTransition == Alphabet.parkSelectedSignal) {
                    this.currentState = States.ENGINESTARTEDPOSITION;
                } else if (currentTransition == Alphabet.reverseSelectedSignal) {
                    this.currentState = States.INREVERSEMOTIONPOSITION;
                } else if (currentTransition == Alphabet.driveSelectedSignal) {
                    this.currentState = States.INFORWARDMOTIONPOSITION;
                }
            }
            case INREVERSEMOTIONPOSITION -> {
                inReverseMotionPosition();
                if (currentTransition == Alphabet.brakeHeldSignal) {
                    this.currentState = States.STATIONARYPOSITION;
                } else if (currentTransition == Alphabet.brakePressedSignal) {
                    this.currentState = States.INREVERSEMOTIONPOSITION;
                } else if (currentTransition == Alphabet.accelerateSignal) {
                    this.currentState = States.INREVERSEMOTIONPOSITION;
                }
            }
            case INFORWARDMOTIONPOSITION -> {
                inForwardMotionPosition();
                if (currentTransition == Alphabet.brakeHeldSignal) {
                    this.currentState = States.STATIONARYPOSITION;
                    //SHOW THE NEXT VIDEO
                } else if (currentTransition == Alphabet.brakePressedSignal) {
                    this.currentState = States.INFORWARDMOTIONPOSITION;
                } else if (currentTransition == Alphabet.accelerateSignal) {
                    this.currentState = States.INFORWARDMOTIONPOSITION;
                } else if (currentTransition == Alphabet.setCruiseControlSignal) {
                    this.currentState = States.CRUISECONTROLENGAGEDPOSITION;
                }
            }
            case CRUISECONTROLENGAGEDPOSITION -> {
                cruiseControlEngagedPosition();
                if (currentTransition == Alphabet.brakePressedSignal) {
                    this.currentState = States.INFORWARDMOTIONPOSITION;
                } else if (currentTransition == Alphabet.accelerateSignal) {
                    this.currentState = States.CRUISECONTROLENGAGEDPOSITION;
                }
            }
        }
        System.out.println("State of car changed");
    }

    /** State Functions **/
   public void offPosition() {
        //Actions to be done to show car is off
        car.videoPanel.setVisible(true);
        video.playVideo("2");
        video.mediaPlayer.stop();
        try {
            voiceOver.playMp3("1");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void ignitionOnPosition() {
        //Actions to be done when the ignition is on
        car.videoPanel.setVisible(true);
        video.playVideo("3");
        car.videoPanel.setVisible(false);
        try {
            voiceOver.playMp3("2");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void holdPosition() {
        //Actions to be done when the car is holding
        car.videoPanel.setVisible(true);
        video.playVideo("4");
        car.videoPanel.setVisible(false);
        try {
            voiceOver.playMp3("3");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void engineStartedPosition() {
        //Actions to be done when the car engine has started
        car.videoPanel.setVisible(true);
        video.playVideo("5");
        car.videoPanel.setVisible(false);
        try {
            voiceOver.playMp3("4");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void stationaryPosition() {
        //Actions to be done when the car is stationary
        car.videoPanel.setVisible(true);
        video.playVideo("6");
        car.videoPanel.setVisible(false);
        try {
            voiceOver.playMp3("5");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void inReverseMotionPosition() {
        //Actions to be done when the car is reversing
        car.videoPanel.setVisible(true);
        video.playVideo("7");
        car.videoPanel.setVisible(false);
        try {
            voiceOver.playMp3("6");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void inForwardMotionPosition() {
        //Actions to be taken when the car is moving forward
        car.videoPanel.setVisible(true);
        video.playVideo("8");
        car.videoPanel.setVisible(false);
        try {
            voiceOver.playMp3("7");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    public void cruiseControlEngagedPosition() {
        //Action to be taken when the car is in cruise control
        car.videoPanel.setVisible(true);
        video.playVideo("9");
        car.videoPanel.setVisible(false);
        try {
            voiceOver.playMp3("8");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
    }

    /* State Transition Functions */
/*    public void startSignal () {
        //Visual for starting
        car.videoPanel.setVisible(true);
        video.playVideo("10");
        car.videoPanel.setVisible(false);
    }
*/

}
