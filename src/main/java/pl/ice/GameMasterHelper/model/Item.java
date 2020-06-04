package pl.ice.GameMasterHelper.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Long value;
}
