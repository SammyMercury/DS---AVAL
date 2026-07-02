package AVAL_SamBrum_GabPinheiro.Trabalho_DS_2026.exceptions;

public class DatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DatabaseException(String message) {
        super(message);
    }
}