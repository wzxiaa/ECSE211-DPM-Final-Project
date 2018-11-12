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
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(60);
		elbowMotor.rotate(-105);
		foreArmMotor.rotate(-100);
		foreArmMotor.rotate(40);
	}
	
	public void grabLowerRing() {
		foreArmMotor.setSpeed(250);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		foreArmMotor.rotate(60);
		elbowMotor.rotate(-50);
		foreArmMotor.rotate(-100);
		foreArmMotor.rotate(100);
		elbowMotor.rotate(155);
		foreArmMotor.rotate(-60);
	}
	
	public void performColorDetection() {
		foreArmMotor.setSpeed(10);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		elbowMotor.rotate(-100);
		foreArmMotor.rotate(-20);
		elbowMotor.setSpeed(10);
		elbowMotor.rotate(-70);
		
	}
	
	
}
