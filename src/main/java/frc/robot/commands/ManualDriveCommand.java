package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.AftershockXboxController;
import frc.robot.subsystems.DriveSubsystem;
import frc.lib.AccelerationMapping;

/**
 * Default Manual Operator Drive Command
 * 
 * @author Shreyas Prasad
 */
public class ManualDriveCommand extends CommandBase {
    private DriveSubsystem mDrive;
    private AftershockXboxController mController;
    AccelerationMapping accelerationLimiter;

    /**
     * Constructor for ManualDriveCommand Class
     * 
     * @param drive DriveSubsystem singleton instance
     * 
     * @param controller Primary Xbox Controller
     */
    public ManualDriveCommand(DriveSubsystem drive, AftershockXboxController controller) {
        mDrive = drive;
        mController = controller;
        addRequirements(mDrive);
    }

    
    @Override
    public void execute() {

        final double right = mController.getLeftDeadbandY();
        final double left = mController.getRightDeadbandX();

        mDrive.arcadeDrive(right, left);

        /*
        if(RobotContainer.getInstance().isAlternativeControlScheme()){
            final double forward = -(AccelerationMapping.linearShapeStraight(mController.getRightTriggerAxis(), mController.getkJoystickDeadbandToleranceY()));
            final double backward = -(AccelerationMapping.linearShapeStraight(-(mController.getLeftTriggerAxis()), mController.getkJoystickDeadbandToleranceY()));
            final double pow = forward + backward;
            final double rot = AccelerationMapping.linearShapeTurn(mController.getLeftDeadbandX(), mController.getkJoystickDeadbandToleranceX());
            mDrive.manualDrive(pow, rot, false /*wantDeccelerate*///);
            //System.out.println("power " + pow);
        /*} else {
            final double pow = (AccelerationMapping.linearShapeStraight(mController.getLeftDeadbandY(), mController.getkJoystickDeadbandToleranceY()));
            final double rot = (AccelerationMapping.linearShapeTurn(mController.getRightDeadbandX(), mController.getkJoystickDeadbandToleranceX())) ;
            final boolean leftTriggerPressed = mController.getLeftTriggerPressed();
            final boolean rightTriggerPressed = mController.getRightTriggerPressed();
            final boolean wantDeccelerate = leftTriggerPressed || rightTriggerPressed;
            mDrive.manualDrive(pow, rot, wantDeccelerate);
            //System.out.println("power" + pow);
        }
        */

    } 
}

