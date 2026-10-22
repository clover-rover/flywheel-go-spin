package first.robot;

import static org.wpilib.units.Units.RPM;

import first.robot.Constants.OperatorConstants;
import first.robot.subsystems.FlywheelSubsystem;
import org.wpilib.command2.Command;
import org.wpilib.command2.button.CommandGamepad;
import org.wpilib.command2.button.Trigger;

/**
 * The RobotContainer.java of the WPILib 2027 Command v2 Robot template, with the FlywheelSubsystem
 * added by YAMG. Replace your project's RobotContainer.java with it.
 *
 * <p>This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  // Added by YAMG.
  private final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();

  private final CommandGamepad driverController = new CommandGamepad(
    OperatorConstants.DRIVER_CONTROLLER_PORT
  );

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // When no other command is using the flywheel, let the flywheel coast.
    flywheelSubsystem.setDefaultCommand(flywheelSubsystem.set(0));

    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link org.wpilib.command2.button.CommandGenericHID}'s
   * subclasses for {@link CommandGamepad Gamepad} gamepads or {@link
   * org.wpilib.command2.button.CommandJoystick Flight joysticks}.
   */
    private void configureBindings() {
      // Added by YAMG, in place of the template's example on the right face button.
      // Spin at a slow speed while the bottom (A) face button is held, and a fast one while the right (B) one is.
      driverController
        .faceDown()
        .whileTrue(flywheelSubsystem.run(RPM.of(2330.0)));
      driverController
        .faceRight()
        .whileTrue(flywheelSubsystem.run(RPM.of(4650.0)));
      // Drive the flywheel with a duty cycle while the left (X) or top (Y) face button is held.
      driverController.faceLeft().whileTrue(flywheelSubsystem.set(0.3));
      driverController.faceUp().whileTrue(flywheelSubsystem.set(-0.3));
  }
}
