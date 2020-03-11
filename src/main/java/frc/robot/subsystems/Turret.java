package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase {

  // The motors on the flywheel
  private WPI_TalonFX flywheel1 = new WPI_TalonFX(TurretConstants.flywheel1);
  private WPI_TalonFX flywheel2 = new WPI_TalonFX(TurretConstants.flywheel2);

  // The motor on the neck
  private WPI_TalonSRX neck = new WPI_TalonSRX(TurretConstants.neck);

  // The motor on the hood
  private WPI_TalonSRX hood = new WPI_TalonSRX(TurretConstants.hood);

  public Turret() {
    flywheel1.setInverted(false);
    flywheel1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, TurretConstants.PIDIDX, 10);
    flywheel1.setSensorPhase(false);

    flywheel2.setInverted(true);
    flywheel2.follow(flywheel1);

    neck.setInverted(false);
    neck.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, TurretConstants.PIDIDX, 10);
    neck.setSensorPhase(false);

    hood.setInverted(false);
    hood.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.Analog, TurretConstants.PIDIDX, 10);
    hood.setSensorPhase(false);
  }

  @Override
  public void periodic() {
  }

  public void setFlyWheelPercent(double percent){
    flywheel1.set(ControlMode.PercentOutput, percent);
  }

  public void setNeckPercent(double percent){
    neck.set(ControlMode.PercentOutput, percent);
  }

  public void setHoodPercent(double percent){
    hood.set(ControlMode.PercentOutput, percent);
  }

  public void setNeutralMode(NeutralMode mode){
    flywheel1.setNeutralMode(mode);
    flywheel2.setNeutralMode(mode);
    neck.setNeutralMode(mode);
    hood.setNeutralMode(mode);
  }

}