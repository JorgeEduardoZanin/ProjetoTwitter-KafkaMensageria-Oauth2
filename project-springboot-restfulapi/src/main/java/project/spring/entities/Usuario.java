package project.spring.entities;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import project.spring.dto.request.LoginRequest;


@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_id")
	private UUID id;
	
	private String nome;
	
	@Column(name = "cpf_cnpj", unique = true, nullable = false, length = 14)
	private String cpf_cnpj;
	
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean primeiraCobranca;
	
	
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean assinante;

	
	@Column(unique = true)
	private String email;
	private Integer idade;
	private String password;
	private LocalDate dataExpiracaoAssinatura;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<NotificacaoPagamento> notificacoes;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Telefone> telList;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)      
	@JoinTable(
			  name = "tb_usuario_role",
			  uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "role_id"})
			)  
    private Set<Role> roles = new HashSet<>();
	
	public Usuario() {
    }
	
	

	public Usuario(String nome, String cpf_cnpj, String email, Integer idade, String password) {
		this.nome = nome;
		this.cpf_cnpj = cpf_cnpj;
		this.email = email;
		this.idade = idade;
		this.password = password;
	}



	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public List<Telefone> getTelList() {
		return telList;
	}

	public void setTelList(List<Telefone> telList) {
		this.telList = telList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public boolean isAssinante() {
		return assinante;
	}

	public void setAssinante(boolean assinante) {
		this.assinante = assinante;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}



	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}



	public boolean isPrimeiraCobranca() {
		return primeiraCobranca;
	}



	public void setPrimeiraCobranca(boolean primeiraCobranca) {
		this.primeiraCobranca = primeiraCobranca;
	}



	public LocalDate getDataExpiracaoAssinatura() {
		return dataExpiracaoAssinatura;
	}



	public void setDataExpiracaoAssinatura(LocalDate dataExpiracaoAssinatura) {
		this.dataExpiracaoAssinatura = dataExpiracaoAssinatura;
	}



	public List<NotificacaoPagamento> getNotificacoes() {
		return notificacoes;
	}



	public void setNotificacoes(List<NotificacaoPagamento> notificacoes) {
		this.notificacoes = notificacoes;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	
	
	public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(loginRequest.password(), this.password);
	}
	
}
