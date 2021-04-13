package com.bet.me.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Odds implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<BigDecimal> h2h;

	public List<BigDecimal> getH2h() {
		return h2h;
	}

	public void setH2h(List<BigDecimal> h2h) {
		this.h2h = h2h;
	}
	

}
