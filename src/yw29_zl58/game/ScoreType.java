package yw29_zl58.game;

import common.IUserMessageType;

public class ScoreType implements IUserMessageType{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int score_TeamA;
	int score_TeamB;
	
	public ScoreType(int scoreA,int scoreB)
	{
		score_TeamA=scoreA;
		score_TeamB=scoreB;
	}
	public int getScoreA()
	{
		return score_TeamA;
	}
	public int getScoreB()
	{
		return score_TeamB;
	}

}
