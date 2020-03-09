package frc.robot.commands;

import java.util.function.DoubleSupplier;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Drive command
 */
public class DriveWithController extends CommandBase {

  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;

  /**
   * Creates a new DriveWithController
   *
   * @param subsystem The subsystem used by this command.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DriveWithController(Drivetrain driveTrain, DoubleSupplier forward, DoubleSupplier rotation) {
    m_drivetrain = driveTrain;
    m_forward = forward;
    m_rotation = rotation;
    addRequirements(driveTrain);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
