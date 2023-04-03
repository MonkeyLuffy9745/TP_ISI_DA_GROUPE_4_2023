package TP.SI.tpIntegration.entities;

import java.time.LocalDate;
import java.util.List;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String Nom;
    private String Prenom;
    private String dnaissance;
    private String sexe;
    private String addresse;
    private String telephone;
    private String courriel;
    private String nationalité;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @Nullable
    private List<Compte> comptes;

}
