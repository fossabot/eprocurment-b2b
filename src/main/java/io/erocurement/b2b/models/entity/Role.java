package io.erocurement.b2b.models.entity;

import lombok.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
@Table(name = "roles")
public class Role extends  AuditModel   implements Serializable {




    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String name;




}
