package org.usfirst.frc.team4611.robot;

import java.util.ArrayList;

import org.usfirst.frc.team4611.robot.defaults.DefaultValues;
import org.usfirst.frc.team4611.robot.logging.Logger;
//import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.logging.LoggerType;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The RobotMap is where we declare what our constants are It's where we set
 * which PWM the Victors are plugged into and what ports are which joysticks
 * etc. This is where you're probably gonna end up when incorrect motors are
 * running or we change ports for certain motors.
 */
public class RobotMap {

	//Drive train Talons
	public static WPI_TalonSRX driveTrainFL_Talon;
	public static WPI_TalonSRX driveTrainFR_Talon;
	public static WPI_TalonSRX driveTrainBL_Talon;
	public static WPI_TalonSRX driveTrainBR_Talon;
	
	public static WPI_TalonSRX elevator_Talon;

	//Drive train Victors
	public static Victor driveTrainFL;
	public static Victor driveTrainFR;
	public static Victor driveTrainBL;
	public static Victor driveTrainBR;
	
	//Extra Victors
	public static Victor linearActuator;
	public static Victor linearActuator2;
	
	//Victor ports
	public static int linearActuatorPort = 4;
	public static int victorPortFL = 1;
	public static int victorPortFR = 0;
	public static int victorPortBL = 2;
	public static int victorPortBR = 3;
	
	
	//General Objects
	public static DoubleSolenoid sol;
	public static MecanumDrive driveTrain;
	public static AnalogInput ultrasonicInput;
	
	//Joystick ports
	public static int leftJoyPort = 0; //Joystick can be found on this port. The ports aren't physical plugs
	public static int rightJoyPort = 1; //But rather decided from the drivers station by the drivers

	//Button Ports
	public static int solTogglePort = 10;
	public static int solExtendPort = 2;
	public static int solRetractPort = 1;
	//public static int autoGrabButtPort = 11;
	public static int openPort = 1;
	public static int closePort = 0;

	//Networktable things
	public static final int teamID = 4611;
	public static final String networkTableServerAddress = "10.46.11.2";
	public static final String networkTableID = "Custom Values";
	public static NetworkTableManager networkManager = new NetworkTableManager();
	public static ArrayList<LoggerType> loggerTypes = new ArrayList<LoggerType>();
	public static final long systemStartupTime = System.currentTimeMillis();
	
	//Constants
	public static final int ULTRA_PORT = 3;
	public static final int UD_DISTANCE = 13; // distance for UltraDrive, pointless if it's less than 12 for now
	
	//initial values for potentiometer settings
	public static double potMin = .08;
	public static double potMax = .8;
	public static double potSwitch = .45;
	public static double potMin2 = .08;
	public static double potMax2 = .8;
	public static double potSwitch2 = .45;
	private static double varianceLimit = .01;
	
	//Default motor speeds
	public static double linearActuatorSpeed = 0.5;
	//public static double linearActuator2Speed = 0.5;
	public static double elevatorSpeed = 0.5;
	
	//public static double motorSpeed = 1;

	//String keys
	public static String joyStickSubTable = "Joysticks";
	public static String mecanumSubTable = "Mecanum";
	public static String defaultsSubTable = "Defaults";
	public static String switcherSubTable = "Switchable";
	public static String linearActuatorSubTable = "LA";
	public static String potentiometerSubTable = "Potentiometer";
	public static String ultraSubtable = "Ultrasonic";
	public static String solenoidSubtable = "Solenoid";
	public static String elevatorSubtable = "Elevator";
	public static String leftJoyXID = "leftJoyX";
	public static String leftJoyYID = "leftJoyY";
	public static String leftJoyZID = "leftJoyZ";
	public static String rightJoyXID = "rightJoyX";
	public static String rightJoyYID = "rightJoyY";
	public static String rightJoyZID = "rightJoyZ";
	public static String motorPowerID = "motorPower";
	public static String strafePowerID = "strafePower";
	public static String deadZoneID = "deadZone";
	public static String deadZoneYID = "deadZoneY";
	public static String switcherID = "Talon Enabled";
	public static String LASpeedID = "LA-speed";
	public static String potMinID = "Potentiometer Min";
	public static String potMaxID = "Potentiometer Max";
	public static String potSwitchID = "Potentiometer Switch";
	public static String potMin2ID = "Potentiometer 2 Min";
	public static String potMax2ID = "Potentiometer 2 Max";
	public static String potSwitch2ID = "Potentiometer 2 Switch";
	public static String varianceLimitID = "Variance Limit";
	public static String elevatorSpeedID = "Elevator Speed";
	public static DefaultValues defaults;

	public static AnalogPotentiometer linearActuatorPot;
	public static AnalogPotentiometer linearActuatorPot2;
	

	public static void init() {
		
		//Drive Train Victors
		driveTrainFL = new Victor(victorPortFL);
		driveTrainFR = new Victor(victorPortFR);
		driveTrainBL = new Victor(victorPortBL);
		driveTrainBR = new Victor(victorPortBR);

		// Ultrasonic sensor
		ultrasonicInput = new AnalogInput(ULTRA_PORT);
		
		//Linear Actuator
		linearActuator = new Victor(linearActuatorPort);
		linearActuator2 = new Victor(5);
		linearActuatorPot = new AnalogPotentiometer(0);
		linearActuatorPot2 = new AnalogPotentiometer(1);
		
		//Solenoid
		sol = new DoubleSolenoid(RobotMap.openPort, RobotMap.closePort);
		
		//Default Values
		defaults = new DefaultValues();

		//Drive train Talons
		driveTrainFL_Talon = new WPI_TalonSRX(12);
		driveTrainFR_Talon = new WPI_TalonSRX(13);
		driveTrainBL_Talon = new WPI_TalonSRX(10);
		driveTrainBR_Talon = new WPI_TalonSRX(11);
		
		elevator_Talon = new WPI_TalonSRX(35);
		
		//Talon Configuration
			//Sensor pos
			driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
			driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
			driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
			driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		
			//Motion
			driveTrainFL_Talon.configMotionAcceleration(360, 0);
			driveTrainFL_Talon.configMotionCruiseVelocity(360, 0);
			driveTrainFR_Talon.configMotionAcceleration(360, 0);
			driveTrainFR_Talon.configMotionCruiseVelocity(360, 0);
			driveTrainBL_Talon.configMotionAcceleration(360, 0);
			driveTrainBL_Talon.configMotionCruiseVelocity(360, 0);
			driveTrainBR_Talon.configMotionAcceleration(360, 0);
			driveTrainBR_Talon.configMotionCruiseVelocity(360, 0);
			
		Logger.init("Logs");

		//Shuffleboard Tables
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID,
		RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID, 0.65));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID,
		RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID, 0.5));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID,
		RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID, 0.15));
		RobotMap.updateValue(RobotMap.mecanumSubTable, RobotMap.deadZoneYID,
				RobotMap.defaults.getDoubleDefaultValue(RobotMap.mecanumSubTable, RobotMap.deadZoneYID, 0.15));
		RobotMap.updateValue(RobotMap.switcherSubTable, RobotMap.switcherID, true);
		RobotMap.updateValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedID, 
				RobotMap.defaults.getDoubleDefaultValue(linearActuatorSubTable, LASpeedID, linearActuatorSpeed));
		RobotMap.updateValue(potentiometerSubTable, potMaxID, potMax);
		RobotMap.updateValue(potentiometerSubTable, potMinID, potMin);
		RobotMap.updateValue(potentiometerSubTable, potSwitchID, potSwitch);
		RobotMap.updateValue(potentiometerSubTable, potMax2ID, potMax2);
		RobotMap.updateValue(potentiometerSubTable, potMin2ID, potMin2);
		RobotMap.updateValue(potentiometerSubTable, potSwitch2ID, potSwitch2);
		RobotMap.updateValue(potentiometerSubTable, varianceLimitID,
				RobotMap.defaults.getDoubleDefaultValue(potentiometerSubTable, varianceLimitID, varianceLimit));
		RobotMap.updateValue(elevatorSubtable, elevatorSpeedID, 
				RobotMap.defaults.getDoubleDefaultValue(elevatorSubtable, elevatorSpeedID, elevatorSpeed));
		
		//Which type of drive train do you have?
		if(!(boolean)RobotMap.getValue(RobotMap.switcherSubTable, RobotMap.switcherID)) {
			setupVictor();
		}else{
			RobotMap.setupTalon();
		}
	}	
	
	//Setup Victor DriveTrain
	public static void setupVictor() {
		stopTalon();
		RobotMap.log(RobotMap.switcherSubTable, "Setting up victor");
		driveTrain = new MecanumDrive(driveTrainFL, driveTrainFR, driveTrainBL, driveTrainBR);
		driveTrain.setSafetyEnabled(false);
	}

	//Setup Talon DriveTrain
	public static void setupTalon() {
		stopVictor();
		RobotMap.log(RobotMap.switcherSubTable, "Setting up talons");
		driveTrain = new MecanumDrive(driveTrainFL_Talon, driveTrainFR_Talon, driveTrainBL_Talon, driveTrainBR_Talon);
		driveTrain.setSafetyEnabled(false);
	}
	
	/**
	 * Called by the setupTalon and is meant as a safety to ensure
	 * anything that shouldn't be abruptly stopped is safely stopped
	 * before continuing the setup process
	 */	
	public static void stopVictor() {
		RobotMap.driveTrainBL.stopMotor();
		RobotMap.driveTrainBR.stopMotor();
		RobotMap.driveTrainFL.stopMotor();
		RobotMap.driveTrainFR.stopMotor();
	}
	
	/**
	 * Called by the setupVictor and is meant as a safety to ensure
	 * anything that shouldn't be abruptly stopped is safely stopped
	 * before continuing the setup process
	 */	
	public static void stopTalon() {
		driveTrainBL.disable();
		driveTrainBR.disable();
		driveTrainFL.disable();
		driveTrainFR.disable();
	}
	
	/**
	 * Updates or adds a new value to the NetworkTable 
	 * in a subtable based on the subTable name
	 * @param key The identifer for the value
	 * @param value The value of the key
	 * Updates or adds a new value to the NetworkTable in a subtable based on
	 * the subTable name
	 * 
	 * @param key
	 *            The identifer for the value
	 * @param value
	 *            The value of the key
	 */
	public static void updateValue(String subtable, String key, Object value) {
		// Tries to add value to the networktable
		if (!RobotMap.networkManager.updateValue(subtable, key, value)) {
			// If it's unsuccessful, it logs there was a problem
			System.out.println("Unable to update value with key: " + key + " on subtable NetworkTable");
			// TODO: Replace System.out.println with Logging functions once
			// merged
		}
	}

	/**
	 * 
	 * @param subtable
	 *            The subtable the value is on
	 * @param key
	 *            the key the value is binded to
	 * @return the value connected to the key, or null otherwise
	 */
	public static Object getValue(String subtable, String key) {
		return RobotMap.networkManager.getValue(subtable, key);
	}

	public static void log(String subTable, String message) {
		//Logger.log(message, Logger.getLoggerType(subTable));
	}
}
