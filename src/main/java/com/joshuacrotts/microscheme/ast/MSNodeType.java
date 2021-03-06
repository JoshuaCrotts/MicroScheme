/******************************************************************************
 *  File: MSNodeType.java
 *
 *  Author: Joshua Crotts
 *
 *  Last Updated: 05/22/2022
 *
 *  Each MSSyntaxTree has a node type and this file defines each.
 *
 ******************************************************************************/

package com.joshuacrotts.microscheme.ast;

public enum MSNodeType {

    ROOT("root"),
    SEQUENCE("sequence"),
    NUMBER("number"),
    STRING("string"),
    BOOLEAN("boolean"),
    CHARACTER("char"),
    SYMBOL("symbol"),
    QUASISYMBOL("quasisymbol"),
    AND("and"),
    OR("or"),
    LIST("list"),
    VECTOR("vector"),
    VARIABLE("variable"),
    COND("cond"),
    LAMBDA("lambda"),
    LETREC("letrec"),
    SET("set!"),
    SETCAR("set-car!"),
    SETCDR("set-cdr!"),
    SETVECTOR("vector-set!"),
    DO("do"),
    DECLARATION("declaration"),
    APPLICATION("application"),
    APPLY("apply"),
    EVAL("eval");
        
    private final String STRING_REP;
    
    MSNodeType(final String stringRep) {
        this.STRING_REP = stringRep;
    }
    
    @Override
    public String toString() {
        return this.STRING_REP;
    }
}
