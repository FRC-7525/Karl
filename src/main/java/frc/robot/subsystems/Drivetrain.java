package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.Constants.DriveConstants;

public class Drivetrain extends SubsystemBase {

  // The motors on the left side of the drive.
  private WPI_TalonFX leftFront = new WPI_TalonFX(DriveConstants.kLeftFront);
  private WPI_TalonFX leftBack = new WPI_TalonFX(DriveConstants.kLeftBack);

  // The motors on the right side of the drive.
  private WPI_TalonFX rightFront = new WPI_TalonFX(DriveConstants.kRightFront);
  private WPI_TalonFX rightBack = new WPI_TalonFX(DriveConstants.kRightBack);

  // The robot's drive
  private DifferentialDrive robotDrive = new DifferentialDrive(leftFront, rightFront);

  // The gyro sensor
  private AHRS navx = new AHRS(SPI.Port.kMXP);

  // Shifter Solenoid
  Solenoid shifter = new Solenoid(DriveConstants.kShifterSolenoid);

  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

  public Drivetrain() {
    leftFront.setInverted(false);
    leftFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, DriveConstants.PIDIDX, 10);
    leftFront.setSensorPhase(false);
    leftFront.setNeutralMode(NeutralMode.Brake);

    leftBack.setInverted(InvertType.FollowMaster);
    leftBack.follow(leftFront);
    leftBack.setNeutralMode(NeutralMode.Brake);

    rightFront.setInverted(false);
    rightFront.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, DriveConstants.PIDIDX, 10);
    rightFront.setSensorPhase(true);
    rightFront.setNeutralMode(NeutralMode.Brake);

    rightBack.setInverted(InvertType.FollowMaster);
    rightBack.follow(rightFront);
    rightBack.setNeutralMode(NeutralMode.Brake);

    zeroHeading();
    resetOdometry(new Pose2d());
  }

  public double getLeftPosition(){
    return leftFront.getSelectedSensorPosition(DriveConstants.PIDIDX) * DriveConstants.encoderConstant;
  }

  public double getLeftVelocity(){
    return leftFront.getSelectedSensorVelocity(DriveConstants.PIDIDX) * DriveConstants.encoderConstant * 10;
  }

  public double getRightPosition(){
    return rightFront.getSelectedSensorPosition(DriveConstants.PIDIDX) * DriveConstants.encoderConstant;
  }

  public double getRightVelocity(){
    return rightFront.getSelectedSensorVelocity(DriveConstants.PIDIDX) * DriveConstants.encoderConstant * 10;
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftPosition(), getRightPosition());
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftVelocity(), getRightVelocity());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }
  
  public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
    robotDrive.arcadeDrive(forwardSpeed, rotationSpeed, true);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftFront.setVoltage(leftVolts);
    rightFront.setVoltage(-rightVolts);
    robotDrive.feed();
  }

  public void resetEncoders() {
    leftFront.setSelectedSensorPosition(0, DriveConstants.PIDIDX, 10);
    rightFront.setSelectedSensorPosition(0, DriveConstants.PIDIDX, 10);
  }

  public double getAverageEncoderDistance() {
    return (getLeftPosition() + getRightPosition()) / 2.0;
  }

  public void setMaxOutput(double maxOutput) {
    robotDrive.setMaxOutput(maxOutput);
  }

  public void zeroHeading() {
    navx.reset();
  }

  public double getHeading() {
    return Math.IEEEremainder(navx.getAngle(), 360) * -1;
  }

  public double getTurnRate() {
    return navx.getRate() * -1;
  }

}
