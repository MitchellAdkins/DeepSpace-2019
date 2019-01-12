package org.usfirst.frc.team4611.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.subsystems.PortMan;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TalonMecanum;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.TurboTankDrive;
import org.usfirst.frc.team4611.robot.subsystems.drivetrain.interfaces.DriveTrain;
import org.usfirst.frc.team4611.robot.subsystems.petal.Petal;
import org.usfirst.frc.team4611.robot.subsystems.navigation.Navigation;
import org.usfirst.frc.team4611.robot.subsystems.trianglehatch.TriangleHatch;

public class SubsystemFactory {
    private Subsystem   s;
    private static SubsystemFactory    me;
    private static String   botMacAddress;  // value of environment variable for MAC Address
    
    private String   jankyMacAddress    = "00:80:2F:17:F8:3F";
    private String   wonkyMacAddress    = "00:80:2F:27:1D:E9";
    private String   zippyMacAddress    = "00:80:2F:25:B4:CA";
    private String   turboMacAddress    = "00:80:2F:27:04:C6";
    private String   footballMacAddress = "00:80:2F:17:D7:4B";

    private OI oi;

    private PortMan portMan  = new PortMan();

    private DriveTrain driveTrain;
    private Petal petal;
    private Navigation nav;
    private TriangleHatch triangleHatch;

    private SubsystemFactory() {
        // private constructor to enforce Singleton pattern
    }

    public static SubsystemFactory getInstance() {
        if (me == null) {
            me  = new SubsystemFactory();
        }
        return me;
    }

    public void init() throws Exception {
        
        botMacAddress   = System.getenv("MAC_ADDRESS");
        if (botMacAddress == null) {
            throw new Exception("Could not find MAC Address for this bot. Make sure /home/lvuser/.bash_profile is correct");
        }
        // subsystems common to every bot
        initCommon();

        if (botMacAddress.equals(jankyMacAddress)) {
            initJanky();
        } else if (botMacAddress.equals(footballMacAddress)) {
            initFootball();
        } else if (botMacAddress.equals(wonkyMacAddress)) {
            initWonky();
        } else if (botMacAddress.equals(zippyMacAddress)) {
            initZippy();
        } else if (botMacAddress.equals(turboMacAddress)) {
            initTurbo();
        } else {
            System.err.println("Unrecognized MAC Address [" + botMacAddress + "]");
        }
        
        // do this last. OI commands need Subsystems to be alive before they init 
        oi = new OI();
    }

    /**
     * init subsystems that are common to all bots
     */
    private void initCommon() {
    }

    /**
     * init subsytems specific to Janky
     */
    private void initJanky() {
        System.out.println("initializing Janky");
        driveTrain = new TalonMecanum();
    }
    
    /**
     * init subsytems specific to Wonky
     */
    private void initWonky() {
        System.out.println("initializing Wonky");
        driveTrain = new TalonMecanum();
    } 

    /**
     * init subsytems specific to Zippy
     */
    private void initZippy() {
        System.out.println("initializing Zippy");
        driveTrain = new TalonMecanum();
    }
    
    /**
     * init subsytems specific to Turbo
     */
    private void initTurbo() {
        System.out.println("initializing Turbo");
        driveTrain = new TurboTankDrive();
    }

    /**
     * init subsystems specific to Football
     */
    private void initFootball() {
        System.out.println("initializing Football");
        triangleHatch   = new TriangleHatch();
        triangleHatch.init(portMan);
    }

    public DriveTrain getDriveTrain(){
        return driveTrain;
    }

    public Petal getPetal(){
        return petal;
    }
    
    public Navigation getNavigation(){
        return nav;
    }

    public TriangleHatch getTriangleHatch(){
        return triangleHatch;
    }
}