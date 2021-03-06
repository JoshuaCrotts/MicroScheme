/******************************************************************************
 *  File: MSListNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  A list is either a cons pair or quoted parentheses, e.g., (cons 1 2) = (1 . 2)
 *  e.g., '(1 2 3) = (1 2 3) = (1 . (2 . (3 . ()))). A list that does not end with
 *  the empty list is improper and contains dots when printed.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import com.joshuacrotts.microscheme.main.LValue;

import java.util.ArrayList;

public final class MSListNode extends MSSyntaxTree {

    /**
     * Creates a global "empty list" to use. These can, realistically,
     * all be the same reference.
     */
    public static final MSListNode EMPTY_LIST = new MSListNode();

    public MSListNode(final MSSyntaxTree car, final MSSyntaxTree cdr) {
        super(MSNodeType.LIST);
        if (car != null) { this.addChild(car); }
        if (cdr != null) { this.addChild(cdr); }
    }

    /**
     * Constructs a MSListNode with a list of nodes. Each element is the cdr of
     * the preceding element.
     * @param nodes
     */
    public MSListNode(final ArrayList<LValue> nodes) {
        super(MSNodeType.LIST);
        ArrayList<MSSyntaxTree> syntaxTrees = new ArrayList<>();
        for (LValue lval : nodes) { syntaxTrees.add(LValue.getAst(lval)); }
        MSListNode curr = null;
        for (int i = nodes.size() - 1; i > 0; i--) {
            MSSyntaxTree next = syntaxTrees.get(i);
            curr = new MSListNode(next, curr);
        }

        if (!nodes.isEmpty()) { this.addChild(syntaxTrees.get(0)); }
        if (curr != null) { this.addChild(curr); }
    }

    private MSListNode() {
        this(null, null);
    }

    @Override
    public String getStringRep() {
        return this.getListStringRep();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public MSSyntaxTree getCar() {
        return this.getChildrenSize() >= 1 ? this.getChild(0) : MSListNode.EMPTY_LIST;
    }

    public void setCar(final MSSyntaxTree newCar) {
        if (this.getCar() != null) { this.setChild(0, newCar); }
        else { this.addChild(newCar); };
    }

    public MSSyntaxTree getCdr() {
        return this.getChildrenSize() >= 2 ? this.getChild(1) : MSListNode.EMPTY_LIST;
    }

    public void setCdr(final MSSyntaxTree newCdr) {
        if (this.getCdr() != null) { this.setChild(1, newCdr); }
        else { this.addChild(newCdr); }
    }

    public boolean isProper() {
        if (this.isEmptyList()) { return true; }
        else if (this.getCar() != null && this.getChildrenSize() == 1) { return true; }
        else if (this.getChildrenSize() == 2 && this.getCdr().isList()) { return ((MSListNode) this.getCdr()).isProper(); }
        return false;
    }

    public boolean isEmptyList() {
        return this.getChildrenSize() == 0 || this == MSListNode.EMPTY_LIST;
    }

    public ArrayList<MSSyntaxTree> getListAsArrayList() {
        ArrayList<MSSyntaxTree> elementsList = new ArrayList<>();
        MSListNode curr = this;
        while (!curr.isEmptyList()) {
            elementsList.add(curr.getCar());
            curr = (MSListNode) curr.getCdr();
        }
        return elementsList;
    }

    /**
     *
     * @return
     */
    private String getListStringRep() {
        if (this.getChildrenSize() == 0) { return "()"; }
        // If the list is proper, then we don't print dot notation.
        if (this.isProper()) {
            StringBuilder sb = new StringBuilder("(");
            this.getProperStringRep(this, sb);
            sb.append(")");
            return sb.toString();
        } else {
            // If the tail is null then we print the head with parentheses surrounding.
            if (this.getCdr() == null) { return "(" + this.getCar().getStringRep() + ")"; }
            else {
                return "(" + this.getCar().getStringRep()
                        + (this.isProper() ? " " : " . ")
                        + this.getCdr().getStringRep()
                        + ")";
            }
        }
    }

    /**
     * @param curr
     * @param sb
     */
    private void getProperStringRep(final MSSyntaxTree curr, final StringBuilder sb) {
        MSListNode currList = (MSListNode) curr;
        if (currList.getChildrenSize() == 0) {
            // Trim the last space.
            sb.setLength(sb.length() - 1);
        } else {
            // Append the head then check the cdr.
            sb.append(currList.getCar().getStringRep());
            if (currList.getCdr() != null) {
                sb.append(" ");
                this.getProperStringRep(currList.getCdr(), sb);
            }
        }
    }
}
