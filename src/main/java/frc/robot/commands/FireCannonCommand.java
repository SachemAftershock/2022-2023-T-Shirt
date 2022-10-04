package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PneumaticConstants.PneumaticIDs;
import frc.robot.subsystems.PneumaticSubsystem;

public class FireCannonCommand extends CommandBase {
    
    private final PneumaticSubsystem mPneuamticSubsystem;
    private long startTime;
    private PneumaticIDs id;

    public FireCannonCommand(PneumaticSubsystem pneumaticSubsystem, PneumaticIDs id) {
        addRequirements(pneumaticSubsystem);
        mPneuamticSubsystem = pneumaticSubsystem;
        this.id = id;
    }

    @Override
    public void initialize() {
        mPneuamticSubsystem.setSolenoid(this.id, true); // fire cannon
        startTime = System.currentTimeMillis();
    }

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - startTime > 1000;
    }

    @Override
    public void end(boolean interrupted) {
        mPneuamticSubsystem.setSolenoid(this.id, false);
    }

}
