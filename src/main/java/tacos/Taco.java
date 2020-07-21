package tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Taco {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	private Date createdAt;
	
	@NotNull
	@Size(min=5, message="name은 최소5자여야합니다.")
	private String name;

	@ManyToMany(targetEntity=Ingredient.class)
	@Size(min=1, message="최소 1가지 항목은 선택하셔야합니다.")
	private List<Ingredient> ingredients;
	
	@PrePersist
	void createAt() {
		this.createdAt = new Date();
	}
}
