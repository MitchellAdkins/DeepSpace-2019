package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ResetElevator extends Command{
	
	public ResetElevator(){
		this.requires(Robot.el); //This command uses this subsystem
	}

	protected void execute() {
		Robot.el.move((double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed));
	}	
	@Override
	protected boolean isFinished() {
		System.out.println(Robot.el.isSwitchSet());
		return Robot.el.isSwitchSet();
	}
	
	protected void end() {
		RobotMap.elevator_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.elevator_Talon.configReverseSoftLimitThreshold(-117328, 0); //upper limit
		RobotMap.elevator_Talon.configForwardSoftLimitThreshold(0, 0); //lower limit
		RobotMap.elevator_Talon.configForwardSoftLimitEnable(true, 0);
		RobotMap.elevator_Talon.configReverseSoftLimitEnable(true, 0);
	}
}