package com.sprk.ExaminationPortal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@Column(name = "user_email", nullable = false, unique = true)
	private String emailId;
	@Column(name = "user_password", nullable = false)
	private String password;
	@Column(name = "user_first_name", nullable = false)
	private String firstName;
	@Column(name = "user_last_name", nullable = false)
	private String lastName;
	
}
