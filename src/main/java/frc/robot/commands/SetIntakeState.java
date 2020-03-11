package frc.robot.commands;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.State;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetIntakeState extends CommandBase {

  private final Intake m_intake;

  private final State m_state;

  public SetIntakeState(Intake intake, State state) {
    m_intake  = intake;
    m_state = state;
    addRequirements(intake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.setState(m_state);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
