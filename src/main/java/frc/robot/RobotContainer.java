// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.AftershockXboxController;
import frc.robot.commands.FireCannonCommand;
import frc.robot.commands.ManualDriveCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.PneumaticSubsystem;

import static frc.robot.Constants.PneumaticConstants.PneumaticIDs.*;


public class RobotContainer {
  private static RobotContainer mInstance;

  private DriveSubsystem mDriveSubsystem;
  private PneumaticSubsystem mPneumaticSubsystem;

  private final AftershockXboxController mControllerPrimary = new AftershockXboxController(0);
  //private final AftershockXboxController mControllerSecondary = new AftershockXboxController(1);

  private JoystickButton bFireCannon0, bFireCannon1, bFireCannon2, bFireCannon3, bFireCannon4, bFireCannon5;

  public RobotContainer() {
    // Configure the button bindings
    mDriveSubsystem = DriveSubsystem.getInstance();
    mPneumaticSubsystem = PneumaticSubsystem.getInstance();

    CommandScheduler.getInstance().setDefaultCommand(mDriveSubsystem, new ManualDriveCommand(mDriveSubsystem, mControllerPrimary));

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    bFireCannon0 = new JoystickButton(mControllerPrimary, XboxController.Button.kA.value);
    bFireCannon0.whenPressed(new FireCannonCommand(mPneumaticSubsystem, eSolenoid0));

    bFireCannon1 = new JoystickButton(mControllerPrimary, XboxController.Button.kB.value);
    bFireCannon1.whenPressed(new FireCannonCommand(mPneumaticSubsystem, eSolenoid1));

    bFireCannon2 = new JoystickButton(mControllerPrimary, XboxController.Button.kX.value);
    bFireCannon2.whenPressed(new FireCannonCommand(mPneumaticSubsystem, eSolenoid2));

    bFireCannon3 = new JoystickButton(mControllerPrimary, XboxController.Button.kY.value);
    bFireCannon3.whenPressed(new FireCannonCommand(mPneumaticSubsystem, eSolenoid3));

    bFireCannon4 = new JoystickButton(mControllerPrimary, XboxController.Button.kRightBumper.value);
    bFireCannon4.whenPressed(new FireCannonCommand(mPneumaticSubsystem, eSolenoid4));

    bFireCannon5 = new JoystickButton(mControllerPrimary, XboxController.Button.kLeftBumper.value);
    bFireCannon5.whenPressed(new FireCannonCommand(mPneumaticSubsystem, eSolenoid5));
  }

  public void initialize() {
    mPneumaticSubsystem.startCompessor();
  }

  public AftershockXboxController getControllerPrimary() {
      return mControllerPrimary;
  }

  /*
  public AftershockXboxController getControllerSecondary() {
      return mControllerSecondary;
  }
  */

  public synchronized static RobotContainer getInstance() {
      if(mInstance == null) {
          mInstance = new RobotContainer();
      }
      return mInstance;
  }
}
