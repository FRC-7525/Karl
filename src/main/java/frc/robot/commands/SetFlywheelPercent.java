package frc.robot.commands;

import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetFlywheelPercent extends CommandBase {

  private final Turret m_turret;

  private final double m_percent;

  public SetFlywheelPercent(Turret turret, double percent) {
    m_turret = turret;
    m_percent = percent;
    addRequirements(turret);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_turret.setFlyWheelPercent(m_percent);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
