package org.springframework.samples.petclinic.vacination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vaccine extends BaseEntity{
    
	@Size(min=3, max=50)
	@NotEmpty
	@Column(unique=true)
    String name;
	
	@Min(0)
    Double price;
    
    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name="pet_type_id")
    PetType petType;
}
