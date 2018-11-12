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
	
	public void ringScanning() {
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		elbowMotor.rotate(-180);
		elbowMotor.rotate(180);
	}
	
	public void grab() {
		foreArmMotor.setSpeed(foreArmSpeed);
		elbowMotor.setSpeed(elbowMotorRotationSpeed);
		//foreArmMotor.rotate(30);
		elbowMotor.rotate(-130);
		foreArmMotor.rotate(-40);
	}
	
	
}
