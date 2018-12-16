package org.usfirst.frc.team4611.robot.subsystems.turbo;

import java.util.HashMap;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.OzoneJavaLogger.LogTest;
import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.commands.teleop.drive.Move;
import org.usfirst.frc.team4611.robot.networktables.NetTableManager;
import org.usfirst.frc.team4611.robot.subsystems.baseclasses.DriveTrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class TurboTankDrive extends DriveTrain {

    private final int turboSolOpenPort = 0;
    private final int turboSolClosePort = 1;

    private final int frontLeftTalonPort = 10;
    private final int frontRightTalonPort = 10;
    private final int backLeftTalonPort = 10;
    private final int backRightTalonPort = 10;

    public final double INCH_PU_MULT = 215.910640625;

    public final double METER_PU_MULT = 39.3701 * 215.910640625;

    final Logger logger = Logger.getLogger(LogTest.class.getName());

    private DoubleSolenoid turboSol = new DoubleSolenoid(turboSolOpenPort, turboSolClosePort);

    private WPI_TalonSRX frontLeft = new WPI_TalonSRX(frontLeftTalonPort);
    private WPI_TalonSRX frontRight = new WPI_TalonSRX(frontRightTalonPort);
    private WPI_TalonSRX backLeft = new WPI_TalonSRX(backLeftTalonPort);
    private WPI_TalonSRX backRight = new WPI_TalonSRX(backRightTalonPort);

    private double pVal = .65;
    private int interval = 10;

    private int maxRPM = 400; // Reduced from 1200

    private double velocity1;
    private double velocity2;

    private double YValScaler1 = 1;
    private double YValScaler2 = 1;

    private int velocityInvert1 = 1;
    private int velocityInvert2 = -1;

    private String mecanumSubtable = "Mecanum";

    private String velocity1ID = "Velocity1";
    private String velocity2ID = "Velocity2";

    public TurboTankDrive() {
        setupTalons();
    }

    public void setupTalons() {
        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        frontLeft.setSelectedSensorPosition(0, 0, 0);
        frontRight.setSelectedSensorPosition(0, 0, 0);
        backLeft.setSelectedSensorPosition(0, 0, 0);
        backRight.setSelectedSensorPosition(0, 0, 0);

        frontLeft.config_kP(0, pVal, interval);
        frontRight.config_kP(0, pVal, interval);
        backLeft.config_kP(0, pVal, interval);
        backRight.config_kP(0, pVal, interval);

        frontLeft.config_kI(0, 0.000, 0);
        frontRight.config_kI(0, 0.000, 0);
        backLeft.config_kI(0, 0.000, 0);
        backRight.config_kI(0, 0.000, 0);

        frontLeft.config_kD(0, 0, 0);
        frontRight.config_kD(0, 0, 0);
        backLeft.config_kD(0, 0, 0);
        backRight.config_kD(0, 0, 0);

        frontLeft.setSensorPhase(true);
        frontRight.setSensorPhase(true);
        backLeft.setSensorPhase(true);
        backRight.setSensorPhase(true);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);

    }

    @Override
    public void move() {
        double LYVal = -OI.generalJoystickFilter(OI.leftJoy.getY());
        double RYVal = -OI.generalJoystickFilter(OI.rightJoy.getY());

        velocity1 = 4 * (maxRPM * (LYVal * YValScaler1) * (velocityInvert1));
        velocity2 = 4 * (maxRPM * (RYVal * YValScaler2) * (velocityInvert2));

        frontLeft.set(ControlMode.Velocity, velocity1);
        frontRight.set(ControlMode.Velocity, velocity2);

        HashMap<String, Object> values = new HashMap<String, Object>();
        values.put(velocity1ID, velocity1);
        values.put(velocity2ID, velocity2);
        NetTableManager.updateValues(mecanumSubtable, values);
        logger.info("" + (frontRight.getSelectedSensorVelocity(0) * 600 / 4092));
        logger.fine("" + frontRight.getBusVoltage());
    }

    @Override
    public void moveForward(double d) {

    }

    @Override
    public void moveBackward(double d) {

    }

    @Override
    public int getAverageSensorPos() {
        return (Math.abs(frontLeft.getSelectedSensorPosition(0)) + Math.abs(frontRight.getSelectedSensorPosition(0))
                + Math.abs(backLeft.getSelectedSensorPosition(0)) + Math.abs(backRight.getSelectedSensorPosition(0)))
                / 4;
    }

    @Override
    public void resetEncoders() {
        frontLeft.setSelectedSensorPosition(0, 0, 0);
        frontRight.setSelectedSensorPosition(0, 0, 0);
        backLeft.setSelectedSensorPosition(0, 0, 0);
        backRight.setSelectedSensorPosition(0, 0, 0);
    }

    @Override
    protected void initDefaultCommand() {
        Robot.driveTrain.setDefaultCommand(new Move(this));
    }

    public void moveVelocityAuton(double speed) {

    }

    public void rotate(double d) {

    }

    /*
     * public void followTrajectory(Trajectory t) {
     * 
     * }
     */

    public void setTrajectorySpeeds() {

    }

    public double getVelocity() {
        return 0;
    }

    public void activateTurbo() {
        turboSol.set(Value.kForward);
        turboSol.set(Value.kOff);
    }

    public void deactivateTurbo() {
        turboSol.set(Value.kReverse);
        turboSol.set(Value.kOff);
    }
}