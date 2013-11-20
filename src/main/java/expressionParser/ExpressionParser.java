package expressionParser;

import expressionParser.parserProcedures.NumberParser;

public class ExpressionParser {

	private FunctionEntry functionEntry;
	private NumberParser numberParser;

	public ExpressionParser() {
		this.functionEntry = new FunctionEntry();
		this.numberParser = new NumberParser();
	}

	public double parse(String expression) throws Exception {
		Node node = plusMinusLevel(expression);
		if (!node.restOfExpression.isEmpty()) {
			System.err.println("Cant parsing a full expression");
		}
		return node.currentState;
	}

	private Node plusMinusLevel(String expression) throws Exception {
		Node currentNode = mulDivLevel(expression);
		double currentState = currentNode.currentState;

		while (currentNode.restOfExpression.length() > 0) {
			if (!(currentNode.restOfExpression.charAt(0) == '+' || currentNode.restOfExpression.charAt(0) == '-'))
				break;

			char sign = currentNode.restOfExpression.charAt(0);
			String next = currentNode.restOfExpression.substring(1);

			currentNode = mulDivLevel(next);
			if (sign == '+') {
				currentState += currentNode.currentState;
			} else {
				currentState -= currentNode.currentState;
			}
		}
		return new Node(currentState, currentNode.restOfExpression);
	}
	
	private Node mulDivLevel(String expression) throws Exception {
		Node currentNode = findBracket(expression);

		double currentState = currentNode.currentState;
		while (true) {
			if (currentNode.restOfExpression.length() == 0) {
				return currentNode;
			}
			char sign = currentNode.restOfExpression.charAt(0);
			if ((sign != '*' && sign != '/'))
				return currentNode;

			String next = currentNode.restOfExpression.substring(1);
			Node right = findBracket(next);

			if (sign == '*') {
				currentState *= right.currentState;
			} else {
				currentState /= right.currentState;
			}

			currentNode = new Node(currentState, right.restOfExpression);
		}
	}
	
	private Node findBracket(String expression) throws Exception {
		char firstSymbol = expression.charAt(0);
		if (firstSymbol == '(') {
			Node node = plusMinusLevel(expression.substring(1));
			if (!node.restOfExpression.isEmpty() && node.restOfExpression.charAt(0) == ')') {
				node.restOfExpression = node.restOfExpression.substring(1);
			} else {
				System.err.println("Cant find closer bracket");
			}
			return node;
		}
		return findFunctionOrValue(expression);
	}
	
	private Node findFunctionOrValue(String expression) throws Exception {
		String tempString = "";
		int i = 0;

		while (i < expression.length() && (Character.isLetter(expression.charAt(i)) || (Character.isDigit(expression.charAt(i)) && i > 0))) {
			tempString += expression.charAt(i);
			i++;
		}
		if (!tempString.isEmpty()) {
			if (expression.length() > i && expression.charAt(i) == '(') {
				Node node = findBracket(expression.substring(tempString.length()));
				return functionEntry.determineFunction(tempString, node);
			}
		}
		return numberParser.findNumber(expression);
	}
	
}
