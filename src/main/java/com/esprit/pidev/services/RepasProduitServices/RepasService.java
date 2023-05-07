package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.Forum.Post;
import com.esprit.pidev.entities.ProduitRepas.*;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.NutritionRepository;
import com.esprit.pidev.repository.RepasproduitRepository.RepasRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
@AllArgsConstructor
public class RepasService implements IRepas {
    UserRepository userRepository;
    RepasRepository repasRepository;
    NutritionRepository nutritionRepository;


    public User getCurrentUserObjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public Repas addRepas(Repas rep) {
        return repasRepository.save(rep);
    }

    @Override
    public Repas updateRepas(Repas rep)  {
        repasRepository.save(rep);
        return rep;
    }

    @Override
    public Repas retrieveRepasById(Long id) {
        return repasRepository.findById(id).orElse(null);
    }

    @Override
    public List<Repas> retrieveAllRepas() {
        return repasRepository.findAll();
    }

    @Override
    public void deleteRepas(Repas rep) {
        repasRepository.delete(rep);
    }

    @Override
    public int calculerCaloriesTotales(List<Repas> repasChoisis,long id) {
        int caloriesTotales = 0;
        User user = userRepository.findById(id).get();
        long maxCalories = calculerMaxCalories(user.getId());
        for (Repas repas : repasChoisis) {
            caloriesTotales += repas.getNutrition().getCalories();
        }
        System.out.println("le nombre totale des calories est : " + caloriesTotales);
        //notifier le client qu'il excede le maxuimum de calories qu'il doit consommer
        if (caloriesTotales > maxCalories) {
            System.out.println("Le total des calories dépasse le maximum autorisé !");
        }
        proposerRepasSelonObjectifEtActivite(id);
        return caloriesTotales;

    }

    @Override
    public void updateRepasBloqueStatus() {
        repasRepository.blockRepasWithTooManyReclamations();
    }
    public String checkMealNutrition(Repas repas) {
        // Logique de vérification des valeurs nutritionnelles d'un repas
        // Exemple : vérifier si les calories dépassent la limite recommandée
        if (repas.getNutrition().getCalories() >600 ) {
            return ("Les calories du repas dépassent la limite recommandée.");
        }
        return "";
    }

    //calculer le nombre maximum qu'un client doit consommer par jour
    @Override
    public long calculerMaxCalories(long id) {
        User user = userRepository.findById(id).get();
        double metabolismeDeBase = 0;
        if (user.getId() != null) {
            if (user.getGender().equals("Homme")) {
                metabolismeDeBase = 88.362 + (13.397 * user.getPoids()) + (4.799 * user.getTaille()) - (5.677 * user.getAge());
            }
            else {
                metabolismeDeBase = 447.593 + (9.247 * user.getPoids()) + (3.098 * user.getTaille()) - (4.330 * user.getAge());
            }
            return Math.round(metabolismeDeBase);
        } else {
            throw new IllegalArgumentException("L'objet User est null");
        }
    }


    //proposer des repas selon les activités des utilisateur


    @Override
    public List<Repas> rechercherRepasParNom(String nom) {

        return repasRepository.findByNomContainingIgnoreCase(nom);
    }

    @Override
    public Repas addRepasAndImage(String nom, String description, double prix, String ingredient, String allergene, ObjectifType objectifType, CategRepas categRepas, MultipartFile image,long user) throws IOException {
        Repas pt = new Repas();
        pt.setNom(nom);
        pt.setDescription(description);
        pt.setPrix(prix);
        pt.setIngredient(ingredient);
        pt.setAllergene(allergene);
        pt.setObjectif(objectifType);
        pt.setCategorieRep(categRepas);
        // pt.setNutrition(nutritionRepository.findById(nutritionId).orElse(null));
        pt.setUser(userRepository.findById(user).get());
        byte[] imageData = image.getBytes();
        System.err.println(imageData.toString());
        pt.setImageData(imageData);
        // Save the image file to a folder named 'images' in your project directory
        Path directory = Paths.get("images");
        if (!Files.exists(directory)) {Files.createDirectories(directory);}
        Path imagePath = directory.resolve(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(image.getOriginalFilename()));
        Files.write(imagePath, imageData);
        repasRepository.save(pt);
        return pt;
    }
    @Override
    public Repas updateRepasAndImage(long id,String nom, String description, double prix, String ingredient, String allergene, ObjectifType objectifType, CategRepas categRepas, MultipartFile image,long user) throws IOException {
        Repas pt = new Repas();
        pt.setId(id);
        pt.setNom(nom);
        pt.setDescription(description);
        pt.setPrix(prix);
        pt.setIngredient(ingredient);
        pt.setAllergene(allergene);
        pt.setObjectif(objectifType);
        pt.setCategorieRep(categRepas);
        pt.setUser(userRepository.findById(user).get());
        // pt.setNutrition(nutritionRepository.findById(nutritionId).orElse(null));
        //pt.setUser(userRepository.findById(user).orElse(null));
        byte[] imageData = image.getBytes();
        System.err.println(imageData.toString());
        pt.setImageData(imageData);
        // Save the image file to a folder named 'images' in your project directory
        Path directory = Paths.get("images");
        if (!Files.exists(directory)) {Files.createDirectories(directory);}
        Path imagePath = directory.resolve(UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(image.getOriginalFilename()));
        Files.write(imagePath, imageData);
        repasRepository.save(pt);
        return pt;
    }












   /* public List<Repas> proposerRepasSelonObjectifEtActivite() {
        User user = getCurrentUserObjects();
            ObjectifType objectifClient = user.getObjectif();
            TypeActivite typeActiviteClient = user.getActivite();
            double maxCalories = calculerMaxCalories(user);
            // Calculer les calories minimales et maximales en fonction de l'objectif et du type d'activité
            double minCalories = 0;
            double maxCaloriesProposees = 0;
            switch (objectifClient) {
                case Perdre_Poids:
                    minCalories = maxCalories * 0.75; // 75% des calories maximales
                    break;
                case Maintenir_Poids:
                    minCalories = maxCalories * 0.9; // 90% des calories maximales
                    break;
                case Prendre_Poids:
                    minCalories = maxCalories * 1.1; // 110% des calories maximales
                    break;
            }
            switch (typeActiviteClient) {
                case ACTIF:
                    maxCaloriesProposees = maxCalories * 1.1; // 110% des calories maximales
                    break;
                case MODEREMENT_ACTIF:
                    maxCaloriesProposees = maxCalories * 1.05; // 105% des calories maximales
                    break;
                case SEDENTAIRE:
                    maxCaloriesProposees = maxCalories; // Les mêmes calories maximales
                    break;
            }
            // Rechercher les repas qui ont une quantité de calories entre les calories minimales et maximales proposées et
            // qui correspondent à l'objectif du client
            List<Repas> repasProposes = repasRepository.findByCaloriesAndObjectif(minCalories,maxCaloriesProposees,objectifClient);
            return repasProposes;
    }*/

    @Override
    public Set<Repas> getRepasByUserId(long id) {
        Set<Repas> repas = repasRepository.findByUserId(id);
        for (Repas repasItem : repas) {
            if (repasItem.getImageData() != null) {
                try {
                    String imageBase64 = Base64.getEncoder().encodeToString(repasItem.getImageData());
                    repasItem.setImageBase64(imageBase64);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return repas;

    }

    @Override
    public List<Repas> proposerRepasSelonObjectifEtActivite(long id) {

        User user = userRepository.findById(id).get();
        // double maxCalories = calculerMaxCalories(user);

        List<Repas> mealsByUserGoal = new ArrayList<>();
        List<Repas> meals = repasRepository.findAll();
        for (Repas meal : meals) {
            if (meal.getObjectif().equals(user.getObjectif())) {
                mealsByUserGoal.add(meal);
            }
        }

        return mealsByUserGoal;
    }

    public List<Repas> getAllRepasAndImage() {
        List<Repas> repas = repasRepository.findAll();
        for (Repas repasItem : repas) {
            if (repasItem.getImageData() != null) {
                try {
                    String imageBase64 = Base64.getEncoder().encodeToString(repasItem.getImageData());
                    repasItem.setImageBase64(imageBase64);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return repas;
    }

    @Override
    public Repas addNutritionToRepas(Nutrition nutrition, long repasId) {
        Repas repas = repasRepository.findById(repasId).orElse(null);
        if (repas == null) {
            throw new EntityNotFoundException("Produit non trouvé pour l'identifiant " + repasId);
        }
        repas.setNutrition(nutrition);
        return repasRepository.save(repas);
    }

}



