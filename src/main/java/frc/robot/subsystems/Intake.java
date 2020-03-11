package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends SubsystemBase {

  public enum State {
    STOWED,
    HOPPER_LOADING,
    FLOOR_PICKUP
  }

  // The motor that changes the angle of arm
  private WPI_TalonSRX joint = new WPI_TalonSRX(IntakeConstants.joint);

  // The motor that spins the rollers to intake balls
  private WPI_VictorSPX roller = new WPI_VictorSPX(IntakeConstants.roller);

  // The solenoid that sets the linkage position for either stowed or hopper loading
  private Solenoid solenoid = new Solenoid(IntakeConstants.hopperSolenoid);

  public Intake() {
    joint.setInverted(false);
    joint.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, IntakeConstants.PIDIDX, 10);
    joint.setSensorPhase(false);

    roller.setInverted(false);
  }

  @Override
  public void periodic() {
  }

  public void setJointPosition(int position){
      // TODO implement
  }

  public void setRollerPercent(double percent){
    roller.set(ControlMode.PercentOutput, percent);
  }

  public void setState(State state){
      switch(state){
        case STOWED:
            solenoid.set(false);
            setJointPosition(IntakeConstants.stowedPosition);
            setRollerPercent(0.0);
            break;
        case HOPPER_LOADING:
            solenoid.set(true);
            setJointPosition(IntakeConstants.stowedPosition);
            setRollerPercent(1.0);
            break;
        case FLOOR_PICKUP:
            solenoid.set(false);
            setJointPosition(IntakeConstants.pickupPosition);
            setRollerPercent(1.0);
            break;
      }
  }

  public void setNeutralMode(NeutralMode mode){
    joint.setNeutralMode(mode);
    roller.setNeutralMode(mode);
  }

}