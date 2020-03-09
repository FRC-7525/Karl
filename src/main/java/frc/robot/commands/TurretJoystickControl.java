package frc.robot.commands;

import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurretJoystickControl extends CommandBase {

  private final Turret m_turret;
  private final XboxController m_controller;

  public TurretJoystickControl(Turret turret, XboxController controller) {
    m_turret = turret;
    m_controller = controller;
    addRequirements(turret);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_controller.getRawButtonPressed(6)){
      m_turret.setFlyWheelPercent(0.1);
    }else if(m_controller.getRawButtonReleased(6)){
      m_turret.setFlyWheelPercent(0.0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
