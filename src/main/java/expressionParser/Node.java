package expressionParser;

public class Node {

	public double currentState;
	public String restOfExpression;

	public Node(double currentState, String restOfExpression) {
		this.currentState = currentState;
		this.restOfExpression = restOfExpression;
	}
	
}
