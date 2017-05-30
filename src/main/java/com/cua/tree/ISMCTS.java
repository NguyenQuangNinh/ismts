package com.cua.tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Conduct an ISMCTS search for itermax iterations starting from rootstate.
 * Return the best move from the rootstate.
 * Created by nguyenquangninh on 5/29/17.
 */
public class ISMCTS<M extends Move> {
    public static final double C = Math.sqrt(2);
    private Random randomGenerator = new Random();

    public M search(GameState rootstate, int itermax, boolean verbose) {
        Node rootNode = new Node(null, null, -1);
        for(int i = 0; i < itermax; i++) {
            Node node = rootNode;

            // Determinize
            GameState state = rootstate.cloneAndRandomize(rootstate.getPlayerToMove());

            // Select
            while(!state.isTerminal() && node.getUntriedMoves(state.GetMoves()).isEmpty()) { //node is fully expanded and non-terminal
                node = node.UCBSelectChild(state.GetMoves(), rootstate.getPlayerToMove());
                state.doMove(node.getMove());
            }

            // Expand
            ArrayList<M> untriedMoves = node.getUntriedMoves(state.GetMoves());
            if(!untriedMoves.isEmpty()){ // if we can expand (i.e. state/node is non-terminal)
                M m = randomChoice(untriedMoves);
                int player = state.getPlayerToMove();
                state.doMove(m);
                node = node.addChild(m, player);
            }

            // Simulate
            while(!state.isTerminal())
            {
                state.doMove(randomChoice((ArrayList<M>) state.GetMoves()));
            }

            // Backpropagate
            while (node != null) // backpropagate from the expanded node and work back to the root node
            {
                node.Update(state);
                node = node.getParent();
            }
        }

        // Output some information about the tree - can be omitted
        if (verbose)
        {
            System.out.print(rootNode.treeToString(0));
        } else {
            System.out.print(rootNode.ChildrenToString());
        }

        Node bestChild = rootNode.getBestChild();
        if(bestChild != null) {
            return (M) bestChild.getMove();
        }
        else {
           System.err.println("Something wrong happend");
           return null;
        }
    }

    private M randomChoice(ArrayList<M> moves) {
        return moves.get(randomGenerator.nextInt(moves.size()));
    }
}
