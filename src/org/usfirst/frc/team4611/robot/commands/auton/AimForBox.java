package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.commands.drive.VisionDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimForBox extends CommandGroup{
	
	/**
	 * Drives forward until a certain distance from a surface
	 */
	public AimForBox(){
		addSequential(new VisionDrive(),2);
		
	}
}
