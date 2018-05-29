package controller;

public class CannotFindException extends Exception {

		public CannotFindException(Exception e, String explanation) {
			super(explanation, e);
		}
		
		public CannotFindException(String explanation) {
			super(explanation);
		}
}


