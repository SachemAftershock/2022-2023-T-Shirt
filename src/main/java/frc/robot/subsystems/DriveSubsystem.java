package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.lib.AftershockDifferentialDrive;
import frc.lib.AftershockSubsystem;


public class DriveSubsystem extends AftershockSubsystem {

    private static DriveSubsystem mInstance;

    private final WPI_VictorSPX mDriveMotorPortA, mDriveMotorStarboardA;
    private final WPI_VictorSPX mDriveMotorPortB, mDriveMotorStarboardB;

    private final AftershockDifferentialDrive mDifferentialDrive;

    int PortDriveMotorPortA = 2;
    int PortDriveMotorPortB = 3;
    int PortDriveMotorStarboardA = 4;
    int PortDriveMotorStarboardB = 1;

    public DriveSubsystem() {
        mDriveMotorPortA = new WPI_VictorSPX(PortDriveMotorPortA);
        // mDriveMotorPortA.setOpenLoopRampRate(DriveConstants.kRampRateToMaxSpeed);
        mDriveMotorPortA.setNeutralMode(NeutralMode.Brake);
        mDriveMotorPortA.setInverted(false);

        mDriveMotorPortB = new WPI_VictorSPX(PortDriveMotorPortB);
        // mDriveMotorPortB.setOpenLoopRampRate(DriveConstants.kRampRateToMaxSpeed);
        mDriveMotorPortB.setNeutralMode(NeutralMode.Brake);
        mDriveMotorPortB.setInverted(false);
        mDriveMotorPortB.follow(mDriveMotorPortA);

        mDriveMotorStarboardA = new WPI_VictorSPX(PortDriveMotorStarboardA);
        // mDriveMotorStarboardA.setOpenLoopRampRate(DriveConstants.kRampRateToMaxSpeed);
        mDriveMotorStarboardA.setNeutralMode(NeutralMode.Brake);
        mDriveMotorStarboardA.setInverted(true);

        mDriveMotorStarboardB = new WPI_VictorSPX(PortDriveMotorStarboardB);
        // mDriveMotorStarboardB.setOpenLoopRampRate(DriveConstants.kRampRateToMaxSpeed);
        mDriveMotorStarboardB.setNeutralMode(NeutralMode.Brake);
        mDriveMotorStarboardB.setInverted(false);
        mDriveMotorStarboardB.follow(mDriveMotorStarboardA);

        MotorControllerGroup mPortVictorSPXGroup = new MotorControllerGroup(mDriveMotorPortA, mDriveMotorPortB);
        MotorControllerGroup mStarboardVictorSPXGroup = new MotorControllerGroup(mDriveMotorStarboardA, mDriveMotorStarboardB);

        mDifferentialDrive = new AftershockDifferentialDrive(mPortVictorSPXGroup, mStarboardVictorSPXGroup);
        mDifferentialDrive.setNeutralMode(NeutralMode.Brake);
        mDifferentialDrive.invertRight(true);

    }

    public void arcadeDrive(double xSpeed, double zRotation){
        mDifferentialDrive.arcadeDrive(xSpeed, zRotation);
    }

    public void tankDrive(double left, double right) {
        mDriveMotorPortA.set(left);
        mDriveMotorPortB.set(left);
        mDriveMotorStarboardA.set(right);
        mDriveMotorStarboardB.set(right);
    }

    public synchronized static DriveSubsystem getInstance() {
        if(mInstance == null) {
            mInstance = new DriveSubsystem();
        }
        return mInstance;
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void outputTelemetry() {
        // TODO Auto-generated method stub
        
    }

}
