package net.codejava.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rede_social")
public class RedeSocial{
	@Id
	private Integer id;
	private String nomeRede;
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
	@Id
	@GeneratedValue
	@Column(name = "RDS_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}