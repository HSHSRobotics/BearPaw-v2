/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.DriveTrain_Subsystem;
import frc.robot.subsystems.Limelight_Subsystem;
import frc.robot.subsystems.NavXIMU_Subsystem;

public class Find_Target extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Limelight_Subsystem n_subsystem;
  private final DriveTrain_Subsystem m_subsystem;
  /**
   * Creates a new Find_Target.
   */
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  int P = 1;
  float error = 0;

  public Find_Target(Limelight_Subsystem subsystem, DriveTrain_Subsystem subsystem2) {
    n_subsystem = subsystem;
    m_subsystem = subsystem2;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    addRequirements(subsystem2);
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    float steer_speed = 0.5f;
    float direction = 1f; 
    m_subsystem.arcadeDrive(0, steer_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Target_Detected() == true) {
      return true;
    }
    else {
      return false;
    }
  }
  public boolean Target_Detected() {
    int tv = (int)n_subsystem.Get_Target();
    double ta = n_subsystem.Get_Area();
    int tx = (int)n_subsystem.Get_X();

    if((tv == 1) && (ta > 2.2)) {
      return true;

    }
    else {
      return false;
    }
  }
}
