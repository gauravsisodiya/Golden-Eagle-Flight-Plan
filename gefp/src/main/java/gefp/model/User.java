package gefp.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="users")
public class User implements Serializable,UserDetails{
	
	private static final long serialVersionUID = 1L;
	  
	@Id
	@GeneratedValue
	Integer id;
	
	@JsonIgnore
	@Column(nullable = false, unique = true)
	String cin;
	
	@JsonIgnore
    String username;

	@JsonIgnore
    String password;
    
    String emailId;
    
    @JsonIgnore
    @ElementCollection
    @CollectionTable(name = "authorities",
        joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles;

    
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName; 
    
    @JsonIgnore
    boolean enabled;
    
    @JsonIgnore
    @ManyToMany
    Set<Checkpoint> checkpoints;

    @JsonIgnore
    @OneToOne
    Department major;

    @JsonIgnore
    @OneToOne
    Plan plan;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Checkpoint> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(Set<Checkpoint> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public Department getMajor() {
		return major;
	}

	public void setMajor(Department major) {
		this.major = major;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	@JsonIgnore
	public boolean isSysadmin()
    {
        return roles.contains( "ROLE_ADMIN" );
    }
	
	@JsonIgnore
	public boolean hasRole( String role )
    {
        return roles.contains( role );
    }

	@JsonIgnore
	public String getName()
    {
        return firstName + " " + lastName;
    }

	@JsonIgnore
    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for( String role : roles )
            authorities.add( new SimpleGrantedAuthority( role ) );
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }
	

}