package com.neuber.souza.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "dolar")
public class Dolar extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "data_criacao")
    public LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    public LocalDateTime dataAtualizacao;

    @Column(name = "data_cotacao")
    public LocalDateTime dataCotacao;

    @Column(name = "valor_compra")
    public BigDecimal valorCompra;

    @Column(name = "valor_venda")
    public BigDecimal valorVenda;

    @Column(name = "data_hora_cotacao")
    public LocalDateTime dataHoraCotacao;

}
