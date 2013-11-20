package expressionParser.parserProcedures;

import expressionParser.Node;

public class NumberParser {

	public Node findNumber(String expression) throws Exception {
		int i = 0;
		int dot_cnt = 0;
		boolean negative = false;

		if (expression.charAt(0) == '-') {
			negative = true;
			expression = expression.substring(1);
		}

		while (i < expression.length()
				&& (Character.isDigit(expression.charAt(i)) || expression
						.charAt(i) == '.')) {

			if (expression.charAt(i) == '.' && ++dot_cnt > 1) {
				throw new Exception("not valid number '" + expression.substring(0, i + 1) + "'");
			}
			i++;
		}
		if (i == 0) {
			throw new Exception("can't get valid number in '" + expression + "'");
		}

		double dPart = Double.parseDouble(expression.substring(0, i));
		if (negative)
			dPart = -dPart;
		String restPart = expression.substring(i);

		return new Node(dPart, restPart);
	}

}
