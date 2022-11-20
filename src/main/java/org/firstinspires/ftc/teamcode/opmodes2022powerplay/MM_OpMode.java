package org.firstinspires.ftc.teamcode.opmodes2022powerplay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public abstract class MM_OpMode extends LinearOpMode {
    public MM_Robot robot = new MM_Robot(this);

    public Gamepad gamepad1Current = new Gamepad();
    public Gamepad gamepad1Prior = new Gamepad();
    public Gamepad gamepad2Current = new Gamepad();
    public Gamepad gamepad2Prior = new Gamepad();

    final int GAMEPAD1 = 0;
    final int GAMEPAD2 = 1;

    public boolean aPressed(int gamepad) {
        if (gamepad == GAMEPAD1) {
            if (gamepad1Current.a && !gamepad1Prior.a) {
                return true;
            }
            return false;
        } else {
            if (gamepad2Current.a && !gamepad2Prior.a) {
                return true;
            }
            return false;
        }

    }

    public boolean dpadLeftPressed(int gamepad) {
        if (gamepad == GAMEPAD1) {
            if (gamepad1Current.dpad_left && !gamepad1Prior.dpad_left) {
                return true;
            }
            return false;
        } else {
            if (gamepad2Current.dpad_left && !gamepad2Prior.dpad_left) {
                return true;
            }
            return false;
        }
    }

    public boolean dpadRightPressed(int gamepad) {
        if (gamepad == GAMEPAD1) {
            if (gamepad1Current.dpad_right && !gamepad1Prior.dpad_right) {
                return true;
            }
            return false;
        } else {
            if (gamepad2Current.dpad_right && !gamepad2Prior.dpad_right) {
                return true;
            }
            return false;
        }
    }

    public boolean leftBumperPressed(int gamepad) {
        if (gamepad == GAMEPAD1) {
            if (gamepad1Current.left_bumper && !gamepad1Prior.left_bumper) {
                return true;
            }
            return false;
        } else {
            if (gamepad2Current.left_bumper && !gamepad2Prior.left_bumper) {
                return true;
            }
            return false;
        }
    }
}

