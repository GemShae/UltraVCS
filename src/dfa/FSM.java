package dfa;

import car.Mp3;
import car.Mp4;
import domain.Mclaren;
import javazoom.jl.decoder.JavaLayerException;

public class FSM {
    public States currentState = States.OFFPOSITION;
   //public Alphabet currentTransition;
    public int speed=0;

    public Mp3 voiceOver = new Mp3();

    public boolean accept(States finalState) {
        return finalState.ACCEPT;
    }

    //State Change based on states of the car
    public void stateChange(States currentState, Alphabet currentTransition) {
        System.out.println("Attempting to change state of car");

        switch (currentState) {
            //Check if State is Off
            case OFFPOSITION -> {
                switch (currentTransition) {
                    case brakeHeldSignal -> {
                        //Call state function with code
                        hold();
                        //New state
                        this.currentState = States.HOLDPOSITION;
                    }
                    case startSignal -> {
                        //Call state function with code
                        ignitionOn();
                        //New state
                        this.currentState = States.IGNITIONONPOSITION;
                    }
                }
            }
            case HOLDPOSITION -> {
                if (currentTransition == Alphabet.startSignal) {
                    //Call state function with code
                    engineStarted();
                    //New State
                    this.currentState = States.ENGINESTARTEDPOSITION;
                }
            }
            case IGNITIONONPOSITION -> {
                switch (currentTransition) {
                    case brakeHeldSignal -> {
                        //Call state function with code
                        hold();
                        //New state
                        this.currentState = States.HOLDPOSITION;
                    }
                    case startSignal -> {
                        //Call state function with code
                        off();
                        //New state
                        this.currentState = States.OFFPOSITION;
                    }
                }
            }
            case ENGINESTARTEDPOSITION -> {
                switch (currentTransition) {
                    case startSignal -> {
                        //Call state function with code
                        off();
                        //New state
                        this.currentState = States.OFFPOSITION;
                    }
                    case seatBeltEngagedSignal -> {
                        //Call state function with code
                        stationary();
                        //New State
                        this.currentState = States.STATIONARYPOSITION;
                    }
                }
            }
            case STATIONARYPOSITION -> {
                switch (currentTransition) {
                    case driveSelectedSignal -> {
                        //Call state function with code
                        inForwardMotion();
                        //New State
                        this.currentState=States.INFORWARDMOTIONPOSITION;
                    }
                    case accelerateSignal -> {
                        //Call state function with code
                        stationary();
                        //New State
                        this.currentState=States.STATIONARYPOSITION;
                    }
                    case parkSelectedSignal -> {
                        //Call state function with code
                        engineStarted();
                        //New State
                        this.currentState = States.ENGINESTARTEDPOSITION;
                    }
                    case reverseSelectedSignal -> {
                        //Call state function with code
                        inReverseMotion();
                        //New State
                        this.currentState = States.INREVERSEMOTIONPOSITION;
                    }
                }
            }
            case INREVERSEMOTIONPOSITION -> {
                switch (currentTransition) {
                    case brakeHeldSignal -> {
                        //Call state function with code
                        stationary();
                        //New state
                        this.currentState = States.STATIONARYPOSITION;
                    }
                    case accelerateSignal, brakePressedSignal -> {
                        //Call state function with code
                        inReverseMotion();
                        //New State
                        this.currentState = States.INREVERSEMOTIONPOSITION;
                    }
                }
            }
            case INFORWARDMOTIONPOSITION -> {
                switch (currentTransition) {
                    case brakeHeldSignal -> {
                        //Call state function with code
                        stationary();
                        //New state
                        this.currentState = States.STATIONARYPOSITION;
                    }
                    case accelerateSignal, brakePressedSignal -> {
                        //Call state function with code
                        inForwardMotion();
                        //New State
                        this.currentState = States.INFORWARDMOTIONPOSITION;
                    }
                    case setCruiseControlSignal -> {
                        //Call state function with code
                        cruisecontrolEngaged();
                        //New State
                        this.currentState = States.CRUISECONTROLENGAGEDPOSITION;
                    }
                }
            }
            case CRUISECONTROLENGAGEDPOSITION -> {
                switch (currentTransition) {
                    case accelerateSignal -> {
                        //Call state function with code
                        cruisecontrolEngaged();
                        //New State
                        this.currentState = States.CRUISECONTROLENGAGEDPOSITION;
                    }
                    case brakePressedSignal -> {
                        //Call state function with code
                        inForwardMotion();
                        //New State
                        this.currentState = States.INFORWARDMOTIONPOSITION;
                    }
                }
            }
        }
    }

    /* State Functions to avoid repetition in code */
    public void off() {
        try {
            voiceOver.playMp3("1");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }

        new Mp4("2");

        Mclaren.currentStateTextField.setText("OFF");
        //Move state marker To Off
        Mclaren.stateMarkerLabel.setBounds(291,38,100,200);
    }

    public void ignitionOn() {
        try {
            voiceOver.playMp3("2");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        new Mp4("3");

        Mclaren.currentStateTextField.setText("IGNITION-ON");
        //Move state marker to Ignition ON
        Mclaren.stateMarkerLabel.setBounds(268,-44,100,200);
    }

    public void hold() {
        try {
            voiceOver.playMp3("3");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        new Mp4("4");

        Mclaren.currentStateTextField.setText("HOLD");
        //Move state marker to Hold
        Mclaren.stateMarkerLabel.setBounds(356,-44,100,200);
    }

    public void engineStarted() {
        try {
            voiceOver.playMp3("4");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        new Mp4("5");
        new Mp4("17");

        Mclaren.currentStateTextField.setText("ENGINE-STARTED");
        //Move state marker to Engine Started
        Mclaren.stateMarkerLabel.setBounds(379,38,100,200);
    }

    public void stationary() {
        try {
            voiceOver.playMp3("5");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        new Mp4("6");

        Mclaren.currentStateTextField.setText("STATIONARY");
        //Move state marker To Stationary
        Mclaren.stateMarkerLabel.setBounds(477,42,100,200);
    }

    public void inReverseMotion() {
        try {
            voiceOver.playMp3("6");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        new Mp4("7");

        Mclaren.currentStateTextField.setText("IN-REVERSE");
        //Move state marker to Reverse
        Mclaren.stateMarkerLabel.setBounds(575,42,100,200);
    }

    public void inForwardMotion() {
        try {
            voiceOver.playMp3("7");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        new Mp4("8");

        Mclaren.currentStateTextField.setText("IN-FORWARD");
        //Move State marker to Forward
        Mclaren.stateMarkerLabel.setBounds(456,-38,100,200);
    }

    public void cruisecontrolEngaged() {
        try {
            voiceOver.playMp3("8");
        } catch (JavaLayerException ex) {
            ex.printStackTrace();
        }
        new Mp4("9");

        Mclaren.currentStateTextField.setText("CRUISE CONTROL");
        //Move State marker to Cruise Control
        Mclaren.stateMarkerLabel.setBounds(560,-38,100,200);
    }
}
