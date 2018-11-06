package odometer;

//package ca.mcgill.ecse211.lab4;

/**
 * This class is used to handle errors regarding the singleton pattern used for the odometer and
 * odometerData
 * @author Wenzong
 * @author Lucas
 *
 */
@SuppressWarnings("serial")
public class OdometerExceptions extends Exception {

  public OdometerExceptions(String Error) {
    super(Error);
  }

}
