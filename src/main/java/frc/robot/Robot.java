// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.lib.AftershockDifferentialDrive;
import frc.lib.AftershockXboxController;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * 
 * @author Arhum Mudassir
 * 
 * Robot code for 2022 T-Shirt Bot (Hopefully gets used at some point)
 * 
 */
public class Robot extends TimedRobot {
  
  private VictorSP mStarboardDriveMotorA; 
  private VictorSP mStarboardDriveMotorB; 
  private VictorSP mPortDriveMotorA; 
  private VictorSP mPortDriveMotorB; 

  private AftershockDifferentialDrive mDrive; 
  private MotorControllerGroup mStarboard;
  private MotorControllerGroup mPort;

  private AftershockXboxController mController;

  private Compressor mCompressor;
  private Solenoid mCannonA, mCannonB, mCannonC, mCannonD, mCannonE, mCannonF; 
  private PneumaticsControlModule mControlModule;
  private int mCounter; 

  private static final PneumaticsModuleType kModuleType = PneumaticsModuleType.CTREPCM;

  enum CannonState {
    eNone, eCannonA, eCannonB, eCannonC, eCannonD, eCannonE, eCannonF;
  }

  private CannonState mSelectedCannon; 
  private CannonState[] kCannonSequence = new CannonState[] {
    CannonState.eNone,
    CannonState.eCannonA,
    CannonState.eCannonB,
    CannonState.eCannonC,
    CannonState.eCannonD,
    CannonState.eCannonE,
    CannonState.eCannonF,
  };

  @Override
  public void robotInit() {

    mStarboardDriveMotorA = new VictorSP(0);
    mStarboardDriveMotorB = new VictorSP(1);
    mPortDriveMotorA = new VictorSP(2);
    mPortDriveMotorA = new VictorSP(3);

    mStarboard = new MotorControllerGroup(mStarboardDriveMotorA, mStarboardDriveMotorB);
    mPort = new MotorControllerGroup(mPortDriveMotorA, mPortDriveMotorB);
    mDrive = new AftershockDifferentialDrive(mStarboard, mPort);

    mController = new AftershockXboxController(0);

    mControlModule = new PneumaticsControlModule(0);
    mCannonA = new Solenoid(kModuleType, 0);
    mCannonB = new Solenoid(kModuleType, 1);
    mCannonC = new Solenoid(kModuleType, 2);
    mCannonD = new Solenoid(kModuleType, 3);
    mCannonE = new Solenoid(kModuleType, 4);
    mCannonF = new Solenoid(kModuleType, 5);
    mCounter = 0;

  }
  
  @Override
  public void robotPeriodic() {}

  @Override
  public void teleopInit() {

    mCounter = 0;
    
    mCannonA.set(false);
    mCannonB.set(false);
    mCannonC.set(false);
    mCannonD.set(false);
    mCannonE.set(false);
    mCannonF.set(false);

    mSelectedCannon = kCannonSequence[mCounter];
    mControlModule.enableCompressorDigital();

  }

  @Override
  public void teleopPeriodic() {

    double pow = mController.getLeftDeadbandY();
    double rot = mController.getRightDeadbandX();

    mDrive.arcadeDrive(pow, rot, false); //set true if somethings wrong, forgot what sqaure inputs did

    if(mController.getAButtonPressed()) {
      increment();
    }

    switch(mSelectedCannon) {
      case eNone: 
        mCannonF.set(false);
        break;
      case eCannonA :
        mCannonA.set(true);
        break;
      case eCannonB :
        mCannonA.set(false);
        mCannonB.set(true);
        break;
      case eCannonC :
        mCannonB.set(false);
        mCannonC.set(true);
        break;
      case eCannonD :
        mCannonC.set(false); 
        mCannonD.set(true);
        break;
      case eCannonE :
        mCannonD.set(false);
        mCannonE.set(true);
        break;
      case eCannonF :
        mCannonE.set(false);
        mCannonF.set(true);
        break;
    }


  }

  @Override
  public void disabledInit() {
    mCompressor.disable();
  }

  public void increment() {
    if(mCounter <= 7) {
      mCounter++;
    } else {
      mCounter = 0;
    }
    mSelectedCannon = kCannonSequence[mCounter];
  }

}
