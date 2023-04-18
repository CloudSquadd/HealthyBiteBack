package com.esprit.pidev.repository;

import com.esprit.pidev.entities.Ingredient;
import com.esprit.pidev.entities.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ObjectifRepository extends JpaRepository<Objectif, Long> {

    @Query(value = "" +
            "SELECT * " +
            "FROM Objectif " +
            "WHERE " +
            "   poidDepart BETWEEN :poidDepart and :poidDepartTolerated " +
            "AND " +
            "   objectifPoid BETWEEN  objectifPoid  AND objectifPoidTolerated"
            , nativeQuery = true)
    public List<Objectif> findByPoidDeDepardWithTolerance(
            @Param("poidDepart") Long poidDepart,
            @Param("poidDepartTolerated") Long poidDepartTolerated,
            @Param("objectifPoid") Long objectifPoid,
            @Param("objectifPoidTolerated") Long objectifPoidTolerated

    );


}
