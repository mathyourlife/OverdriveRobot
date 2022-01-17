// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
//import edu.wpi.first.wpilibj.RobotDrive;


public class DrivetrainSubsystem extends SubsystemBase
 {


    private final PWMVictorSPX topRightMotor = new PWMVictorSPX(Constants.TOP_RT_MOTOR);
    private final PWMVictorSPX bottomRightMotor = new PWMVictorSPX(Constants.BOTTOM_RT_MOTOR);

    private final SpeedControllerGroup rightSideWheels = new SpeedControllerGroup(topRightMotor, bottomRightMotor);

    private final PWMVictorSPX topLeftMotor = new PWMVictorSPX(Constants.TOP_LFT_MOTOR);
    private final PWMVictorSPX bottomLeftMotor = new PWMVictorSPX(Constants.BOTTOM_LFT_MOTOR);

    private final SpeedControllerGroup leftSideWheels = new SpeedControllerGroup(topLeftMotor, bottomLeftMotor);

    private final PWMVictorSPX omniMotor1 = new PWMVictorSPX(Constants.OMNI_MOTOR1);
    private final PWMVictorSPX omniMotor2 = new PWMVictorSPX(Constants.OMNI_MOTOR2);

    private final SpeedControllerGroup omnis = new SpeedControllerGroup(omniMotor1, omniMotor2);



    private final Joystick controller = new Joystick(Constants.LEFT_JOYSTICK);
    private final Joystick rightJoystick = new Joystick(Constants.RIGHT_JOYSTICK);

    private final DifferentialDrive outsideWheels = new DifferentialDrive(leftSideWheels, rightSideWheels);


  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void leftJoystick()
  {
    if (controller.getDirectionDegrees()>337.5&&controller.getDirectionDegrees()<22.5  || controller.getDirectionDegrees()<-202.5&&controller.getDirectionDegrees()>157.5)// make condition such that it only acceps fwd and bcwds direction
    {
        outsideWheels.tankDrive(controller.getY()*0.8, controller.getY()*0.8);
    }

    if (controller.getDirectionDegrees()>67.5&&controller.getDirectionDegrees()<112.5||controller.getDirectionDegrees()<292.5&&controller.getDirectionDegrees()>247.5)// make angles for degrees for just omni
    {
      System.out.println("only omni running");
      omnis.set(controller.getX()*0.8);
    

    if (controller.getDirectionDegrees()<67.5&&controller.getDirectionDegrees()>22.5)// make for angles to top right diagonal
    {
      omnis.set(0.5);
      outsideWheels.tankDrive(0.5, 0.5);
    }

    if (controller.getDirectionDegrees()<337.5&&controller.getDirectionDegrees()>292.5)// make for angles to top left diagonal
    {
      omnis.set(-0.5);
      outsideWheels.tankDrive(0.5, 0.5);
    }

    if (controller.getDirectionDegrees()<157.5&&controller.getDirectionDegrees()>112.5)// make for angles to bottom right diagonal
    {
      omnis.set(0.5);
      outsideWheels.tankDrive(-0.5, -0.5);
    }

    if (controller.getDirectionDegrees()<247.5&&controller.getDirectionDegrees()>202.5)// make for angles to bottom left diagonal
    {
      omnis.set(-0.5);
      outsideWheels.tankDrive(-0.5, -0.5);
    }

  }
}

  public void rightJoystick()
  {
    if (rightJoystick.getX()>0.05)// make for angles so only turns right or left
    {
      outsideWheels.tankDrive(rightJoystick.getX()*0.8, rightJoystick.getX()*-0.8);
    }

    if (rightJoystick.getX()<-0.05)// make for angles so only turns right or left
    {
      outsideWheels.tankDrive(rightJoystick.getX()*-0.8, rightJoystick.getX()*0.8);
    }
  }


  public void stopTheBot()
  {
    outsideWheels.stopMotor();
  }
  


}
