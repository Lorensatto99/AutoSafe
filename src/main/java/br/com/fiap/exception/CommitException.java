package br.com.fiap.exception;

public class CommitException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommitException() {
		super("Erro ao realizar o commit");
	}
	
	public CommitException(String msg) {
		super(msg);
	}

}
