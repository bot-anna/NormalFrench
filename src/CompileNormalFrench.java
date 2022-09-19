import Grammar.NormalFrenchBaseListener;
import Grammar.NormalFrenchParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Stack;

/*
* TODO:
*    -implementera loop-stacken
*    - fixa objekt och arrayer
*    - kika på om du vill kunna printa annat än id
*
*/

public class CompileNormalFrench extends NormalFrenchBaseListener {
    private StringBuilder out = new StringBuilder();
    private Stack<Integer> loopStack = new Stack<Integer>();
    public String getCompiledCode(){
        return out.toString();
    }

    //TODO lägg till stack för loops
    private int loopcounter = 0; //todo fråga johan om jag får göra såhär

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterFile(NormalFrenchParser.FileContext ctx) {
        //out.append("Entering program.\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitFile(NormalFrenchParser.FileContext ctx) {
        //out.append("Exiting program\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterCode(NormalFrenchParser.CodeContext ctx) {
       // out.append("Entering code \n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitCode(NormalFrenchParser.CodeContext ctx) {
        //out.append("Exiting code\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterStatement(NormalFrenchParser.StatementContext ctx) {
        //out.append("Entering statement\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitStatement(NormalFrenchParser.StatementContext ctx) {
        //out.append("Exiting statement\n");
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
    @Override public void exitDeclare(NormalFrenchParser.DeclareContext ctx) { }
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
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterExpression(NormalFrenchParser.ExpressionContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitExpression(NormalFrenchParser.ExpressionContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterUnaryexpression(NormalFrenchParser.UnaryexpressionContext ctx) {
        if(ctx.INT() == null) {
            out.append("push " + ctx.ID() + "\n");
        }
        else {
            out.append("push " + ctx.INT() + "\n");
        }
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitUnaryexpression(NormalFrenchParser.UnaryexpressionContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterCrement(NormalFrenchParser.CrementContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitCrement(NormalFrenchParser.CrementContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterAdd(NormalFrenchParser.AddContext ctx) {
     //   out.append("push " + ctx.getChild(0)); //Todo
       // out.append("push " + ctx.unaryexpression(1) + "\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAdd(NormalFrenchParser.AddContext ctx) {
        out.append("add\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterSub(NormalFrenchParser.SubContext ctx) {

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitSub(NormalFrenchParser.SubContext ctx) {
        out.append("sub\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterIncrement(NormalFrenchParser.IncrementContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitIncrement(NormalFrenchParser.IncrementContext ctx) {
        out.append("push 1" + "\n");
        out.append("add" + "\n");
        out.append("pop " + ctx.unaryexpression().ID() + "\n"); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterDecrement(NormalFrenchParser.DecrementContext ctx) {
        out.append("push " + ctx.unaryexpression().INT() + "\n");
        out.append("push 1" + "\n");
        out.append("sub" + "\n");
        out.append("pop " + ctx.unaryexpression().ID());
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitDecrement(NormalFrenchParser.DecrementContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterCondition(NormalFrenchParser.ConditionContext ctx) {
    }/*
     * kan man kalla på en annan metod? typ getConditionVariable med en switch för att avgöra variabeltyp
     */
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitCondition(NormalFrenchParser.ConditionContext ctx) {
        String variable = null;
        switch (ctx.conditionvariable().getText()) {
            case "moncherie":
                variable = "gt";
                break;
            case "voulevouz":
                variable = "lt";
                break;
            case "fromage":
                variable = "eq";
                break;
            //TODO hmmmmmm
        }
        out.append(variable + "\n");
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterConditionvariable(NormalFrenchParser.ConditionvariableContext ctx) {
    } //TODO fråga johan om hjälp

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitConditionvariable(NormalFrenchParser.ConditionvariableContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterPrint(NormalFrenchParser.PrintContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
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
        out.append("exiting enterloop" + "\n");
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
    @Override public void exitEnterConditionedSegment(NormalFrenchParser.EnterConditionedSegmentContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterExitConditionedSegment(NormalFrenchParser.ExitConditionedSegmentContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitExitConditionedSegment(NormalFrenchParser.ExitConditionedSegmentContext ctx) { }
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
