package org.firstinspires.ftc.teamcode.opmodes2022powerplay;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@Autonomous(name="MM_AutoTest", group="MM")
public class MM_AutoTest extends MM_OpMode {
    private MM_Robot robot = new MM_Robot(this);

    public static int INCHES = 48;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Status", "Wait for initialization");
        telemetry.update();

        robot.init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        robot.runSlideToPosition(LOW);

        robot.driveInches(42);
        robot.driveInches(-8);


        robot.drivetrain.rotateDegrees(55);
        robot.driveInches(5);

        robot.collector.autoRunCollector(robot.collector.OPEN);
        robot.driveInches(-5);
        robot.drivetrain.rotateDegrees(0);

        telemetry.update();



//        robot.runSlideandDrive(HIGH, 24, 7);
    }
}
