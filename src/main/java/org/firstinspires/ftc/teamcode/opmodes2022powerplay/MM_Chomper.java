package org.firstinspires.ftc.teamcode.opmodes2022powerplay;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MM_Chomper {
    private Servo chomper = null;

    private static final double CHOKE = 0.39;  // tighten belt to collect
    public static final double RELEASE = 1.0;

    private double position = RELEASE;

    public MM_Chomper(HardwareMap hardwareMap) {
        chomper = hardwareMap.get(Servo.class, "Grabber");
        changePosition(RELEASE);
    }

    public double getPosition() {
        return position;
    }

    public void toggle() {
        if (getPosition() == RELEASE) {
            choke();
        } else {
            release();
        }
    }

    public void choke(){
        changePosition(CHOKE);
    }
    public void release(){
        changePosition(RELEASE);
    }

    private void changePosition(double position) {
        chomper.setPosition(position);
        this.position = position;
    }
}
