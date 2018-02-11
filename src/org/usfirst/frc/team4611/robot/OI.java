package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.commands.auton.AimForBox;
import org.usfirst.frc.team4611.robot.commands.auton.AutoGrab;
import org.usfirst.frc.team4611.robot.commands.drive.MoveForward;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeLeft;
import org.usfirst.frc.team4611.robot.commands.drive.StrafeRight;
import org.usfirst.frc.team4611.robot.commands.drive.VisionDrive;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotDown;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotSwitch;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotUp;
import org.usfirst.frc.team4611.robot.potentiometer.StopPot;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.RetractSolenoid;
import org.usfirst.frc.team4611.robot.commands.solenoid.ToggleSolenoid;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This is where we create all of out buttons and joysticks and 
 * set up the "UI" of the bot for the drivers. You're gonna end up 
 * here a lot when people complain about buttons needing to be changed
 */

public class OI {
	
	//Joysticks
	public static Joystick leftJoy;
	public static Joystick rightJoy;
	public static Joystick auxJoy;
	
	
	//Movement Buttons
	public Button strafeLeft;
	public Button strafeRight;
	public Button positionForward;
	public Button positionBackward;
	public Button positionLeft;
	public Button positionRight;
	public Button positionVision;
	public Button autoGrabBox;
	public Button aimBox;
	
	//LA Buttons
	public Button linearActuatorUp;
	public Button linearActuatorDown;
	public Button linearActuatorUp2;
	public Button linearActuatorDown2;
	public Button linearActuatorSwitch;
	
	//Solenoid Buttons
	public Button solToggle;
	public Button solExtend;
	public Button solRetract;	

	public OI (){
		
		//Joystick
		leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		auxJoy = new Joystick(RobotMap.thirdJoyPort);
		
		//Movement Buttons
		strafeLeft= new JoystickButton(rightJoy, 4);
		strafeRight= new JoystickButton(rightJoy, 5);
		autoGrabBox = new JoystickButton(auxJoy, 11);
		aimBox = new JoystickButton(auxJoy, 10);
		
		//LA Buttons
		linearActuatorUp = new JoystickButton(leftJoy, 3);
		linearActuatorDown = new JoystickButton(leftJoy, 2);
		linearActuatorUp2 = new JoystickButton(auxJoy, 6);
		linearActuatorDown2 = new JoystickButton(auxJoy, 7);
		linearActuatorSwitch = new JoystickButton(rightJoy, 9);
		
		//Solenoid Buttons
		solToggle = new JoystickButton(leftJoy, RobotMap.solTogglePort);
		solExtend = new JoystickButton(leftJoy, 7);//close claw
		solRetract = new JoystickButton(leftJoy, 6);//open claw

		
	
		//Sends the starting values of the joysticks to the Shuffleboard
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, leftJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, leftJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, leftJoy.getZ());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, rightJoy.getX());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, rightJoy.getY());
		RobotMap.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, rightJoy.getZ());

		
		double pos = (double)RobotMap.networkManager.getValue(RobotMap.mecanumSubTable, RobotMap.positionDistanceID);
		
		
		//Position Commands
		//positionForward.whenPressed	(new PositionDrive(pos, "forward"));
		//positionBackward.whenPressed(new PositionDrive(pos, "backward"));
		//positionLeft.whenPressed	(new PositionDrive(pos, "left"));
		//positionRight.whenPressed	(new PositionDrive(pos, "right"));
		//positionVision.whenPressed	(new PositionDrive((double)RobotMap.networkManager.getValue(RobotMap.mecanumSubTable, 
				//RobotMap.positionDistanceID), "vision"));
		
		//Strafe Commands
		strafeRight.whileHeld(new StrafeRight((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		strafeLeft.whileHeld(new StrafeLeft((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID)));
		
		//Linear actuator commands
		linearActuatorUp.whileHeld(new MovePotUp());
		linearActuatorUp.whenReleased(new StopPot());
		linearActuatorUp2.whileHeld(new MovePotUp());
		linearActuatorUp2.whenReleased(new StopPot());
		
		linearActuatorDown.whileHeld(new MovePotDown());
		linearActuatorDown.whenReleased(new StopPot());
		linearActuatorDown2.whileHeld(new MovePotDown());
		linearActuatorDown2.whenReleased(new StopPot());
		
		linearActuatorSwitch.whileHeld(new MovePotSwitch((double)RobotMap.getValue(RobotMap.linearActuatorSubTable, RobotMap.LASpeedUpID)));
		linearActuatorSwitch.whenReleased(new StopPot());
		
		//Solenoid Commands
		solToggle.whenPressed(new ToggleSolenoid());
		solExtend.whileHeld(new ExtendSolenoid());
		solRetract.whileHeld(new RetractSolenoid());
		autoGrabBox.whenPressed(new AutoGrab());
		aimBox.whenPressed(new AimForBox());
	}
	
	public double filter(double raw) //We pass joystick values through the filter here and edit the raw value
    {
        if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.motorPowerID); //Set the output to a ceratin percent of of the input
        }
    }
	
	public double strafeFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.strafePowerID) * 2, 1); //Set the output to a ceratin percent of of the input
        }
	}	
	
	public double rotateFilter(double raw) {
		if (Math.abs(raw) < (double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.deadZoneID)) {
            return 0; //If the value passed is less than 15% ignore it. This is reffered to as a deadzone
        } else {
            return  raw * Math.min((double)RobotMap.getValue(RobotMap.mecanumSubTable, RobotMap.rotateFilterID), 1); //Set the output to a ceratin percent of of the input
        }
	}
}