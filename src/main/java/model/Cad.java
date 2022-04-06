package model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;


import javax.persistence.Entity;

@Entity
public class Cad extends PanacheEntity {
    public String estado;
    public String cidade;
}
