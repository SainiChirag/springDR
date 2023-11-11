package com.chirag.springdr.repository;

import com.chirag.springdr.entity.Beer;
import com.chirag.springdr.model.BeerStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

// below annotation path changes the path of the url, collectonResourceRel changes the name of the collection attribute
// being exposed
@RepositoryRestResource(path = "beer", collectionResourceRel = "beer")
public interface BeerRepository extends JpaRepository<Beer, UUID> {
    Page<Beer> findAllByBeerNameIsLikeIgnoreCase(String beerName, Pageable pageable);
    Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, Pageable pageable);
    Page<Beer> findBeerByBeerNameIsLikeIgnoreCaseAndBeerStyle(String beerName, BeerStyle beerStyle, Pageable pageable);
}