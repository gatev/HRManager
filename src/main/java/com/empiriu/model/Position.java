package com.empiriu.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "position")
public class Position extends BaseEntity{
    @NotBlank
    @Size(max = 40)
    private String name;
}
