package com.esprit.pidev.services.RepasProduitServices;

import com.esprit.pidev.entities.ProduitRepas.Produit;
import com.esprit.pidev.entities.UserRole.User;
import com.esprit.pidev.repository.RepasproduitRepository.ProduitRepository;
import com.esprit.pidev.repository.UserRoleRepository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ProduitService implements IProduit{

    ProduitRepository produitRepository;
    UserRepository userRepository;

    public User getCurrentUserObjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        //return new User(user.getUsername(), user.getPassword(), user.getEmail());
        return user;
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //public void checkReclamationsByProduit(Long id) {
    // List<Object[]> result = produitRepository.countReclamationsByProduit();
    //// for (Object[] row : result) {
        //     String nomProduit = (String) row[0];
    // Long nombreReclamations = (Long) row[1];
    // if (nombreReclamations > 10) {
    //  Produit produit = produitRepository.findById(id).orElse(null);
    //  produit.setBloquee(true);
    //  produitRepository.save(produit);
    // }
    //}
    // }


    @Override
    public Produit addProduit(Produit pr) {
        return produitRepository.save(pr);
    }

    @Override
    public Produit updateProduit(Produit pr) throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getRoles().equals("ROLE_RESTAURANT") && pr.getUser().getId() == user.getId()) {
            produitRepository.save(pr);
        } else {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer ce repas.");
        }
        return pr;
    }

    @Override
    public Produit retrieveProduitById(Long id) {

        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Produit> retrieveAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public void deleteProduit(Produit pr) throws AccessDeniedException {
       User user = getCurrentUserObjects();

        if (user.getRoles().equals("ROLE_FOURNISSEUR") && pr.getUser().getId() == user.getId()) {
            produitRepository.delete(pr);
        } else {
            throw new AccessDeniedException("Vous n'êtes pas autorisé à supprimer ce repas.");
        }
    }


    @Override
    public Set<Produit> getProduitByUserId() {
        User user = getCurrentUserObjects();

        if (user.getRoles().equals("ROLE_FOURNISSEUR")) {
            return produitRepository.findByUserId(user.getId());
        }

        return produitRepository.findByUserId(user.getId());
    }

    @Override
    public void updateProduitBloqueStatus() {
        produitRepository.blockProduitWithTooManyReclamations();
    }


}
