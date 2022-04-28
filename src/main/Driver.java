package main;


import domain.Mclaren;

import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Mclaren("Mclaren VCS"));
    }
}


