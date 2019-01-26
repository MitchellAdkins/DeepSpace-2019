package org.usfirst.frc.team4611.robot.subsystems.stick;

import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.stick.commands.RetractHatch;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Stick extends Subsystem {

    private DoubleSolenoid pusher;

    public Stick() {  
    }

    public void init(PortMan pm) {
        try{
           pusher = new DoubleSolenoid(pm.acquirePort(PortMan.pcm0_label, "Stick.inDoubleSolenoid"), pm.acquirePort(PortMan.pcm1_label, "Stick.outDoubleSolenoid"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    } 

    public void pushHatch() {
        pusher.set(DoubleSolenoid.Value.kForward);
    }

    public void retractPistons() {
        pusher.set(DoubleSolenoid.Value.kReverse);
    }

    public boolean isRetracted() {
        return pusher.get().equals(DoubleSolenoid.Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        this.setDefaultCommand(new RetractHatch());
    }   
}