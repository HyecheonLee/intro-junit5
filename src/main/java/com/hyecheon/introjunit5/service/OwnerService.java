package com.hyecheon.introjunit5.service;

import com.hyecheon.introjunit5.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
