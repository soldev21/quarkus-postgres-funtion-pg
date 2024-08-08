//package com.example;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.experimental.SuperBuilder;
//import org.hibernate.annotations.JavaType;
//import org.hibernate.annotations.UuidGenerator;
//
//import java.math.BigDecimal;
//
//
//@Data
//@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(callSuper = true)
//@Entity(name = "Transaction")
//@Table(name = "trx_transaction")
//public class TransactionEntity {
//
//    @Id
//    @GeneratedValue
//    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
//    @Column(name = "id", updatable = false, nullable = false)
////    @Type(type = "io.megafair.jinfra.foundation.entity.uuid.CustomPostgresUUIDType")
////    @Type(type=CustomPostgresUUIDType.TYPE_NAME)
////    @Type(CustomPostgresUUIDType.class)
//    @JavaType(UUIDAsStringJavaType.class)
//    protected String id;
//
//    public static final String TRX_NUMERIC_ID_SEQ = "trx_numeric_id_seq";
//
//    @Column(name = "numeric_id", updatable = false)
//    private Long numericId;
//
//    @Column(name = "amount", nullable = false, updatable = false)
//    private BigDecimal amount;
//
//    @Column(name = "rounded_amount", nullable = false, updatable = false)
//    private BigDecimal roundedAmount;
//
//
//    @Column(name = "baseline_amount", nullable = false, updatable = false)
//    private BigDecimal baselineAmount;
//
//    @Column(name = "new_cash_balance")
//    private BigDecimal newCashBalance;
//
//    @Column(name = "game_id", nullable = false, updatable = false)
//    private String gameId;
//
//    @Column(name = "win_granter", nullable = true, updatable = false)
//    private String winGranter;
//}
