package br.com.zup.MercadoLivre.transaction;

import br.com.zup.MercadoLivre.exception.GenericException;

public enum TransactionStatus {
    ERRO,
    SUCESSO;

    public static TransactionStatus verifyTransactionStatus(String status) {
        try {
            if(status.equals("0"))
                return TransactionStatus.ERRO;

            else if(status.equals("1"))
                return TransactionStatus.SUCESSO;

            return TransactionStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new GenericException("status", "Estado da transação não existente!");
        }
    }
}
