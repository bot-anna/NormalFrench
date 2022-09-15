import Grammar.NormalFrenchLexer;
import Grammar.NormalFrenchParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.CharStreams;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RunCompiler {
    public static void main(String[] args) throws IOException {
        String    infile;
        String    outfile;
        Scanner scanner = new Scanner(System.in);
        CompileNormalFrench compiler = new CompileNormalFrench();

        System.out.println("Vilket program vill du kompilera?");
        infile = scanner.nextLine();
        System.out.println("Var ska den kompilerade filen sparas?");
        outfile = scanner.nextLine();

        NormalFrenchLexer lexer = new NormalFrenchLexer(CharStreams.fromFileName(infile));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        NormalFrenchParser parser = new NormalFrenchParser(tokens);
        ParseTree tree = parser.file();

        ParseTreeWalker walker   = new ParseTreeWalker();
        walker.walk(compiler, tree);

        Writer writer = new OutputStreamWriter(new FileOutputStream(outfile), StandardCharsets.US_ASCII);
        writer.write(compiler.getCompiledCode());
        writer.close();
    }
}
