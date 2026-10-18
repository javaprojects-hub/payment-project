package com.axc.solidprinciples.constant;

public enum TransactionStatus {

    CREATED("Transaction is created"),
    FAILED("transaction is failed due to some internal issues"),
    SUCCESS("transaction is success"),
    IN_PROGESS("transaction is in progress"),
    PENDING("transaction is in pending state");

  private final String discription; 

  TransactionStatus(String discription) { 

     this.discription = discription; 

    }

    public String getDiscription() {

        return discription;
    }
}
