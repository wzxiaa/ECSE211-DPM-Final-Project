package ca.mcgill.ecse211.game;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;

public class RingRetrieval {
	
	private EV3LargeRegulatedMotor leftMotor;
	private EV3LargeRegulatedMotor rightMotor;
	private NXTRegulatedMotor elbowMotor;
	private EV3MediumRegulatedMotor foreArmMotor;
	
	private int foreArmSpeed = 150;
	private int elbowMotorRotationSpeed = 20;
	
	public RingRetrieval(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor, NXTRegulatedMotor elbowMotor, EV3MediumRegulatedMotor foreArmMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		this.elbowMotor = elbowMotor;
		this.foreArmMotor = foreArmMotor;
	}

	
	public void grabUpperRing() {
		foreArmMotor.setSpeed(foreArmSpeed);
		elbowMotor.setSpeed(50);
		foreArmMotor.rotate(75);
		elbowMotor.rotate(-103);//105
		foreArmMotor.rotate(-115);
		foreArmMotor.rotate(40);
	}
	
	public void grabLowerRing() {
		foreArmMotor.setSpeed(250);
		elbowMotor.setSpeed(50);
		foreArmMotor.rotate(75);
		elbowMotor.rotate(-42);
		foreArmMotor.rotate(-100);
		foreArmMotor.rotate(100);
		elbowMotor.rotate(145);
		foreArmMotor.rotate(-75);
	}	
	
}
