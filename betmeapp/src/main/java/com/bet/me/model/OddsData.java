package com.bet.me.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OddsData implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String sport_key;
	private String sport_nice;
	private List<String> teams;
	private Long commence_time;
	private String home_team;
	private List<Site> sites;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSport_key() {
		return sport_key;
	}
	public void setSport_key(String sport_key) {
		this.sport_key = sport_key;
	}
	public String getSport_nice() {
		return sport_nice;
	}
	public void setSport_nice(String sport_nice) {
		this.sport_nice = sport_nice;
	}
	public List<String> getTeams() {
		return teams;
	}
	public void setTeams(List<String> teams) {
		this.teams = teams;
	}
	public Long getCommence_time() {
		return commence_time;
	}
	public void setCommence_time(Long commence_time) {
		this.commence_time = commence_time;
	}
	public String getHome_team() {
		return home_team;
	}
	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}
	public List<Site> getSites() {
		return sites;
	}
	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* Not doing complex equals operation here as it might affect performance dealing with large objects*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OddsData other = (OddsData) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
