package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {

  WPI_VictorSPX driveLeftA = new WPI_VictorSPX(1);
  WPI_VictorSPX driveLeftB = new WPI_VictorSPX(2);
  WPI_VictorSPX driveRightA = new WPI_VictorSPX(3);
  WPI_VictorSPX driveRightB = new WPI_VictorSPX(4);

  private final Joystick joy1 = new Joystick(0);

  private double startTime;
  private int movementCount = 0;
  private boolean forwardMotion = true;

  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    if (time - startTime < 1) {
      if (forwardMotion) {
        driveLeftA.set(0.6);
        driveLeftB.set(0.6);
        driveRightA.set(-0.6);
        driveRightB.set(-0.6);
      } else {
        driveLeftA.set(0);
        driveLeftB.set(0);
        driveRightA.set(0);
        driveRightB.set(0);
      }
    } else {
      forwardMotion = !forwardMotion;
      if (forwardMotion) {
        movementCount++;
      }
      startTime = time;
    }

    if (movementCount >= 4) {
      driveLeftA.set(0);
      driveLeftB.set(0);
      driveRightA.set(0);
      driveRightB.set(0);
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1) * 0.6;
    double turn = joy1.getRawAxis(4) * 0.3;

    double left = speed + turn;
    double right = speed - turn;

    driveLeftA.set(left);
    driveLeftB.set(left);
    driveRightA.set(left);
    driveRightB.set(left);
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
