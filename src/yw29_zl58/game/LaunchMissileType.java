package yw29_zl58.game;

import common.IUserMessageType;

public class LaunchMissileType implements IUserMessageType {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7168536303293348000L;
	double[] p;

	public LaunchMissileType(double[] coor) {
		this.p = coor;
	}

	public double[] getPosition() {
		return p;
	}

}
