package com.bet.me.model;

import java.io.Serializable;

public class Site implements Serializable{

	private static final long serialVersionUID = 1L;
	private String site_key;
	private String site_nice;
	private Long last_update;
	private Odds odds;
	public String getSite_key() {
		return site_key;
	}
	public void setSite_key(String site_key) {
		this.site_key = site_key;
	}
	public String getSite_nice() {
		return site_nice;
	}
	public void setSite_nice(String site_nice) {
		this.site_nice = site_nice;
	}
	public Long getLast_update() {
		return last_update;
	}
	public void setLast_update(Long last_update) {
		this.last_update = last_update;
	}
	public Odds getOdds() {
		return odds;
	}
	public void setOdds(Odds odds) {
		this.odds = odds;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
		result = prime * result + ((site_key == null) ? 0 : site_key.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Site other = (Site) obj;
		if (last_update == null) {
			if (other.last_update != null)
				return false;
		} else if (!last_update.equals(other.last_update))
			return false;
		if (site_key == null) {
			if (other.site_key != null)
				return false;
		} else if (!site_key.equals(other.site_key))
			return false;
		return true;
	}
	
	

}
