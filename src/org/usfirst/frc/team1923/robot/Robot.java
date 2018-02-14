package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1923.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.LEDSubsystem;
import org.usfirst.frc.team1923.robot.utils.pathfinder.TrajectoryStore;

public class Robot extends TimedRobot {

    public static DrivetrainSubsystem drivetrainSubsystem;
    public static ElevatorSubsystem elevatorSubsystem;
    public static IntakeSubsystem intakeSubsystem;
    public static LEDSubsystem ledSubsystem;

    public static OI oi;

    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();

    @Override
    public void robotInit() {
        System.out.println("Loading trajectories...");

        TrajectoryStore.loadTrajectories();

        System.out.println("Loaded all trajectories.");

        drivetrainSubsystem = new DrivetrainSubsystem();
        elevatorSubsystem = new ElevatorSubsystem();
        intakeSubsystem = new IntakeSubsystem();
        ledSubsystem = new LEDSubsystem();

        oi = new OI();

        SmartDashboard.putData("Autonomous Mode", this.chooser);
    }

    private void periodic() {
        Scheduler.getInstance().run();

        drivetrainSubsystem.tick();
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        this.periodic();
    }

    @Override
    public void autonomousInit() {
        this.autonomousCommand = this.chooser.getSelected();

        if (this.autonomousCommand != null) {
            this.autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
        this.periodic();
    }

    @Override
    public void teleopInit() {
        if (this.autonomousCommand != null) {
            this.autonomousCommand.cancel();
        }
    }


    @Override
    public void teleopPeriodic() {
        this.periodic();
    }

    @Override
    public void testPeriodic() {

    }

}
