package expressionParser;

public class TestRunner {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		/*
		 * ��������� -> ��������� [+ ���������] [- ���������]
		   ��������� -> ��������� [* ���������] [/ ���������]
		   ��������� -> ����������, ����� ��� (���������)
		 */
		
		ExpressionParser p = new ExpressionParser();

		String test = "10+2*(sin(30)+3)";
		System.out.println("Result is: " + p.parse(test));

	}

}
