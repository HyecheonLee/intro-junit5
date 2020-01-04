package com.hyecheon.introjunit5.repositories;

import com.hyecheon.introjunit5.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}