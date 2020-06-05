package pl.ice.GameMasterHelper.model;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    private String description;

    private Long value;
}
