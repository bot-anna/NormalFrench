import Grammar.NormalFrenchBaseListener;
import Grammar.NormalFrenchParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

/*
* TODO:
*    - fixa objekt och arrayer
*    - kika på om du vill kunna printa annat än id
*
*/

public class CompileNormalFrench extends NormalFrenchBaseListener {
    private StringBuilder out = new StringBuilder();
    private Stack<Integer> loopStack = new Stack<Integer>();
    private int loopcounter = 0;
    public String getCompiledCode(){
        return out.toString();
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */

    @Override public void enterInish(NormalFrenchParser.InishContext ctx) {
        out.append("push " + ctx.expression().INT() + "\n");
        out.append("pop " + ctx.declare().ID() + "\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitInish(NormalFrenchParser.InishContext ctx) { }
    /**
     * This method for declaring a variable sets it to a default value of zero.
     */
    @Override public void enterDeclare(NormalFrenchParser.DeclareContext ctx) {
        out.append("push 0 \n");
        out.append("pop " + ctx.ID() + "\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAssign(NormalFrenchParser.AssignContext ctx) {
        if(ctx.expression().INT() != null) {
            out.append("push " + ctx.expression().INT() + "\n");
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAssign(NormalFrenchParser.AssignContext ctx) {
        out.append("pop " + ctx.ID() + "\n");}
    @Override public void enterUnaryexpression(NormalFrenchParser.UnaryexpressionContext ctx) {
        if(ctx.INT() == null) {
            out.append("push " + ctx.ID() + "\n");
        }
        else {
            out.append("push " + ctx.INT() + "\n");
        }
    }

    @Override public void exitAdd(NormalFrenchParser.AddContext ctx) {
        out.append("add\n");
    }

    @Override public void exitSub(NormalFrenchParser.SubContext ctx) {
        out.append("sub\n");
    }
    @Override public void exitIncrement(NormalFrenchParser.IncrementContext ctx) {
        out.append("push 1" + "\n");
        out.append("add" + "\n");
        out.append("pop " + ctx.unaryexpression().ID() + "\n"); }
    @Override public void exitDecrement(NormalFrenchParser.DecrementContext ctx) {
        out.append("push 1" + "\n");
        out.append("sub" + "\n");
        out.append("pop " + ctx.unaryexpression().ID());}
    @Override public void exitCondition(NormalFrenchParser.ConditionContext ctx) {
        String operand = null;
        switch (ctx.conditionvariable().getText()) {
            case "moncherie":
                operand = "gt";
                break;
            case "voulevouz":
                operand = "lt";
                break;
            case "fromage":
                operand = "eq";
                break;
        }
        out.append(operand + "\n");
    }
    @Override public void exitPrint(NormalFrenchParser.PrintContext ctx) {
        out.append("print " + ctx.ID() + "\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterLoop(NormalFrenchParser.LoopContext ctx) {
        out.append("label enterLoop" + loopcounter  + "\n");
        loopStack.push(loopcounter);
        loopcounter++;
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitLoop(NormalFrenchParser.LoopContext ctx) {
        out.append("label exitLoop" + loopStack.pop() + "\n");
    }

    @Override public void enterEnterConditionedSegment(NormalFrenchParser.EnterConditionedSegmentContext ctx) {
        out.append("not\n");
        out.append("if-goto exitLoop" + loopStack.peek() + "\n");

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitTerminal(TerminalNode node) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitErrorNode(ErrorNode node) { }
}
