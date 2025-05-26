package mars.venus;

import java.util.List;
import java.util.ArrayList;

import mars.assembler.Token;
import mars.assembler.TokenList;
import mars.assembler.TokenTypes;
import mars.assembler.Tokenizer;
import mars.venus.editors.jeditsyntax.JEditTextArea;

public class EditorSelectionRegisterHighlightListener implements SelectionListener {

    private RegisterSourceUsageHighlight[] highlight;

    public EditorSelectionRegisterHighlightListener(RegisterSourceUsageHighlight[] highlight) {
        this.highlight = highlight;
    }

    @Override
    public void selectionChanged(SelectionEvent evt) {
        JEditTextArea textArea = evt.getTextArea();
        Tokenizer tokenizer = new Tokenizer();
        int start = textArea.getSelectionStartLine();
        int end = textArea.getSelectionEndLine();
        List<String> registers = new ArrayList<String>();
        for (int i = start; i <= end; i++) {
            String lineText = textArea.getLineText(i);
            if (lineText == null) {
                continue;
            }
            TokenList toks = tokenizer.tokenizeLine(i, lineText);
            if (toks == null) {
                continue;
            }
            for (int j = 0; j < toks.size(); j++) {
                Token tok = toks.get(j);
                TokenTypes typ = tok.getType();
                if (typ.equals(TokenTypes.REGISTER_NAME) || typ.equals(TokenTypes.FP_REGISTER_NAME)) {
                    registers.add(tok.getValue());
                }
            }
        }
        
        String[] asArray = new String[registers.size()];
        for (int i = 0; i < registers.size(); i++) {
            asArray[i] = registers.get(i);
        }

        for (int i = 0; i < highlight.length; i++) {
            highlight[i].setUsedRegisterNames(asArray);
        }
    }
    
}
