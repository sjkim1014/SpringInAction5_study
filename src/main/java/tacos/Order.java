package tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class Order {

	@NotBlank(message="name은 필수값입니다.")
	private String deliveryName;

	@NotBlank(message="도로명은 필수값입니다.")
	private String deliveryStreet;

	@NotBlank(message="시/군은 필수값입니다.")
	private String deliveryCity;

	@NotBlank(message="도는 필수값입니다.")
	private String deliveryState;
	
	@NotBlank(message="우편번호는 필수값입니다.")
	private String deliveryZip;

//	@CreditCardNumber(message="유효하지 않은 카드번호입니다.")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="MM/YY 형태로 입력하세요.")
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message="Invalud CVV")
	private String ccCVV;
	
	private Long id;
	private Date placedAt;

	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
		
	}
	
}
