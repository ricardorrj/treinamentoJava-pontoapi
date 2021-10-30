package com.ricardorrj.pontoeletronico.api.domain;

import com.ricardorrj.pontoeletronico.api.domain.enums.PerfilEnum;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "FUNCIONARIO")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="genfuncionario_id")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @CPF(message = "CPF deve ser válido!")
    private String cpf;

    @Column(nullable = false)
    private BigDecimal valorHora;

    @Column(nullable = false)
    private Float horasTrabalhadas;

    @Column(nullable = false)
    private Float horasAlmoco;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @Column(name = "data_cadastro", nullable = false)
    private Date datacriacao;

    @Column(name = "data_alteracao", nullable = false)
    private Date dataAtualizacao;

    //Relacionamento
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @OneToMany(mappedBy = "funcionario")
    private List<Lancamento> lancamentos = new ArrayList<>();


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


    //Permite enumerar com a String ao invés de 0,1,2
    //@Enumerated(EnumType.STRING)
}
