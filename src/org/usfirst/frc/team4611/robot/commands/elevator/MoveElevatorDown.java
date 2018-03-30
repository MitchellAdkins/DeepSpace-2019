package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorDown extends Command{
	
	public MoveElevatorDown(){
		this.requires(Robot.elevator); //This command uses this subsystem
	}

	protected void execute() {	
		double speed = (double)RobotMap.getValue(RobotMap.elevatorSubtable, RobotMap.elevatorDownSpeed);
		if(RobotMap.elevator_Talon.getSelectedSensorPosition(0) <= Elevator.ELEVATOR_TOP * .2) {
			double error = RobotMap.elevator_Talon.getSelectedSensorPosition(0)/((Robot.elevator.ELEVATOR_TOP)*.2);
			Robot.elevator.move(Math.max(Math.min(speed * 0.75, speed * error), .2));
		}
		else {
			Robot.elevator.move(speed);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
		// TODO Auto-generated method stub
	}
	
	protected void end() {
		Robot.elevator.move(0);
	}
}