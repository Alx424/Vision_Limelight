public class PIDController {
  private double kP;
  private double kI;
  private double kD;
  private double target, currentError, lastError, outputMax, outputMin, current, errorSum;
  private double integralRange = 10;

  public PIDController(double current, double target, double outputMax, double outputMin, double kP, double kI, double kD, double integralRange) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.current = current;
    this.target = target;
    this.outputMax = outputMax;
    this.outputMin = outputMin;
    this.integralRange = integralRange;
    this.lastError = 0;

    this.currentError = target - current;
  }
  public double getOutput() {
    return capOutput(currentError*kP + (currentError-lastError)*kD + errorSum*kI);
  }
  public double capOutput(double val) {
    // caps the output value by using if else loops
    if(val > outputMax) {
      val = outputMax;
    } else if (val < outputMin) {
      val = outputMin;
    }
    return val;
  }
  public void updateError(double current) {
    // updating the error
    lastError = currentError;
    currentError = target - current;
    errorSum += Math.abs(currentError)<integralRange?currentError:0;
  }
}