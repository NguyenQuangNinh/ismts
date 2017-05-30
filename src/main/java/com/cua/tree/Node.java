package com.cua.tree;

import java.util.ArrayList;

/**
 * Created by nguyenquangninh on 5/27/17.
 */
public class Node<M extends Move> {
    private int playerJustMoved = -1;
    private int avails;
    private int visits;
    private double wins;
    private ArrayList<Node> childNodes;
    private M move;
    private Node parent;

    public Node(M move, Node parent, int playerJustMoved) {
        this.move = move; // the move that got us to this node - "None" for the root node
        this.parent = parent; // "None" for the root node
        this.childNodes = new ArrayList<>();
        this.wins = 0;
        this.visits = 0;
        this.avails = 1;
        this.playerJustMoved = playerJustMoved; // the only part of the state that the Node needs later
    }

    public ArrayList<M> getUntriedMoves(ArrayList<M> legalMoves)
    {
        ArrayList<M> result = new ArrayList<>(legalMoves);

        // Find all moves for which this node *does* have children
        ArrayList<M> triedMoves = new ArrayList<>();
        for(Node child : this.childNodes) {
           triedMoves.add((M) child.move);
        }

        // Return all moves that are legal but have not been tried yet
        result.removeAll(triedMoves);
        return result;
    }

    /**
     * Use the UCB1 formula to select a child node, filtered by the given list of legal moves
     * @param legalMoves all possible moves of the current game state
     * @param currentPlayer
     * @return
     */
    public Node UCBSelectChild(ArrayList<M> legalMoves, int currentPlayer) {

        // Filter the list of children by the list of legal moves
        ArrayList<Node> legalChildren = new ArrayList<>();
        for(Node child : childNodes) {
            if(legalMoves.contains(child.move))
            {
                legalChildren.add(child);
            }
        }

        // Get the child with the highest UCB score
        boolean isOpponentTurn = currentPlayer == this.playerJustMoved;
        double bestScore = Double.NEGATIVE_INFINITY;
        double score = bestScore;
        Node result = null;
        for(Node child : legalChildren) {
            score = getUCBScore(child);
            if(bestScore < score) {
                bestScore = score;
                result = child;
            }

            // Update availability counts -- it is easier to do this now than during backpropagation
            child.avails++;
        }

        return result;
    }

    public double getUCBScore(Node node) {
        // exploration is a constant balancing between exploitation and exploration, with default value 0.7 (approximately sqrt(2) / 2)
        double exploration = 0.7;
        return Double.valueOf(node.wins)/Double.valueOf(node.visits) + ISMCTS.C * Math.sqrt(Math.log(node.avails)/Double.valueOf(node.visits));
    }

    /**
     * Add a new child node for the move m.
     * @param m
     * @param p
     */
    public Node addChild(M m, int p) {
        Node n = new Node(m, this, p);
        this.childNodes.add(n);
        return n;
    }

    /**
     * Update this node - increment the visit count by one, and increase the win count by the result of terminalState for self..playerJustMoved.
     * @param terminalState
     */
    public void Update(GameState terminalState){
        this.visits++;
        if(this.playerJustMoved != -1)
        {
            this.wins += terminalState.getResult(this.playerJustMoved);
        }
    }

    @Override
    public String toString() {
        return String.format("[M:%s W/V/A: %f/%d/%d]", this.move, this.wins, this.visits, this.avails);
    }

    /**
     * Represent the tree as a string, for debugging purposes.
     * @param indent
     * @return
     */
    public String treeToString(int indent){
        String s = this.IndentString(indent) + this.toString();
        for(Node c : childNodes) {
            s += c.treeToString(indent +1);
        }
        return s;
    }

    public String IndentString(int indent){
        String s = "\n";
        for(int i = 1; i < indent + 1; i++) {
            s += "| ";
        }

        return s;
    }

    public String ChildrenToString() {
        String s = "";
        for(Node c : childNodes) {
            s += c.toString() + "\n";
        }
        return s;
    }

    public Node getBestChild() {
        double max = -9999.0;
        Node result = null;
        for(Node node : childNodes) {
           if(max < node.getWinRate())
           {
               max = node.getWinRate();
               result = node;
           }
        }

        return result;
    }

    public M getMove() {
        return move;
    }

    public Node getParent() {
        return parent;
    }

    public double getWinRate() {
        return Double.valueOf(this.wins) / Double.valueOf(this.visits);
    }
}
