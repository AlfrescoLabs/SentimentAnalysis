package org.alfresco.ml.action.bean;

public class RankingBean {
	
	private Long positive;
	private Long negative;
	private Long neutral;
	
	public Long getPositive() {
		return positive;
	}
	public void setPositive(Long positive) {
		this.positive = positive;
	}
	public Long getNegative() {
		return negative;
	}
	public void setNegative(Long negative) {
		this.negative = negative;
	}
	public Long getNeutral() {
		return neutral;
	}
	public void setNeutral(Long neutral) {
		this.neutral = neutral;
	}

}
