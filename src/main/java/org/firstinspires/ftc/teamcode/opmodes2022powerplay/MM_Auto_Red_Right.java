package org.firstinspires.ftc.teamcode.opmodes2022powerplay;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Config
@Autonomous(name="MM_Auto_Red_Right", group="MM")
public class MM_Auto_Red_Right extends MM_OpMode {
    private final MM_Robot robot = new MM_Robot(this);

    MM_EOCVDetection detector = new MM_EOCVDetection();
    OpenCvCamera camera;

    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime totalTime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        alliance = MM_EOCVDetection.RED;

        telemetry.addData("Status", "Wait for initialization");
        telemetry.update();
        initCamera();
        robot.init();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        totalTime.reset();
        robot.drivetrain.getScorerOutOfTheWay();
        robot.lift.slide.waitToReachPosition(robot.lift.slide.stackTicks(1));
        robot.lift.chomper.release();
        robot.drivetrain.microscopicDriveInches(2);
        robot.lift.slide.waitToReachPosition(MM_Slide.SlidePosition.COLLECT);
        robot.lift.chomper.choke();
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.3) {
        }
        robot.lift.slide.waitToReachPosition(MM_Slide.SlidePosition.DETECT);
        int sleeveColor = detector.getMaxColor();
        //check to see if you can see color during init with a cone
        robot.runSlideandDiagonalDrive(MM_Slide.SlidePosition.LOW.ticks, 15, 2, MM_Drivetrain.STRAFE, 70, 6, true, false); //test
        robot.lift.scoreCone();
        robot.runSlideandDiagonalDrive(robot.lift.slide.stackTicks(5), 41, -1.5, 2, 0,8, false, false);
        robot.drivetrain.driveInches(-4);
        robot.drivetrain.rotateToAngle(-90);
        robot.drivetrain.driveInches(23);
        robot.drivetrain.rotateToMicroscopicAngle(-90);
        robot.drivetrain.resetEncoders();
        robot.drivetrain.correctForTape();
        if (!robot.drivetrain.correctForCone()) {
            robot.parkFromStack(sleeveColor, false);
        } else {
            robot.lift.autoStackCollect(5, false);
            robot.drivetrain.resetEncoders();
            robot.runSlideandDiagonalDrive(MM_Slide.SlidePosition.LOW.ticks, -21, -12, MM_Drivetrain.STRAFE, 90, 7, false, false);
            robot.lift.scoreCone();
            robot.drivetrain.driveInches(-8);
            robot.runSlideandDiagonalDrive(robot.lift.slide.stackTicks(5), 23, 14, 2, 0, 7, false, false);
            robot.drivetrain.correctForTape();
            if (!robot.drivetrain.correctForCone()) {
                robot.parkFromStack(sleeveColor, false);
            } else {
                robot.lift.autoStackCollect(4, false);
                robot.lift.turner.changePosition(MM_Turner.BACK);
                robot.drivetrain.resetEncoders();
                robot.runSlideandDiagonalDrive(MM_Slide.SlidePosition.MEDIUM.ticks, -25, -12.2, MM_Drivetrain.STRAFE, 90, 7, false, false);
                robot.lift.scoreCone();
                robot.drivetrain.strafeInches(13);
                robot.parkFromJunction(sleeveColor, false);
            }
        }
    }
        //start collection code

    private void initCamera() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
        // Connect to the camera
        // Use the SkystoneDetector pipeline
        // processFrame() will be called to process the frame
        camera.setPipeline(detector);
        // Remember to change the camera rotation
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }

        });

        FtcDashboard.getInstance().startCameraStream(camera, 0);

    }
}
//*/
//    private void firstInitCamera() {
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
//        // Connect to the camera
//        // Use the SkystoneDetector pipeline
//        // processFrame() will be called to process the frame
//        camera.setPipeline(detector);
//        // Remember to change the camera rotation
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//
//            }
//
//        });
//
//        FtcDashboard.getInstance().startCameraStream(camera, 0);
//
//    }
//}
