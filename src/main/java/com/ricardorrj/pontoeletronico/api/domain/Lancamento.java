package com.ricardorrj.pontoeletronico.api.domain;

import com.ricardorrj.pontoeletronico.api.domain.enums.TipoEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LANCAMENTO")
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="genlancamento_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String localizacao;

    @Column(name = "data_cadastro", nullable = false)
    private Date datacriacao;

    @Column(name = "data_alteracao", nullable = false)
    private Date dataAtualizacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEnum tipo;


    //Relacionamento
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;


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

    //Certifica que vai ser registrado a data e a hora
    //@Temporal(TemporalType.TIMESTAMP)

}
