package br.com.personal.expenses.personalexpenses.domain.service;

import java.util.List;

// interface genérica que define os métodos que um crud deve ter
public interface CRUDService<Req, Res> {

    // todo CRUD vai ter um método obter todos
    List<Res> getAll();

    Res getById(Long id);

    Res register(Req dto);

    Res updateById(Long id,Req dto);

    void deleteById(Long id);
}
