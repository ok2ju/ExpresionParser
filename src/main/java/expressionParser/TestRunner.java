package expressionParser;

public class TestRunner {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		/*
		 * выражение -> слагаемое [+ слагаемое] [- слагаемое]
		   слагаемое -> множитель [* множитель] [/ множитель]
		   множитель -> переменная, число или (выражение)
		 */
		
		ExpressionParser p = new ExpressionParser();

		String test = "10+2*(sin(30)+3)";
		System.out.println("Result is: " + p.parse(test));

	}

}
