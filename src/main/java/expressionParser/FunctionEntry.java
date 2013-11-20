package expressionParser;

public class FunctionEntry {

	public Node determineFunction(String function, Node node) {
		if (function.equals("sin")) {
			return new Node(Math.sin(Math.toRadians(node.currentState)),
					node.restOfExpression);

		} else if (function.equals("cos")) {
			return new Node(Math.cos(Math.toRadians(node.currentState)),
					node.restOfExpression);

		} else if (function.equals("tan")) {
			return new Node(Math.tan(Math.toRadians(node.currentState)),
					node.restOfExpression);

		} else if (function.equals("arctan")) {
			return new Node(Math.atan(Math.toRadians(node.currentState)),
					node.restOfExpression);

		} else if (function.equals("log")) {
			return new Node(Math.log10(node.currentState),
					node.restOfExpression);

		} else if (function.equals("ln")) {
			return new Node(Math.log(node.currentState), node.restOfExpression);

		} else if (function.equals("sqrt")) {
			return new Node(Math.sqrt(node.currentState), node.restOfExpression);

		} else {
			System.err.println("unknown function");

		}

		return node;
	}

}
