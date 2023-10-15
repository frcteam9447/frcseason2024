package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {

  private final WPI_VictorSPX motor1 = new WPI_VictorSPX(1);
  private final WPI_VictorSPX motor2 = new WPI_VictorSPX(2);
  private final WPI_VictorSPX motor3 = new WPI_VictorSPX(3);
  private final WPI_VictorSPX motor4 = new WPI_VictorSPX(4);

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
        motor1.set(0.6);
        motor2.set(0.6);
        motor3.set(-0.6);
        motor4.set(-0.6);
      } else {
        motor1.set(0);
        motor2.set(0);
        motor3.set(0);
        motor4.set(0);
      }
    } else {
      forwardMotion = !forwardMotion;
      if (forwardMotion) {
        movementCount++;
      }
      startTime = time;
    }

    if (movementCount >= 4) {
      motor1.set(0);
      motor2.set(0);
      motor3.set(0);
      motor4.set(0);
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

    motor1.set(left);
    motor2.set(left);
    motor3.set(-right);
    motor4.set(-right);
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
