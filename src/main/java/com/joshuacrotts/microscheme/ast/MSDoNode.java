/******************************************************************************
 *  File: MSDoNode.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  MSDoNodes are iterative statements in MicroScheme. They take the form
 *
 *  (do ((<var> <expr> <step-expr>)*) (<test> <when-true-expr>) <seq>)
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

import java.util.ArrayList;

public final class MSDoNode extends MSSyntaxTree {

    /**
     * Number of declarations inside this Do Node.
     */
    private final int NUM_DECLARATIONS;

    /**
     * Number of set expressions. A set expression is a "next" step.
     */
    private final int NUM_SET_EXPRESSIONS;

    /**
     * Number of "true" expressions to evaluate once the loop terminates.
     */
    private final int NUM_TRUE_EXPRESSIONS;

    public MSDoNode(final ArrayList<MSSyntaxTree> doDeclarations, final ArrayList<MSSyntaxTree> doSetExpressions,
                    final MSSyntaxTree doTest, final ArrayList<MSSyntaxTree> doTrueExpressions, final MSSyntaxTree doBody) {
        super(MSNodeType.DO);
        this.NUM_DECLARATIONS = doDeclarations.size();
        this.NUM_SET_EXPRESSIONS = doSetExpressions.size();
        this.NUM_TRUE_EXPRESSIONS = doTrueExpressions.size();

        doDeclarations.forEach(this::addChild);
        doSetExpressions.forEach(this::addChild);
        this.addChild(doTest);
        doTrueExpressions.forEach(this::addChild);
        this.addChild(doBody);
    }

    @Override
    public String getStringRep() {
        return this.getNodeType().toString();
    }

    @Override
    public String toString() {
        return this.getNodeType().toString();
    }

    public ArrayList<MSSyntaxTree> getDoDeclarations() {
        ArrayList<MSSyntaxTree> doDeclarations = new ArrayList<>();
        for (int i = 0; i < this.NUM_DECLARATIONS; i++) {
            doDeclarations.add(this.getChild(i));
        }
        return doDeclarations;
    }

    public ArrayList<MSSyntaxTree> getDoSetExpressions() {
        ArrayList<MSSyntaxTree> doSetExpressions = new ArrayList<>();
        for (int i = 0; i < this.NUM_SET_EXPRESSIONS; i++) {
            doSetExpressions.add(this.getChild(i + this.NUM_DECLARATIONS));
        }
        return doSetExpressions;
    }

    public MSSyntaxTree getDoTest() {
        return this.getChild(this.NUM_DECLARATIONS + this.NUM_SET_EXPRESSIONS);
    }

    public ArrayList<MSSyntaxTree> getDoTrueExpressions() {
        ArrayList<MSSyntaxTree> doTrueExpressions = new ArrayList<>();
        for (int i = 0; i < this.NUM_TRUE_EXPRESSIONS; i++) {
            doTrueExpressions.add(this.getChild(i + this.NUM_DECLARATIONS + this.NUM_SET_EXPRESSIONS + 1));
        }
        return doTrueExpressions;
    }

    public MSSyntaxTree getDoBody() {
        return this.getChild(this.getChildrenSize() - 1);
    }
}
