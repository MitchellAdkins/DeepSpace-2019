package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ExtendSolenoid extends Command{
	private Timer time;

	public ExtendSolenoid() {
		time = new Timer();
		this.requires(Robot.sol);
	}
	
	protected void execute() {
		time.reset();
		time.start();
		Robot.sol.move(DoubleSolenoid.Value.kForward);
		RobotMap.log(RobotMap.solenoidSubtable, "Extending solenoid" );
		Robot.sol.isRetracted = false;
	}
	
	protected boolean isFinished() {
		if(time.get() >= 0.2){
			time.stop();
			return true;
		}
		return false;
	}
	
	protected void end() {
		RobotMap.log(RobotMap.solenoidSubtable, "Done extending solenoid" );
		Robot.sol.isRetracted = false;
	}
	
	protected void interrupted(){
		Robot.sol.move(DoubleSolenoid.Value.kOff);
		Robot.sol.isRetracted = false;
	}
}
