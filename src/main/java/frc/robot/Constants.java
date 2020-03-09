package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int kLeftFront = 1;
        public static final int kLeftBack = 0;
        public static final int kRightFront = 2;
        public static final int kRightBack = 3;

        public static final int kShifterSolenoid = 0;

        public static final double WHEEL_DIAMETER = 0.1524; // 6in in meters
        public static final double ENCODER_EDGES_PER_REV = 4096;
        public static final int PIDIDX = 0;
        public static final double encoderConstant = (1 / ENCODER_EDGES_PER_REV) * WHEEL_DIAMETER * Math.PI;

        // TODO: Need to tune these for the new robot
        public static final double ksVolts = 0.951;
        public static final double kvVoltSecondsPerMeter = 4.21;
        public static final double kaVoltSecondsSquaredPerMeter = 0.837;
        public static final double kPDriveVel = 10;

        public static final double kTrackwidthMeters = 0.6748; // Needs to be calculated
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);
    }

    public static final class TurretConstants {
        public static final int flywheel1 = 4;
        public static final int flywheel2 = 5;

        public static final int neck = 0; // Needs to be set
        public static final int hood = 0; // Needs to be set

        public static final int PIDIDX = 0;

        public static final double kFlywheelP = 0.0;
        public static final double kFlywheelI = 0.0;
        public static final double kFlywheelD = 0.0;
        public static final double kFlywheelF = 0.0;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 1.0;
        public static final double kMaxAccelerationMetersPerSecondSquared = 0.5;

        // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
    }

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0;
      }
}
