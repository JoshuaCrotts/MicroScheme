package com.joshuacrotts.minischeme.ast;

/**
 *
 *
 * @author Joshua Crotts
 * @version 12/23/2021
 */
public class MSStringNode extends MSSyntaxTree {

    /**
     * String associated with this node.
     */
    private final String value;

    public MSStringNode(String value) {
        super(MSNodeType.STR);
        this.value = value;
    }

    @Override
    public MSSyntaxTree copy() {
        return new MSStringNode(this.value);
    }

    @Override
    public String getStringRep() {
        if (this.value.startsWith("\"")
            && this.value.endsWith("\"")
            && this.value.length() >= 2) {
            return this.value.substring(1, this.value.length() - 1);
        }
        return this.value;
    }

    @Override
    public String toString() {
        return "(STR " + this.value + ")";
    }

    public String getValue() {
        return this.value;
    }
}
