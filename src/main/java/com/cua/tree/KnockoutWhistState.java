package com.cua.tree;

import com.cua.tree.tictactoe.TicTacToeMove;
import com.sun.tools.javac.util.Pair;

import java.util.*;

/**
 * Created by nguyenquangninh on 5/27/17.
 */
public class KnockoutWhistState {
//    private static final String[] SUITS = {"♣", "♦", "♥", "♠"};
//
//    private int tricksInRound;
//    private HashMap<Integer, ArrayList<Move>> playerHands;
//    private HashMap<Integer, Integer> tricksTaken;
//
//
//    private HashMap<Integer, Boolean> knockedOut;
//    private ArrayList<Move> discards;
//    private ArrayList<Pair<Integer, Move>> currentTrick;
//    private String trumpSuit;
//
//    public KnockoutWhistState(int numberOfPlayers, int playerToMove) {
//        super(numberOfPlayers, playerToMove);
//
//        this.tricksInRound  = 7;
//        this.playerHands = new HashMap<>();
//
//
//        discards = new ArrayList<>();
//        currentTrick = new ArrayList<>();
//        trumpSuit      = null;
//
//        tricksTaken = new HashMap<>();
//        knockedOut = new HashMap<>();
//
//        for(int p = 1; p < numberOfPlayers + 1; p++) {
//            playerHands.put(p, new ArrayList<>());
//            knockedOut.put(p, false);
//        }
//
//        deal();
//    }
//
//    @Override
//    public GameState clone() {
//        KnockoutWhistState st = new KnockoutWhistState(this.getNumberOfPlayers(), this.getPlayerToMove());
//        st.setTricksInRound(this.getTricksInRound());
//        st.setPlayerHands(new HashMap<>(this.getPlayerHands()));
//        st.setDiscards(new ArrayList<>(this.getDiscards()));
//        st.setCurrentTrick(new ArrayList<>(this.getCurrentTrick()));
//        st.setTrumpSuit(this.getTrumpSuit());
//        st.setTricksTaken(new HashMap<>(this.getTricksTaken()));
//        st.setKnockedOut(new HashMap<>(this.getKnockedOut()));
//        return st;
//    }
//
//    /**
//     * Return the player to the left of the specified player, skipping players who have been knocked out
//     * @param p current player
//     * @return
//     */
//    @Override
//    public int getNextPlayer(int p) {
//        int next = (p % this.getNumberOfPlayers()) + 1;
//		// Skip any knocked-out players
//        while (next != p && this.knockedOut.get(next)) {
//            next = (next % this.getNumberOfPlayers()) + 1;
//        }
//
//        return next;
//    }
//
//    @Override
//    public ArrayList<TicTacToeMove> GetMoves() {
//        return null;
//    }
//
//    /**
//     * Update a state by carrying out the given move.
//     * Must update playerToMove.
//     * @param move
//     */
//    @Override
//    public void doMove(Move move) {
//        // Store the played card in the current trick
//        this.currentTrick.add(new Pair<>(this.getPlayerToMove(), move));
//
//		// Remove the card from the player's hand
//        this.getHand(this.getPlayerToMove()).remove(move);
//
//		// Find the next player
//        this.setPlayerToMove(this.getNextPlayer(this.getPlayerToMove()));
//
//        // If the next player has already played in this trick, then the trick is over
//        for(Pair<Integer, Move> p : currentTrick){
//            Integer player = p.fst;
//            Move card = p.snd;
//            if(player == this.getPlayerToMove())
//            {
//                Integer leader = currentTrick.get(0).fst;
//                Move leadCard = currentTrick.get(0).snd;
//
//            }
//        }
//    }
//
//    @Override
//    public int getResult(int player) {
//        return 0;
//    }
//
//    @Override
//    public boolean isTerminal() {
//        return true;
//    }
//
//    /**
//     * Create a deep clone of this game state, randomizing any information not visible to the specified observer player.
//     * @return
//     */
//    @Override
//    public GameState cloneAndRandomize(int observer) {
//        KnockoutWhistState st = (KnockoutWhistState)this.clone();
//
//        // The observer can see his own hand and the cards in the current trick, and can remember the cards played in previous tricks
//        ArrayList<Move> seenCards = new ArrayList<>();
//        seenCards.addAll(st.getHand(observer));
//        seenCards.addAll(st.getDiscards());
//        for(Pair<Integer,Move> p : st.getCurrentTrick())
//        {
//            seenCards.add(p.snd);
//        }
//
//        ArrayList<Move> unseenCards = getCardDeck();
//        unseenCards.removeAll(seenCards);
//
//		// Deal the unseen cards to the other players
//        Collections.shuffle(unseenCards);
//        for(int p = 1; p < st.getNumberOfPlayers() + 1; p++) {
//            if(p != observer) {
//                // Deal cards to player p
//				// Store the size of player p's hand
//                int numCards = this.getHand(p).size();
//                List<Move> dealCards = unseenCards.subList(0, numCards);
//                st.getPlayerHands().put(p, new ArrayList<>(dealCards));
//                dealCards.clear();
//            }
//        }
//
//        return st;
//    }
//
//    public ArrayList<Move> getHand(int player) {
//        return this.getPlayerHands().get(player);
//    }
//
//    /**
//     * Reset the game state for the beginning of a new round, and deal the cards.
//     */
//    public void deal() {
//        discards = new ArrayList<>();
//        currentTrick = new ArrayList<>();
//        tricksTaken = new HashMap<>();
//        for(int p = 1; p < this.getNumberOfPlayers() + 1; p++) {
//            tricksTaken.put(p, 0);
//        }
//
//		// Construct a deck, shuffle it, and deal it to the players
//        ArrayList<Move> deck = this.getCardDeck();
//        Collections.shuffle(deck);
//
//        for(int p = 1; p < this.getNumberOfPlayers() + 1; p++) {
//            List<Move> dealCards = deck.subList(0, tricksInRound);
//            this.getPlayerHands().put(p, new ArrayList<>(dealCards));
//            dealCards.clear();
//        }
//
//		// Choose the trump suit for this round
//        trumpSuit = SUITS[(new Random()).nextInt(SUITS.length)];
//    }
//
//    public ArrayList<Move> getCardDeck() {
//        ArrayList<Move> deck = new ArrayList<>();
//        try {
//            for(int i = 2; i < 15; i++) {
//                for(int j = 0; j < SUITS.length; j++) {
//                    deck.add(new Move(i, SUITS[j]));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return deck;
//    }
//
//    /*
//    *   GETTER SETTER
//    * */
//    public int getTricksInRound() {
//        return tricksInRound;
//    }
//
//    public void setTricksInRound(int tricksInRound) {
//        this.tricksInRound = tricksInRound;
//    }
//
//    public HashMap<Integer, ArrayList<Move>> getPlayerHands() {
//        return playerHands;
//    }
//
//    public void setPlayerHands(HashMap<Integer, ArrayList<Move>> playerHands) {
//        this.playerHands = playerHands;
//    }
//
//    public HashMap<Integer, Integer> getTricksTaken() {
//        return tricksTaken;
//    }
//
//    public void setTricksTaken(HashMap<Integer, Integer> tricksTaken) {
//        this.tricksTaken = tricksTaken;
//    }
//
//    public HashMap<Integer, Boolean> getKnockedOut() {
//        return knockedOut;
//    }
//
//    public void setKnockedOut(HashMap<Integer, Boolean> knockedOut) {
//        this.knockedOut = knockedOut;
//    }
//
//    public ArrayList<Move> getDiscards() {
//        return discards;
//    }
//
//    public void setDiscards(ArrayList<Move> discards) {
//        this.discards = discards;
//    }
//
//    public ArrayList<Pair<Integer, Move>> getCurrentTrick() {
//        return currentTrick;
//    }
//
//    public void setCurrentTrick(ArrayList<Pair<Integer,Move>> currentTrick) {
//        this.currentTrick = currentTrick;
//    }
//
//    public String getTrumpSuit() {
//        return trumpSuit;
//    }
//
//    public void setTrumpSuit(String trumpSuit) {
//        this.trumpSuit = trumpSuit;
//    }
}
