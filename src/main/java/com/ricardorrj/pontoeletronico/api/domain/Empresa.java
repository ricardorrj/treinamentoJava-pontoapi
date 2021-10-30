package com.ricardorrj.pontoeletronico.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPRESA")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Campo Razão Social é obrigatório")
    private String razaoSocial;

    @Column(nullable = false)
    @NotBlank(message = "Campo CPF é obrigatório")
    @CNPJ(message = "Cnpj deve ser Válido!")
    private String cnpj;

    @Column(name = "data_cadastro", nullable = false)
    private Date datacriacao;

    @Column(name = "data_alteracao", nullable = false)
    private Date dataAtualizacao;


    //Relacionamento
    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<Funcionario> funcionarios = new ArrayList<>();


    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }

    @PrePersist
    public void prePersist(){
        final Date atual = new Date();
        datacriacao = atual;
        dataAtualizacao = atual;
    }

    //@PrePersist e @PreUpdate permitem executar uma ação antes de uma inserção ou atualização de um registro.
    //No caso, recebe a data atual nos campos.
}
