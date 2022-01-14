package org.springframework.samples.petclinic.vacination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccinationService {
	
	@Autowired
	VaccinationRepository vaccinationRepository;
	
    public List<Vaccination> getAll(){
        return vaccinationRepository.findAll();
    }

    public List<Vaccine> getAllVaccines(){
        return vaccinationRepository.findAllVaccines();
    }

    public Vaccine getVaccine(String typeName) {
        return vaccinationRepository.getVaccine(typeName);
    }

    @Transactional(rollbackFor=UnfeasibleVaccinationException.class)
    public Vaccination save(Vaccination p) throws UnfeasibleVaccinationException {
        if(!p.getVaccinatedPet().getType().equals(p.getVaccine().getPetType())) {
        	throw new UnfeasibleVaccinationException();
        }else {
        	return vaccinationRepository.save(p);
        }      
    }

    
}
