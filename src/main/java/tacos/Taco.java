package tacos;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {
	@NotNull
	@Size(min=5, message="name은 최소5자여야합니다.")
	private String name;

	@Size(min=1, message="최소 1가지 항목은 선택하셔야합니다.")
	private List<Ingredient> ingredients;
	
	private Long id;
	private Date createdAt;

	
}
