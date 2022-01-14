package org.springframework.samples.petclinic.vacination;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VaccinationController {
	
	@Autowired
	VaccinationService vaccinationService;
	
	@Autowired
	PetService petService;
    
	@GetMapping("/vaccination/create")
	public String getForm(ModelMap model) {
		model.addAttribute("vaccination", new Vaccination());
		Collection<Pet> p=petService.findAllPets();
		List<Vaccine> v=vaccinationService.getAllVaccines();
		model.addAttribute("vaccines", v);
		model.addAttribute("pets", p);
		return "vaccination/createOrUpdateVaccinationForm";
	}
	
	@PostMapping("/vaccination/create")
	public String save(@Valid Vaccination v, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "vaccination/createOrUpdateVaccinationForm";
		}else {
			try {
				vaccinationService.save(v);
				
			} catch (UnfeasibleVaccinationException e) {
				model.addAttribute("message", "La mascota seleccionada no puede recibir la vacuna especificada");
				return "vaccination/createOrUpdateVaccinationForm";
			}
			
		}
		return "welcome";
	}
	
}
