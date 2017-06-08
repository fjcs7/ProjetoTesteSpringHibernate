package net.codejava.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RedeSocial{
	@Id
	private Integer id;
	@Column
	private String nomeRede;
	@Column
	private String profile;
	
	//Metodos Getters and Setters
	public String getNomeRede() {
		return nomeRede;
	}
	public void setNomeRede(String nomeDaRede) {
		this.nomeRede = nomeDaRede;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}